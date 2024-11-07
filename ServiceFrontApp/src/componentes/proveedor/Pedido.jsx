import React, { useState, useEffect } from "react";
import "./Pedido.css";

const Pedido = () => {
  const [pedidos, setPedidos] = useState([]);
  const [transacciones, setTransacciones] = useState([]);
  const [selectedTransaccion, setSelectedTransaccion] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [totalPorInsumo, setTotalPorInsumo] = useState({});
  const userToken = localStorage.getItem("userToken");
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));


  // Function to fetch transactions
  const fetchTransacciones = async () => {
    try {
      const response = await fetch(`api/transacciones/byrole/${userInfo.nrodoc}/1`, {
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
      setTransacciones(data);
    } catch (error) {
      alert("Error al obtener las transacciones");
      console.error(error);
    }
  };

  // Fetch de las transacciones al cargar el componente
  useEffect(() => {
    fetchTransacciones();
  }, [userToken]);

  // Fetch de pedidos basado en la transacción seleccionada
  useEffect(() => {
    if (selectedTransaccion) {
      const fetchPedidosPorTransaccion = async (transaccionId) => {
        try {
          const response = await fetch(
            `/api/pedidos/bytransaccion/${transaccionId}`,
            {
              method: "GET",
              headers: {
                Authorization: `Bearer ${userToken}`,
                "Content-Type": "application/json",
              },
            }
          );

          if (!response.ok) {
            const errorData = await response.json();
            alert(
              `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
            );
            return;
          }

          const data = await response.json();
          setPedidos(data);
        } catch (error) {
          console.error("Error al obtener los pedidos:", error);
          alert("Error al obtener los pedidos.");
        }
      };

      fetchPedidosPorTransaccion(selectedTransaccion);
    }
  }, [selectedTransaccion]);

  const calcularTotales = () => {
    const totales = {};
    // Se eliminó el cálculo de precio total, ya que se obtendrá de la transacción
    pedidos.forEach((pedido) => {
      if (pedido.estadoAprobacion.id !== 2) {
        // Filtra pedidos no despachados
        totales[pedido.insumo.nombre] =
          (totales[pedido.insumo.nombre] || 0) + pedido.cantidad;
      }
    });
    setTotalPorInsumo(totales);
  };

  const handleRequest = () => {
    calcularTotales();
    setShowModal(true);
  };

  const closeModal = async () => {
    const transaccionSeleccionada = transacciones.find(
      (transaccion) => transaccion.id === Number(selectedTransaccion) // Conversión a número
    );

    if (transaccionSeleccionada) {
      // Step 1: Aggregate quantities by insumo
      const stockUpdates = pedidos.reduce((acc, pedido) => {
        const insumoId = pedido.insumo.id; // Assuming insumo has an ID
        if (!acc[insumoId]) {
          acc[insumoId] = {
            ...pedido.insumo,
            stock: pedido.insumo.stock + pedido.cantidad, // Initialize stock
          };
        } else {
          acc[insumoId].stock += pedido.cantidad; // Sum quantities for existing insumos
        }
        return acc;
      }, {});

      // Step 2: Prepare an array of update promises for each unique insumo
      const stockUpdatePromises = Object.values(stockUpdates).map(
        async (insumoToUpdate) => {
          try {
            const response = await fetch(`/api/insumos/${insumoToUpdate.id}`, {
              method: "PUT",
              headers: {
                Authorization: `Bearer ${userToken}`,
                "Content-Type": "application/json",
              },
              body: JSON.stringify(insumoToUpdate),
            });

            if (!response.ok) {
              const errorData = await response.json();
              alert(
                `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
              );
              throw new Error("Error updating stock");
            }
          } catch (error) {
            console.error("Error updating stock:", error);
            alert("Error al actualizar el stock.");
          }
        }
      );

      // Wait for all stock updates to complete
      await Promise.all(stockUpdatePromises);

      // Step 3: Prepare the transaction object
      const transaccion = {
        total: transaccionSeleccionada.total,
        persona: { nrodoc: transaccionSeleccionada.persona.nrodoc },
        estadoAprobacion: { id: 3 },
        proveedor: { nrodoc: transaccionSeleccionada.proveedor.nrodoc },
        fecha: transaccionSeleccionada.fecha,
      };

      try {
        const response = await fetch(
          `/api/transacciones/${transaccionSeleccionada.id}`,
          {
            method: "PUT",
            headers: {
              Authorization: `Bearer ${userToken}`,
              "Content-Type": "application/json",
            },
            body: JSON.stringify(transaccion),
          }
        );

        if (!response.ok) {
          const errorData = await response.json();
          alert(
            `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
          );
          return;
        }

        // Call fetchTransacciones after successful update
        await fetchTransacciones();
        alert("Transacción actualizada correctamente");
      } catch (error) {
        console.error("Error al actualizar la transacción:", error);
        alert("Error al actualizar la transacción.");
      }
    } else {
      console.error("No se encontró la transacción seleccionada.");
    }

    setShowModal(false);
  };

  const handleTransaccionChange = (e) => {
    setSelectedTransaccion(e.target.value);
  };

  // Obtener el total de la transacción seleccionada
  const transaccionSeleccionada = transacciones.find(
    (transaccion) => transaccion.id === selectedTransaccion
  );
  const totalTransaccion = transaccionSeleccionada
    ? transaccionSeleccionada.total
    : 0;

  return (
    <div className="pedidos-container">
      <div className="botones">
        <div className="selectTransaccionPedido">
          <select
            name="transaccionSelect"
            id="transaccionSelect"
            onChange={handleTransaccionChange}
            value={selectedTransaccion || ""}
          >
            <option value="">Seleccione una transacción</option>
            {transacciones.map((transaccion) => (
              <option key={transaccion.id} value={transaccion.id}>
                Transacción {transaccion.id}
              </option>
            ))}
          </select>
        </div>

        <button
          onClick={handleRequest}
          className="dispatch-buttonpedido"
          disabled={!selectedTransaccion}
        >
          Despachar
        </button>
      </div>

      <div className="pedidos-grid">
        {pedidos.map((pedido) => (
          <div key={pedido.id} className="pedido-card">
            <h3>Pedido #{pedido.id}</h3>
            <hr className="divider" />
            <p>
              <strong>Cantidad:</strong> {pedido.cantidad}
            </p>
            <p>
              <strong>Insumo:</strong>{" "}
              {pedido.insumo?.nombre || "Insumo no disponible"}
            </p>
            <p>
              <strong>Transacción:</strong>{" "}
              {pedido.transaccion?.id || "ID de transacción no disponible"}
            </p>
            <p>
              <strong>Estado:</strong>{" "}
              {pedido.estadoAprobacion?.id === 2
                ? "Ya despachado"
                : "Pendiente"}
            </p>
            <p>
              <strong>Precio Unitario:</strong> $
              {pedido.insumo?.valorunitario || "0"}
            </p>
            <p>
              <strong>Total:</strong> $
              {(pedido.cantidad * (pedido.insumo?.valorunitario || 0)).toFixed(
                2
              )}
            </p>
          </div>
        ))}
      </div>

      {showModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>Resumen de Despacho</h2>
            <h3>Total de la Transacción:</h3>
            <p>${totalTransaccion.toFixed(2)}</p>
            <button onClick={closeModal}>Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Pedido;
