import React, { useState, useEffect } from "react";
import "./OrderService.css";

const fetchOrderService = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve([
        {
          id: 1,
          title: "Order One",
          cantidad: "10",
          insumo: "BCA",
          Transaccion: "1",
          Estado: "Aprovada",
        },
        {
          id: 2,
          title: "Order Two",
          cantidad: "10",
          insumo: "BCA",
          Transaccion: "1",
          Estado: "Aprovada",
        },
        {
          id: 3,
          title: "Order Three",
          cantidad: "10",
          insumo: "BCA",
          Transaccion: "1",
          Estado: "Aprovada",
        },
        {
          id: 4,
          title: "Order Four",
          cantidad: "10",
          insumo: "BCA",
          Transaccion: "1",
          Estado: "Aprovada",
        },
        {
          id: 5,
          title: "Order Five",
          cantidad: "10",
          insumo: "BCA",
          Transaccion: "1",
          Estado: "Aprovada",
        },
        {
          id: 6,
          title: "Order Six",
          cantidad: "10",
          insumo: "BCA",
          Transaccion: "1",
          Estado: "Aprovada",
        },
      ]);
    }, 1000); // Simula un retraso de 1 segundo
  });
};

const OrderService = () => {
  const [orderservice, setOrderService] = useState([]);
  const [requestedOrderService, setRequestedOrderService] = useState({});

  useEffect(() => {
    fetchOrderService().then((data) => setOrderService(data));
  }, []);

  const handleRequest = (orderservice) => {
    setSelectedOrderService(orderservice);
    setShowModal(true);
  };


  return (
    <div className="orderservice-container">
      <div className="selectService">
      <select name="orderSelect" id="orderSelect">
          <option value="Service One">Service One</option>
          <option value="Service Two">Service Two</option>
          <option value="Service Three">Service Three</option>
          <option value="Service Four">Service Four</option>
          <option value="Service Five">Service Five</option>
          <option value="Service Six">Service Six</option>
        </select>
      </div>
      {orderservice.map((orderservice) => (
        <div key={orderservice.id} className="orderservice-card">
          <h3>{orderservice.title}</h3>
          <hr className="divider" />
          <p>
            <strong>Cantidad:</strong> {orderservice.cantidad}
          </p>
          <p>
            <strong>Insumo:</strong> {orderservice.insumo}
          </p>
          <p>
            <strong>Transaccion:</strong> {orderservice.Transaccion}
          </p>
          <p>
            <strong>Estado:</strong> {orderservice.Estado}
          </p>
        </div>
      ))}
    </div>
  );
};

export default OrderService;
