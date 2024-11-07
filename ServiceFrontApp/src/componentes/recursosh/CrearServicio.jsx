import React, { useState } from "react";
import "./CrearServicio.css";

const CrearServicio = () => {
  const [requiereInsumo, setRequiereInsumo] = useState(false);
  const [nombre, setNombre] = useState("");
  const [descripcion, setDescripcion] = useState("");
  const [insumo, setInsumo] = useState("");
  const [cantidad, setCantidad] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aquí iría la lógica para enviar los datos del servicio
    console.log({ nombre, descripcion, insumo, cantidad, requiereInsumo });
  };

  return (
    <div className="crear-servicio-container">
      <h2>Crear Servicio</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nombre"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          required
        />
        <textarea
          placeholder="Descripción"
          value={descripcion}
          onChange={(e) => setDescripcion(e.target.value)}
          required
        />
        <div className="checkbox-container">
          <input
            type="checkbox"
            id="requiereInsumo"
            checked={requiereInsumo}
            onChange={(e) => setRequiereInsumo(e.target.checked)}
          />
          <label htmlFor="requiereInsumo">Requiere insumo</label>
        </div>
        {requiereInsumo && (
          <>
            <input
              type="text"
              placeholder="Insumo"
              value={insumo}
              onChange={(e) => setInsumo(e.target.value)}
            />
            <input
              type="number"
              placeholder="Cantidad"
              value={cantidad}
              onChange={(e) => setCantidad(e.target.value)}
            />
          </>
        )}
        <button type="submit">Create</button>
      </form>
    </div>
  );
};

export default CrearServicio;