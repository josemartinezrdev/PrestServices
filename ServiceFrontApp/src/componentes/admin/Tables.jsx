import React, { useState, useEffect } from "react";
import CrudTable from "./CrudTable";
import "./Tables.css";
import tablas from "./ObjetosTablas";

const Tables = () => {
  const [selectedTable, setSelectedTable] = useState("");
  const [data, setData] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false); // Estado para controlar la apertura del modal
  const userToken = localStorage.getItem("userToken");


  const handleTableSelect = (tableName) => {
    setSelectedTable(tableName);
    console.log("hola", tableName);
    setIsModalOpen(true); // Abre el modal cuando se selecciona una tabla
    setData([]);
  };

  const findByName = (name) => {
    for (const [nombre, objeto] of Object.entries(tablas)) {
      if (nombre === name) {
        return objeto;
      }
    }
    return null;
  };

  const simplifyObject = (obj) => {
    const simplified = {};
    for (const key in obj) {
      if (
        obj[key] &&
        typeof obj[key] === "object" &&
        (obj[key].hasOwnProperty("id") ||
          obj[key].hasOwnProperty("norden") ||
          obj[key].hasOwnProperty("nrodoc"))
      ) {
        simplified[key] = {
          id: obj[key].id || obj[key].norden || obj[key].nrodoc,
        };
      } else {
        simplified[key] = obj[key];
      }
    }
    return simplified;
  };

  const fetchData = async (table) => {
    const tablaName = findByName(table);
    console.log("tabla antes del",table);

    try {
      const response = await fetch(`/api/${table}`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      });
      const fetchedData = await response.json();

      if (!response.ok) {
        alert(
          `${fetchedData.error}\n${fetchedData.message}\nEstado: ${fetchedData.status}`
        );
        setData([...data, tablaName]);
        console.log("tablename",tablaName);
        console.log("data",data);
      } else {
        const processedData = fetchedData.map(simplifyObject);
        setData(processedData);
      }
    } catch (error) {
      console.error("Error buscando: ", error);
    }
  };

  useEffect(() => {
    if (selectedTable) {
      fetchData(selectedTable);
    }
  }, [selectedTable]);

  const closeModal = () => {
    setIsModalOpen(false); // Cierra el modal
    setSelectedTable(""); // Resetea la tabla seleccionada
  };

  return (
    <div className="tables-container">
      <h1 className="titulo">Administrar Tablas</h1>
      <div className="table-selection">
        {[
          "aprobacionservicios",
          "ciudades",
          "detalleordenes",
          "detalleordentrabajos",
          "direcciones",
          "emailpersonas",
          "empresas",
          "estadoaprobaciones",
          "estadoordenes",
          "estadoserordenes",
          "insumos",
          "ordenservicios",
          "ordentrabajos",
          "paises",
          "pedidos",
          "personainsumos",
          "personas",
          "regiones",
          "roles",
          "servicioinsumos",
          "servicios",
          "sucursales",
          "sucursalservicio",
          "telpersonas",
          "telsucursales",
          "tipoemails",
          "tipoempresas",
          "tipopersonas",
          "tipotelefonos",
          "transacciones",
          "userroles",
          "user",
        ].map((tableName) => (
          <div
            key={tableName}
            className="table-card"
            onClick={() => handleTableSelect(tableName)}
          >
            <p className="heading">{tableName}</p>
            <p>Administrar tabla</p>
          </div>
        ))}
      </div>

      {isModalOpen && (
        <div className="modal-overlay-admin" onClick={closeModal}>
          <div className="modal-content-admin" onClick={(e) => e.stopPropagation()}>
            <button className="close-button" onClick={closeModal}>
              &times;
            </button>
            <CrudTable
              tableName={selectedTable}
              data={data}
              onDataUpdate={(updatedData) => setData(updatedData)}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default Tables;
