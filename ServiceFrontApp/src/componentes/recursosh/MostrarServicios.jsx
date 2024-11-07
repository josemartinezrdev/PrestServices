import React, { useState, useEffect } from "react";
import "./MostrarServicios.css";

// Simulación de una llamada al backend
const fetchServicios = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve([
        { nroTrabajo: '001', fecha: '2023-05-20', empleado: 'EMP001', nroOrden: '1001' },
        { nroTrabajo: '002', fecha: '2023-05-21', empleado: 'EMP002', nroOrden: '1002' },
        { nroTrabajo: '003', fecha: '2023-05-22', empleado: 'EMP003', nroOrden: '1003' },
        { nroTrabajo: '004', fecha: '2023-05-23', empleado: 'EMP004', nroOrden: '1004' },
        { nroTrabajo: '005', fecha: '2023-05-24', empleado: 'EMP005', nroOrden: '1005' },
        { nroTrabajo: '006', fecha: '2023-05-25', empleado: 'EMP006', nroOrden: '1006' },
      ]);
    }, 1000); // Simula un retraso de 1 segundo
  });
};

const MostrarServicios = () => {
  const [servicios, setServicios] = useState([]);

  useEffect(() => {
    fetchServicios().then((data) => setServicios(data));
  }, []);

  return (
    <div className="servicios-container">
      <h2>Servicios Asignados</h2>
      {servicios.map((servicio) => (
        <div key={servicio.nroTrabajo} className="servicio-card">
          <h3>Orden Trabajo {servicio.nroTrabajo}</h3>
          <hr className="divider" />
          <p>
            <strong>Nro Trabajo:</strong> {servicio.nroTrabajo}
          </p>
          <p>
            <strong>Fecha:</strong> {servicio.fecha}
          </p>
          <p>
            <strong>Empleado:</strong> {servicio.empleado}
          </p>
          <p>
            <strong>Nro Orden:</strong> {servicio.nroOrden}
          </p>
        </div>
      ))}
    </div>
  );
};

export default MostrarServicios;