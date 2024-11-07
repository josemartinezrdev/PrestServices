import React, { useState, useEffect } from "react";
import "./Supplies.css";

const Supplies = () => {
  const [proveedores, setProveedores] = useState([]);
  const [selectedProveedorId, setSelectedProveedorId] = useState(null);
  const [supplies, setSupplies] = useState([]);
  const userToken = localStorage.getItem("userToken");

  useEffect(() => {
    // Obtener la lista de proveedores al montar el componente
    const fetchProveedores = async () => {
      try {
        const response = await fetch("/api/personas/byrole/11", {
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
        setProveedores(data);
      } catch (error) {
        alert("Error al obtener los proveedores");
        console.error(error);
      }
    };

    fetchProveedores();
  }, [userToken]);

  // Función para obtener los insumos del proveedor seleccionado
  const fetchSuppliesByProveedor = async (proveedorId) => {
    try {
      const response = await fetch(`/api/insumos/byproveedor/${proveedorId}`, {
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
      setSupplies(data);
    } catch (error) {
      alert("Error al obtener los insumos");
      console.error(error);
    }
  };

  // Manejador del cambio en el select
  const handleProveedorChange = (event) => {
    const selectedId = event.target.value;
    setSelectedProveedorId(selectedId);
    fetchSuppliesByProveedor(selectedId); // Consultar insumos por proveedor
  };

  return (
    <div className="supplies-container-general">
      <div className="Proveedor">
        <select
          name="proveedorSelect"
          id="proveedorSelect"
          onChange={handleProveedorChange}
          value={selectedProveedorId || ""}
        >
          <option value="">Seleccione un proveedor</option>
          {proveedores.map((proveedor) => (
            <option key={proveedor.nrodoc} value={proveedor.nrodoc}>
              {proveedor.nombre}
            </option>
          ))}
        </select>
      </div>
      <div className="supplies-container">
        {supplies.map((supply) => (
          <div key={supply.id} className="order-card">
            <h3>{supply.title}</h3>
            <hr className="divider" />
            <p>
              <strong>Código:</strong> {supply.codinterno}
            </p>
            <p>
              <strong>Nombre:</strong> {supply.nombre}
            </p>
            <p>
              <strong>Valor:</strong> {supply.valorunitario}
            </p>
            <p>
              <strong>Stock:</strong> {supply.stock}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Supplies;
