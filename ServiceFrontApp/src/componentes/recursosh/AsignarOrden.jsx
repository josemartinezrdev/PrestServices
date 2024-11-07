import React, { useState } from "react";
import "./AsignarOrden.css";

const AsignarOrden = () => {
  const [nroTrabajo, setNroTrabajo] = useState("");
  const [empleado, setEmpleado] = useState("");
  const [nroOrden, setNroOrden] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí iría la lógica para asignar la orden
    console.log({ nroTrabajo, empleado, nroOrden });
  };

  return (
    <div className="asignar-orden-container">
      <h2>Asignar Orden</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nro Trabajo"
          value={nroTrabajo}
          onChange={(e) => setNroTrabajo(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Empleado"
          value={empleado}
          onChange={(e) => setEmpleado(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Nro Orden"
          value={nroOrden}
          onChange={(e) => setNroOrden(e.target.value)}
          required
        />
        <button type="submit">Asignar</button>
      </form>
    </div>
  );
};

export default AsignarOrden;