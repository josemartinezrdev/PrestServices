import React, { useState, useEffect } from "react";
import "./Suplies.css";

export default function Suplies() {
  const [suppliers, setSuppliers] = useState([]);
  const [selectedSupplier, setSelectedSupplier] = useState("");
  const [codigo, setCodigo] = useState("");
  const [nombre, setNombre] = useState("");
  const [preciou, setPreciou] = useState("");
  const [stock, setStock] = useState("");
  const [stockMin, setStockMin] = useState("");
  const [stockMax, setStockMax] = useState("");
  const userToken = localStorage.getItem("userToken");

  useEffect(() => {
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
        setSuppliers(data);
        console.log(data);
      } catch (error) {
        alert("Error al obtener los proveedores");
        console.error(error);
      }
    };

    fetchProveedores();
  }, [userToken]); // Add userToken as a dependency if it can change

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Create the data object based on the form inputs
    const insumoData = {
      codinterno: codigo,
      nombre: nombre,
      valorunitario: parseFloat(preciou),
      stock: parseInt(stock, 10),
      stockmin: parseInt(stockMin, 10),
      stockmax: parseInt(stockMax, 10),
      proveedorId: selectedSupplier, // Save the selected supplier ID
    };

    try {
      const response = await fetch("/api/insumos", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(insumoData),
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const result = await response.json();
      console.log(result);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div className="content">
      <form className="form" onSubmit={handleSubmit}>
        <div className="form-columns">
          <div className="form-group">
            <label htmlFor="proveedor">Proveedor</label>
            <select
              id="proveedor"
              value={selectedSupplier}
              onChange={(e) => setSelectedSupplier(e.target.value)}
              className="form-control"
              required
            >
              <option value="">Seleccione un proveedor</option>
              {suppliers.map((supplier) => (
                <option key={supplier.nrodoc} value={supplier.nrodoc}>
                  {supplier.nrodoc}
                </option>
              ))}
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="codigo">CODIGO</label>
            <input
              id="codigo"
              type="text"
              value={codigo}
              onChange={(e) => setCodigo(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="nombre">Nombre</label>
            <input
              id="nombre"
              type="text"
              value={nombre}
              onChange={(e) => setNombre(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="preciou">Precio Unitario</label>
            <input
              id="preciou"
              type="number"
              value={preciou}
              onChange={(e) => setPreciou(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="stock">Stock</label>
            <input
              id="stock"
              type="number"
              value={stock}
              onChange={(e) => setStock(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="stockMin">Stock Minimo</label>
            <input
              id="stockMin"
              type="number"
              value={stockMin}
              onChange={(e) => setStockMin(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="stockMax">Stock Maximo</label>
            <input
              id="stockMax"
              type="number"
              value={stockMax}
              onChange={(e) => setStockMax(e.target.value)}
              required
            />
          </div>
        </div>
        <button type="submit" className="btn btn-primary">
          Registrar
        </button>
      </form>
    </div>
  );
}
