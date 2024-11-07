import React, { useState, useEffect } from "react";
import "./Llamados.css";

const Llamados = ({ pluralkey, onSelectId }) => {
  const [listSelect, setListSelect] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const userToken = localStorage.getItem("userToken");


  useEffect(() => {
    const traerSelect = async () => {

      // Determinar el nombre de la tabla en base al valor de pluralkey.
      const endpoint =
        pluralkey === "clientes" || pluralkey === "empleados"
          ? "personas"
          : pluralkey;

      try {
        console.log(`Fetching data from endpoint: ${endpoint}`);
        const response = await fetch(`/api/${endpoint}`, {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(`Error: ${errorData.message || response.statusText}`);
        }

        const data = await response.json();
        setListSelect(data);
      } catch (error) {
        console.error("Error trayendo los datos: ", error);
      }
    };

    traerSelect();
  }, [pluralkey]); // Eliminar tableName de las dependencias

  const handleSelectChange = (e) => {
    const idSeleccionado = e.target.value;
    setSelectedId(idSeleccionado);
    onSelectId(idSeleccionado);
  };

  return (
    <div>
      <select
        className="llamados-select"
        onChange={handleSelectChange}
        value={selectedId || ""}
      >
        <option value="">
          {listSelect.length === 0
            ? `No hay datos disponibles en ${pluralkey}`
            : pluralkey}
        </option>
        {listSelect.map((objeto) => (
          <option key={objeto.id} value={objeto.id}>
            {objeto.nombre}
          </option>
        ))}
      </select>
      {selectedId && <p>ID seleccionado: {selectedId}</p>}
    </div>
  );
};

export default Llamados;
