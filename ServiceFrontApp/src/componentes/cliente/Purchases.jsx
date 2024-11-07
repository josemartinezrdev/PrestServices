/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from "react";
import "./Purchases.css";
import purchasesIcon from "../../../public/shopping-icon.svg";

const Purchases = () => {
  const [purchases, setPurchases] = useState([]);
  const [cart, setCart] = useState([]);
  const [showCartModalCliente, setShowCartModalCliente] = useState(false);
  const [quantities, setQuantities] = useState({});
  const userToken = localStorage.getItem("userToken");
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));

  const findAllInsumos = async () => {
    try {
      const responseInsumos = await fetch("/api/insumos", {
        method: "GET",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      });
      if (!responseInsumos.ok) {
        const errorData = await responseInsumos.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando");
      }
      const data = await responseInsumos.json();
      setPurchases(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    findAllInsumos();
    const savedCart = localStorage.getItem("cart");
    if (savedCart) {
      setCart(JSON.parse(savedCart));
    }
  }, []);

  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(cart));
  }, [cart]);

  const handleBuy = (purchase) => {
    const quantity = quantities[purchase.id] || 1;
    const purchaseWithQuantity = {
      ...purchase,
      quantity: parseInt(quantity, 10),
    };

    setCart((prevCart) => {
      const existingItemIndex = prevCart.findIndex(
        (item) => item.id === purchase.id
      );

      if (existingItemIndex !== -1) {
        return prevCart.map((item, index) =>
          index === existingItemIndex
            ? {
                ...item,
                quantity: item.quantity + purchaseWithQuantity.quantity,
              }
            : item
        );
      } else {
        return [...prevCart, purchaseWithQuantity];
      }
    });
  };

  const handleQuantityChange = (id, value) => {
    setQuantities({ ...quantities, [id]: value });
  };

  const handleOpenCartModalCliente = () => {
    setShowCartModalCliente(true);
  };

  const handleCloseCartModalCliente = () => {
    setShowCartModalCliente(false);
  };

  const handleRemoveFromCart = (id) => {
    setCart(cart.filter((item) => item.id !== id));
  };

  const handleCancelOrder = () => {
    setCart([]);
    setShowCartModalCliente(false);
  };

  const handlePlaceOrder = async () => {

    /// no olvides que transaccion ahora deves ponerle un proveedor por defecto 😘😘😘😘😘
    /// meno ps, por que me pones en vueltass 😢😽😽😽😽

    const transaccion = {
      total: 0,
      persona: { nrodoc: userInfo.nrodoc },
      estadoAprobacion: { id: 1 },
      proveedor: { nrodoc: "bot" }
    };
    if (cart.length === 0) {
      alert("No puedes enviar una transaccion vacia.");
    } else {
      console.log(cart);
      
      const total = cart.reduce((acc, item) => {
        return acc + item.valorunitario * item.quantity;
      }, 0);

      transaccion.total = total;
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
          throw new Error("Error añadiendo transacciones");
        }
        const dataTransaccion = await responseTransaccion.json();

        await Promise.all(
          cart.map(async (item) => {
            const pedido = {
              cantidad: item.quantity,
              insumo: { id: item.id },
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
              throw new Error("Error añadiendo pedidos");
            }
          })
        );
      } catch (error) {
        console.error(error);
      }
    }
    setCart([]);
    setShowCartModalCliente(false);
  };

  const calculateTotalItems = () => {
    return cart.reduce((total, item) => total + item.quantity, 0);
  };

  return (
    <div className="purchases-container">
      <div className="cart-icon-container" onClick={handleOpenCartModalCliente}>
        <img src={purchasesIcon} alt="Purchases" className="cart-icon-img" />
        <span className="cart-count">{calculateTotalItems() || 0}</span>
      </div>

      {purchases.map((purchase) => (
        <div key={purchase.id} className="purchase-card">
          <h3>{purchase.nombre}</h3>
          <p>Cod: {purchase.codinterno}</p>
          <p>Stock: {purchase.stock}</p>
          <p>Price: {purchase.valorunitario}</p>
          <div className="purchase-form">
            <input
              type="number"
              min="1"
              max={purchase.stock}
              defaultValue="1"
              onChange={(e) =>
                handleQuantityChange(purchase.id, e.target.value)
              }
              className="quantity-input"
            />
            <button className="CartBtn" onClick={() => handleBuy(purchase)}>
              <span className="IconContainer">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1em"
                  viewBox="0 0 576 512"
                  fill="rgb(17, 17, 17)"
                  className="cart"
                >
                  <path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"></path>
                </svg>
              </span>
              <p className="text">Add</p>
            </button>
          </div>
        </div>
      ))}

      {showCartModalCliente && (
        <div className="modalcliente-overlay">
          <div className="modalcliente-content">
            <h2>Carrito de compras</h2>
            <div className="cart-items">
              {cart.map((item) => (
                <div key={item.id} className="cart-item">
                  <p>
                    {/* <strong>Insumo:</strong> {item.nombre.length === 10 ? item.nombre : "Muy largo"} */}
                    <strong>Insumo:</strong> {item.nombre}
                  </p>
                  <p>
                    <strong>Cantidad:</strong> {item.quantity}
                  </p>
                  <button
                    onClick={() => handleRemoveFromCart(item.id)}
                    className="remove-item"
                  >
                    Eliminar
                  </button>
                </div>
              ))}
              {cart.length === 0 && <p>El carrito está vacío</p>}
            </div>
            <div className="cart-total">
              <p>
                <strong>Total de artículos:</strong> {calculateTotalItems()}
              </p>
            </div>
            <div className="cart-actions">
              <button onClick={handleCancelOrder} className="cancel-order">
                Cancelar
              </button>
              <button onClick={handlePlaceOrder} className="place-order">
                Hacer Pedido
              </button>
              <button
                onClick={handleCloseCartModalCliente}
                className="close-modalcliente"
              >
                Cerrar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Purchases;
