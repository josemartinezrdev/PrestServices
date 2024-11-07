/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from "react";
import "./Asignacion.css";

const Asignacion = () => {
  const [asignacion, setAsignacion] = useState([]);
  const [requestedAsignacion, setRequestedAsignacion] = useState({});
  const userToken = localStorage.getItem("userToken");
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));

  const findAllDetOrdenesTrabajo = async () => {
    try {
      const responseDetOrdenTrabajo = await fetch(
        `/api/detalleordentrabajos/byprofesional/${userInfo.nrodoc}/1`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (!responseDetOrdenTrabajo.ok) {
        const errorData = await responseDetOrdenTrabajo.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando ordenes trabajos");
      }
      const data = await responseDetOrdenTrabajo.json();
      console.table(data);
      setAsignacion(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    findAllDetOrdenesTrabajo();
  }, []);

  const handleRequest = async (asignacionItem) => {
    console.log(asignacionItem.id);
    const detalleOrdenTrabajoUp = {
      ordenTrabajo: { id: asignacionItem.ordenTrabajo.id },
      servicio: { id: asignacionItem.servicio.id },
      empleado: { nrodoc: asignacionItem.empleado.nrodoc },
      estadoSerOrden: { id: 2 },
    };

    console.log(detalleOrdenTrabajoUp);

    const responseDetOrdenTrabajo = await fetch(
      `/api/detalleordentrabajos/${asignacionItem.id}`,
      {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(detalleOrdenTrabajoUp),
      }
    );

    if (!responseDetOrdenTrabajo.ok) {
      const errorData = await responseDetOrdenTrabajo.json();
      alert(
        `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
      );
      throw new Error("Error actualizando ordenes trabajos");
    }

    // findAllDetOrdenesTrabajo(); // Elimina logicamente las ordenes trabajos que ya esten terminadas

    if (!requestedAsignacion[asignacionItem.id]) {
      setRequestedAsignacion((prevState) => ({
        ...prevState,
        [asignacionItem.id]: true,
      }));
    }
  };

  return (
    <div className="asignacion-container">
      {asignacion.map((asignacionItem) => (
        <div key={asignacionItem.id} className="asignacion-card">
          <h3>{asignacionItem.ordenTrabajo.nrotrabajo}</h3>
          <hr className="divider" />
          <p>
            <strong>Fecha Programada:</strong>{" "}
            {asignacionItem.fecha ? asignacionItem.fecha : "Sin fecha"}
          </p>
          <p>
            <strong>Servicio:</strong> {asignacionItem.servicio.nombre}
          </p>
          <p>
            <strong>Descripcion:</strong> {asignacionItem.servicio.descripcion}
          </p>
          <p>
            <strong>Estado:</strong> {asignacionItem.estadoSerOrden.nombre}
          </p>
          <button
            onClick={() => handleRequest(asignacionItem)}
            className={
              requestedAsignacion[asignacionItem.id] ? "requested" : ""
            }
            disabled={requestedAsignacion[asignacionItem.id]}
          >
            {requestedAsignacion[asignacionItem.id]
              ? "Finalizado"
              : "Finalizar"}
          </button>
        </div>
      ))}
    </div>
  );
};

export default Asignacion;
