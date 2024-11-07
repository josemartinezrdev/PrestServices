import React, { useState, useEffect } from "react";
import "./Customer.css";

const Customers = () => {
  const [customers, setCustomers] = useState([]);
  const userToken = localStorage.getItem("userToken");

  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await fetch("/api/personas/byrole/3", {
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
        setCustomers(data);
      } catch (error) {
        alert("Error al obtener las órdenes");
        console.error(error);
      }
    };

    fetchCustomers();
  }, [userToken]);

  return (
    <div className="orders-container">
      {customers.map((customer) => (
        <div key={customer.id} className="order-card card">
          <h3>{customer.title || "No asignado"}</h3>
          <hr className="divider" />
          <p>
            <strong>Nro Doc:</strong> {customer.nrodoc || "No asignado"}
          </p>
          <p>
            <strong>Nombre:</strong> {customer.nombre || "No asignado"}
          </p>
          <p>
            <strong>Apellido:</strong> {customer.apellido || "No asignado"}
          </p>
          <p>
            <strong>Registro:</strong> {customer.fecharegistro || "No asignado"}
          </p>
          <p>
            <strong>Tipo Persona:</strong>{" "}
            {customer.tipoPersona?.nombre || "No asignado"}
          </p>
          <p>
            <strong>Sucursal:</strong>{" "}
            {customer.sucursal?.nombre || "No asignado"}
          </p>
        </div>
      ))}
    </div>
  );
};

export default Customers;
