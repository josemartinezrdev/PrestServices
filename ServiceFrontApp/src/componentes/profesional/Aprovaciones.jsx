/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from "react";
import "./Aprovaciones.css";

const Aprovaciones = () => {
  const [aprovacions, setAprovaciones] = useState([]);
  const [requestedAprovaciones, setRequestedAprovaciones] = useState({});
  const [selectedAprovacion, setSelectedAprovacion] = useState(null);
  const [hallazgo, setHallazgo] = useState("");
  const [solucion, setSolucion] = useState("");
  const [aprobacionCreate, setAprobacionCreate] = useState(null);
  const userToken = localStorage.getItem("userToken");

  const findAllAprovaciones = async () => {
    try {
      const responseAprobaciones = await fetch(
        "/api/ordenservicios/byestado/1",
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (!responseAprobaciones.ok) {
        const errorData = await responseAprobaciones.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando orden servicios");
      }
      const data = await responseAprobaciones.json();
      console.log(data);
      setAprovaciones(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    findAllAprovaciones();
  }, []);

  const handleRequest = async (aprovacion) => {
    setSelectedAprovacion(aprovacion);
    try {
      const responseServicio = await fetch(
        `/api/aprobacionservicios/byordenservicio/${aprovacion.norden}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (!responseServicio.ok) {
        const errorData = await responseServicio.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando servicio por orden");
      }

      const data = await responseServicio.json();

      setAprobacionCreate({
        ordenservicio: { norden: aprovacion.norden },
        cliente: { nrodoc: aprovacion.cliente.nrodoc },
        servicio: { id: data.id },
        estadoaprobacion: { id: 1 },
        hallazgo: "",
        solucion: "",
      });
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = async () => {
    if (!aprobacionCreate) {
      alert("No se ha seleccionado una aprobación.");
      return;
    }

    const updatedAprobacion = {
      ...aprobacionCreate,
      hallazgo,
      solucion,
    };

    console.log(updatedAprobacion);

    try {
      const responseAprobacionCreate = await fetch("/api/aprobacionservicios", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedAprobacion),
      });

      if (!responseAprobacionCreate.ok) {
        const errorData = await responseAprobacionCreate.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error añadiendo la aprobación servicio");
      }

      console.log(await responseAprobacionCreate.json());

      const ordenservicio = {
        cliente: { nrodoc: updatedAprobacion.cliente.nrodoc },
        empleado: { nrodoc: "bot" },
        estadoOrden: { id: 2 },
      };

      const responseEstadoUpdate = await fetch(
        `/api/ordenservicios/${aprobacionCreate.ordenservicio.norden}`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(ordenservicio),
        }
      );

      if (!responseEstadoUpdate.ok) {
        const errorData = await responseEstadoUpdate.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error actualizando el estado de la orden");
      }

      alert("Aprobación registrada y estado actualizado correctamente.");
      setHallazgo("");
      setSolucion("");
      closeModal();
      findAllAprovaciones();
    } catch (error) {
      console.error(error);
    }
  };

  const closeModal = () => {
    setSelectedAprovacion(null);
    setHallazgo("");
    setSolucion("");
    setAprobacionCreate(null);
  };

  return (
    <div className="aprovaciones-container">
      {aprovacions.map((aprovacion) => (
        <div key={aprovacion.norden} className="aprovacion-card">
          <h3>
            <strong>Orden:</strong> {aprovacion.norden}
          </h3>
          <hr className="divider" />
          <p>
            <strong>Fecha:</strong> {aprovacion.fecha}
          </p>
          <p>
            <strong>Nro Cliente:</strong> {aprovacion.cliente.nrodoc}
          </p>
          <p>
            <strong>Cliente:</strong> {aprovacion.cliente.nombre}
          </p>
          <p>
            <strong>Estado:</strong> {aprovacion.estadoOrden.nombre}
          </p>
          <button
            onClick={() => handleRequest(aprovacion)}
            className={
              requestedAprovaciones[aprovacion.norden] ? "requested" : ""
            }
            disabled={requestedAprovaciones[aprovacion.norden]}
          >
            {requestedAprovaciones[aprovacion.norden] ? "Revisado" : "Revisar"}
          </button>
        </div>
      ))}
      {selectedAprovacion && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={closeModal}>
              &times;
            </span>
            <h2>Registrar Aprobación</h2>
            <div>
              <label htmlFor="hallazgo">Hallazgo:</label>
              <textarea
                id="hallazgo"
                value={hallazgo}
                onChange={(e) => setHallazgo(e.target.value)}
              />
            </div>
            <div>
              <label htmlFor="solucion">Solución:</label>
              <textarea
                id="solucion"
                value={solucion}
                onChange={(e) => setSolucion(e.target.value)}
              />
            </div>
            <button onClick={handleSubmit}>Enviar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Aprovaciones;
