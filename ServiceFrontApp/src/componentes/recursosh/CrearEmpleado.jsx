import React, { useState } from "react";
import "./CrearEmpleado.css";

const CrearEmpleado = () => {
  const [nroDoc, setNroDoc] = useState("");
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [sucursal, setSucursal] = useState("");
  const [tipo, setTipo] = useState("");
  const [userId, setUserId] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí iría la lógica para enviar los datos del empleado
    console.log({ nroDoc, nombre, apellido, sucursal, tipo, userId });
  };

  return (
    <div className="crear-empleado-container">
      <h2>Crear Empleado</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nro Doc"
          value={nroDoc}
          onChange={(e) => setNroDoc(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Nombre"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Apellido"
          value={apellido}
          onChange={(e) => setApellido(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Sucursal"
          value={sucursal}
          onChange={(e) => setSucursal(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Tipo"
          value={tipo}
          onChange={(e) => setTipo(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          required
        />
        <button type="submit">Create</button>
      </form>
    </div>
  );
};

export default CrearEmpleado;
