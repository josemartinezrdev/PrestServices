import React, { useState, useEffect } from "react";
import "./Suppliers.css";
import purchasesIcon from "../../../public/shopping-icon.svg"; // Icono de compras

const Suppliers = () => {
  const [suppliers, setSuppliers] = useState([]);
  const [cart, setCart] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [supplies, setSupplies] = useState([]);
  const [selectedSupplier, setSelectedSupplier] = useState(null);
  const [insumoSelections, setInsumoSelections] = useState([
    { insumoId: "", cantidad: 1 },
  ]); // Estado inicial para un solo insumo
  const userToken = localStorage.getItem("userToken");
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));

  // Cargar lista de proveedores al montar el componente
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
      } catch (error) {
        alert("Error al obtener los proveedores");
        console.error(error);
      }
    };

    fetchProveedores();
  }, []);

  // Función para abrir el modal y cargar los insumos del proveedor
  const handleRequest = async (supplier) => {
    setSelectedSupplier(supplier);
    await fetchSuppliesByProveedor(supplier.nrodoc);
    setShowModal(true);
  };

  // Cerrar modal y limpiar selecciones
  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedSupplier(null);
    setInsumoSelections([{ insumoId: "", cantidad: 1 }]); // Restablecer a un solo insumo
  };

  // Obtener insumos de un proveedor específico
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

  // Manejar el cambio en el `select` o en el campo de cantidad
  const handleInsumoChange = (index, field, value) => {
    const updatedSelections = [...insumoSelections];
    updatedSelections[index][field] = field === "insumoId" ? parseInt(value, 10) : value; // Convertir insumoId a entero
    setInsumoSelections(updatedSelections);
  };

  // Agregar otro `select` de insumo y campo de cantidad
  const handleAddInsumo = () => {
    setInsumoSelections([...insumoSelections, { insumoId: "", cantidad: 1 }]);
  };

  // Eliminar un insumo específico
  const handleRemoveInsumo = (index) => {
    setInsumoSelections(insumoSelections.filter((_, i) => i !== index));
  };

  // Enviar el pedido
  console.log(insumoSelections);
  const handlePlaceOrder = async () => {
    const transaccion = {
      total: 0,
      persona: { nrodoc: userInfo.nrodoc },
      estadoAprobacion: { id: 1 },
      proveedor: { nrodoc: selectedSupplier.nrodoc },
    };

    if (
      insumoSelections.length === 0 ||
      insumoSelections.every((selection) => selection.insumoId === "")
    ) {
      alert("No puedes enviar una transacción vacía.");
      return;
    }

    // Cálculo del Total
    let total = 0;
    insumoSelections.forEach((selection) => {
      const insumo = supplies.find(
        (supply) => supply.id === parseInt(selection.insumoId, 10) // Asegurar que ambos sean enteros para comparación
      );
      console.log(insumo);
      if (insumo) {
        const valorUnitario = parseFloat(insumo.valorunitario);
        const cantidad = parseInt(selection.cantidad, 10);
        if (!isNaN(valorUnitario) && !isNaN(cantidad)) {
          total += valorUnitario * cantidad;
        }
      }
    });
    transaccion.total = total;

    console.log("Total calculado:", transaccion.total);

    try {
      const responseTransaccion = await fetch("/api/transacciones", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(transaccion),
      });

      if (!responseTransaccion.ok) {
        const errorData = await responseTransaccion.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error al crear la transacción");
      }

      const dataTransaccion = await responseTransaccion.json();

      await Promise.all(
        insumoSelections.map(async (selection) => {
          const pedido = {
            cantidad: selection.cantidad,
            insumo: { id: selection.insumoId },
            transaccion: { id: dataTransaccion.id },
            estadoAprobacion: { id: 1 },
          };

          const responsePedido = await fetch("/api/pedidos", {
            method: "POST",
            headers: {
              Authorization: `Bearer ${userToken}`,
              "Content-Type": "application/json",
            },
            body: JSON.stringify(pedido),
          });

          if (!responsePedido.ok) {
            const errorData = await responsePedido.json();
            alert(
              `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
            );
            throw new Error("Error al crear el pedido");
          }
        })
      );

      alert("Pedido realizado con éxito.");
    } catch (error) {
      console.error("Error en la transacción o pedidos:", error);
    }

    setInsumoSelections([{ insumoId: "", cantidad: 1 }]);
    setShowModal(false);
  };

  return (
    <div className="suppliers-container">
      {suppliers.map((supplier) => (
        <div key={supplier.nrodoc} className="supplier-card">
          <h3>{supplier.nombre}</h3>
          <hr className="divider" />
          <p>
            <strong>ID:</strong> {supplier.nrodoc}
          </p>
          <p>
            <strong>Nombre:</strong> {supplier.nombre}
          </p>
          <p>
            <strong>Apellido:</strong> {supplier.apellido}
          </p>
          <p>
            <strong>Registro:</strong> {supplier.fecharegistro}
          </p>
          <button onClick={() => handleRequest(supplier)}>
            Ordenar Insumos
          </button>
        </div>
      ))}

      {showModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>Ordenar insumos de {selectedSupplier?.nombre}</h2>

            {insumoSelections.map((selection, index) => (
              <div key={index} className="insumo-selection">
                <select
                  value={selection.insumoId}
                  onChange={(e) =>
                    handleInsumoChange(index, "insumoId", e.target.value)
                  }
                >
                  <option value="">Seleccionar insumo</option>
                  {supplies.map((insumo) => (
                    <option key={insumo.id} value={insumo.id}>
                      {insumo.nombre}
                    </option>
                  ))}
                </select>
                <input
                  type="number"
                  min="1"
                  value={selection.cantidad}
                  onChange={(e) =>
                    handleInsumoChange(index, "cantidad", e.target.value)
                  }
                  placeholder="Cantidad"
                />
                <button onClick={() => handleRemoveInsumo(index)}>
                  Eliminar
                </button>
              </div>
            ))}

            <button onClick={handleAddInsumo}>Agregar otro insumo</button>

            <div className="modal-actions">
              <button onClick={handleCloseModal}>Cerrar</button>
              <button onClick={handlePlaceOrder}>Hacer Pedido</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Suppliers;
