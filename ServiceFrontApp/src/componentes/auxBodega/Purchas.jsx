/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from "react";
import "./Purchas.css";

const Purchas = () => {
  const userToken = localStorage.getItem("userToken");

  const [purchas, setPurchas] = useState([]);
  const [servicios, setServicios] = useState([]);
  const [requestedPurchas, setRequestedPurchas] = useState({});

  const findAllServicios = async () => {
    try {
      const responseServicios = await fetch("/api/servicios", {
        method: "GET",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      });

      if (!responseServicios.ok) {
        const errorData = await responseServicios.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando");
      }

      const data = await responseServicios.json();
      setServicios(data);
    } catch (error) {
      console.error("Error fetching servicios:", error);
    }
  };

  const findTransaccionByServiceId = async (event) => {
    const serviceId = event.target.value;

    const responseTransaccion = await fetch(
      `/api/transacciones/byservicio/${serviceId}`, // ¨¨ IMPORTANTE AÑADIR EL ESTADO TAMBIEN
      {
        method: "GET",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      }
    );
    if (!responseTransaccion.ok) {
      const errorData = await responseTransaccion.json();
      alert(
        `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
      );
      throw new Error("Error buscando");
    }
    const data = await responseTransaccion.json();
    console.log(data);
    setPurchas(data);
  };

  useEffect(() => {
    findAllServicios();
  }, []);

  const aprobar = async (purchasId, updateObj) => {
    const responseAprobar = await fetch(`/api/transacciones/${purchasId}`, {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${userToken}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updateObj),
    });
    if (!responseAprobar.ok) {
      const errorData = await responseAprobar.json();
      alert(
        `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
      );
      throw new Error("Error buscando");
    }
  };

  const rechazar = async (purchasId, updateObj) => {
    const responseAprobar = await fetch(`/api/transacciones/${purchasId}`, {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${userToken}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updateObj),
    });
    if (!responseAprobar.ok) {
      const errorData = await responseAprobar.json();
      alert(
        `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
      );
      throw new Error("Error buscando");
    }
  };

  return (
    <div className="purchas-container">
      <div className="selectPurchas">
        <select
          name="orderPurchas"
          id="orderPurchas"
          onChange={findTransaccionByServiceId}
        >
          <option value="">Seleccione el servicio</option>
          {servicios.map((servicio) => (
            <option key={servicio.id} value={servicio.id}>
              {servicio.nombre}
            </option>
          ))}
        </select>
      </div>
      {purchas.map((purchas) => (
        <div key={purchas.id} className="purchas-card">
          <h3>ID Transaccion: {purchas.id}</h3>
          <hr className="divider" />
          <p>
            <strong>Fecha:</strong> {purchas.fecha}
          </p>
          <p>
            <strong>Doc Cliente:</strong> {purchas.persona.nrodoc}
          </p>
          <p>
            <strong>Cliente:</strong> {purchas.persona.nombre}
          </p>
          <p>
            <strong>Total:</strong> {purchas.total}
          </p>
          <p>
            <strong>Estado:</strong> {purchas.estadoAprobacion.nombre}
          </p>
          <div className="button-container">
            <button
              onClick={() =>
                aprobar(purchas.id, {
                  total: purchas.total,
                  persona: { nrodoc: purchas.persona.nrodoc },
                  estadoAprobacion: { id: 2 },
                  proveedor: { nrodoc: purchas.proveedor.nrodoc },
                })
              }
            >
              Aprobar
            </button>
            <button
              onClick={() =>
                rechazar(
                  (purchas.id,
                  {
                    total: purchas.total,
                    persona: { nrodoc: purchas.persona.nrodoc },
                    estadoAprobacion: { id: 3 },
                    proveedor: { nrodoc: purchas.proveedor.nrodoc },
                  })
                )
              }
            >
              Rechazar
            </button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Purchas;
