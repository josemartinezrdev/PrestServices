import React, { useState, useEffect } from "react";
import "./Orders.css"; // Asegúrate de tener el archivo de estilos correcto

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const userToken = localStorage.getItem("userToken");

  useEffect(() => {
    // Llamada a la API para obtener las órdenes
    const fetchOrders = async () => {
      try {
        const response = await fetch("/api/ordenservicios/byestado/1", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          const errorData = await response.json();
          alert(
            `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
          );
          return;
        }

        const data = await response.json();
        console.log(data);
        setOrders(data);
      } catch (error) {
        alert("Error al obtener las órdenes");
        console.error(error);
      }
    };

    fetchOrders();
  }, [userToken]);

  return (
    <div className="orders-container">
      {orders.map((order) => (
        <div key={order.norden} className="order-card card">
          <h3 className="heading">Orden #{order.norden || "No asignado"}</h3>
          <p>
            Fecha:{" "}
            {order.fecha
              ? new Date(order.fecha).toLocaleDateString()
              : "Fecha no disponible"}
          </p>
          <p>Motivo: {order.motivo || "No asignado"}</p>
          <p>
            Cliente: {order.cliente?.nombre || "No asignado"}{" "}
            {order.cliente?.apellido || ""} (ID:{" "}
            {order.cliente?.nrodoc || "No asignado"})
          </p>
          <p>
            Empleado: {order.empleado?.nombre || "No asignado"}{" "}
            {order.empleado?.apellido || ""} (ID:{" "}
            {order.empleado?.nrodoc || "No asignado"})
          </p>
          <p>
            Estado de Orden: {order.estadoOrden?.id || "No asignado"} -{" "}
            {order.estadoOrden?.nombre || "No asignado"}
          </p>
        </div>
      ))}
    </div>
  );
};

export default Orders;
