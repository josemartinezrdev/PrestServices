import React, { useState, useEffect } from "react";
import "./OrderAprov.css";

const fetchOrderAprov = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve([
        {
          id: 1,
          title: "Order One",
          cantidad: "5",
          insumo: "Perez",
          Transaccion: "ABC",
          Estado: "2",
        },
        {
          id: 2,
          title: "Order Two",
          cantidad: "5",
          insumo: "Gomez",
          Transaccion: "ABC",
          Estado: "1",
        },
        {
          id: 3,
          title: "Order Three",
          cantidad: "5",
          insumo: "Rodriguez",
          Transaccion: "ABC",
          Estado: "1",
        },
        {
          id: 4,
          title: "Order Four",
          cantidad: "5",
          insumo: "Martinez",
          Transaccion: "ABC",
          Estado: "1",
        },
        {
          id: 5,
          title: "Order Five",
          cantidad: "5",
          insumo: "Lopez",
          Transaccion: "ABC",
          Estado: "1",
        },
        {
          id: 6,
          title: "Order Six",
          cantidad: "5",
          insumo: "Diaz",
          Transaccion: "ABC",
          Estado: "1",
        },
      ]);
    }, 1000); // Simula un retraso de 1 segundo
  });
};
const OrderAprov = () => {
  const [orderaprovs, setOrderAprov] = useState([]);
  const [requestedOrderAprov, setRequestedOrderAprov] = useState({});

  useEffect(() => {
    fetchOrderAprov().then((data) => setOrderAprov(data));
  }, []);

  const handleRequest = () => {
    // Handle the dispatch request for all orders
    setRequestedOrderAprov((prevState) => ({
      ...prevState,
      dispatched: true,
    }));
  };

  return (
    <div className="orderaprovs-container">
      <div className="selectTransaccion">
        <select name="Transaccion" id="Transaccion">
          <option value="Transaccion One">Transaccion One</option>
          <option value="Transaccion Two">Transaccion Two</option>
          <option value="Transaccion Three">Transaccion Three</option>
          <option value="Transaccion Four">Transaccion Four</option>
          <option value="Transaccion Five">Transaccion Five</option>
          <option value="Transaccion Six">Transaccion Six</option>
        </select>
      </div>
      {orderaprovs.map((orderaprov) => (
        <div key={orderaprov.id} className="orderaprov-card">
          <h3>{orderaprov.title}</h3>
          <hr className="divider" />
          <p>
            <strong>Cantidad:</strong> {orderaprov.cantidad}
          </p>
          <p>
            <strong>Insumo:</strong> {orderaprov.insumo}
          </p>
          <p>
            <strong>Transaccion:</strong> {orderaprov.Transaccion}
          </p>
          <p>
            <strong>Estado:</strong> {orderaprov.Estado}
          </p>
        </div>
      ))}
      <button
        onClick={handleRequest}
        className={`dispatch-button ${
          requestedOrderAprov.dispatched ? "requested" : ""
        }`}
      >
        {requestedOrderAprov.dispatched ? "Despachado" : "Despachar"}
      </button>
    </div>
  );
};

export default OrderAprov;
