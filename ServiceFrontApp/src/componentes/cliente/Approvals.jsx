/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from "react";
// import { jwtDecode } from "jwt-decode";
// import { useNavigate } from "react-router-dom";
import "./Approvals.css";

const Approvals = () => {
  const [approvals, setApprovals] = useState([]);
  const userToken = localStorage.getItem("userToken");
  // const decodedToken = jwtDecode(userToken);
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));

  const findAll = async () => {
    try {
      const response = await fetch(
        `/api/aprobacionservicios/persona/${userInfo.nrodoc}/1`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (!response.ok) {
        const errorData = await response.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando");
      }
      const data = await response.json();
      setApprovals(data);
    } catch (error) {
      console.error("Error buscando: ", error);
    }
  };
  useEffect(() => {
    findAll();
  }, []);

  const handleApprove = async (approval) => {
    try {
      const update = {
        ordenservicio: { norden: approval.ordenservicio.norden },
        cliente: { nrodoc: approval.cliente.nrodoc },
        servicio: { id: approval.servicio.id },
        hallazgo: approval.hallazgo,
        solucion: approval.solucion,
        estadoaprobacion: { id: 2 },
      };
      const responseAproServicio = await fetch(
        `/api/aprobacionservicios/${approval.id}`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(update),
        }
      );

      if (!responseAproServicio.ok) {
        const errorData = await responseAproServicio.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error actualizando");
      }
      alert("Bien 👍🏻");
    } catch (error) {
      console.error("Error al aprobar:", error);
    }
  };

  const handleCancel = async (approval) => {
    try {
      const update = {
        ordenservicio: { norden: approval.ordenservicio.norden },
        cliente: { nrodoc: approval.cliente.nrodoc },
        servicio: { id: approval.servicio.id },
        hallazgo: approval.hallazgo,
        solucion: approval.solucion,
        estadoaprobacion: { id: 3 },
      };
      const responseAproServicio = await fetch(
        `/api/aprobacionservicios/${approval.id}`,
        {
          method: "PUT",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(update),
        }
      );

      if (!responseAproServicio.ok) {
        const errorData = await responseAproServicio.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error actualizando");
      }
      alert("Bien 👍🏻");
    } catch (error) {
      console.error("Error al aprobar:", error);
    }
  };

  return (
    <div className="approvals-container">
      {approvals.length === 0 ? (
        <h1>No encontré nada 😢 </h1>
      ) : (
        approvals.map((approval) => (
          <div key={approval.id} className="approval-card">
            <h3>Nro Doc: {approval.cliente.nrodoc}</h3>
            <p>Hallazgo: {approval.hallazgo}</p>
            <p>Solución: {approval.solucion}</p>
            <p>Estado Aprobación: {approval.estadoaprobacion.id}</p>
            <p>Nro Orden: {approval.ordenservicio.norden}</p>
            <p>Servicio: {approval.servicio.id}</p>
            <div className="approval-buttons">
              <button
                onClick={() => handleApprove(approval)}
                className="approve"
              >
                Aprobar
              </button>
              <button
                onClick={() => handleCancel(approval)}
                className="cancel"
              >
                Cancelar
              </button>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default Approvals;
