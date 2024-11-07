import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import orderServices from "../../../public/insumos-icon.svg";
import purchases from "../../../public/shopping-icon.svg";
import orderApprov from "../../../public/pedidos-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import OrderService from "./OrderService";
import OrderAprov from "./OrderAprov";
import Purchases from "./Purchas";
import Profile from "../cliente/Profile";

export default function AuxBodega() {
  const [activeForm, setActiveForm] = useState("home");
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const handleItemClick = (component) => {
    setIsOpen(false);
    setActiveForm(component);
  };

  const handleLogout = () => {
    setIsOpen(false);
    navigate("/");
  };

  const renderForm = () => {
    switch (activeForm) {
      case "home":
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="OrderService App Logo"
              className="logo"
            />
          </div>
        );
      case "orderservice":
        return <OrderService />;
      case "orderaprov":
        return <OrderAprov />;
      case "purchases":
        return <Purchases />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="OrderService App Logo"
              className="logo-auxbodega"
            />
          </div>
        );
    }
  };

  return (
    <div className="auxbodega">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
        <h3>Auxiliar de Bodega</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("orderservice")}>
            <img src={orderServices} alt="OrderService" /> OrderService
          </button>
          <button onClick={() => handleItemClick("orderaprov")}>
            <img src={purchases} alt="OrderAprov" /> OrderAprov
          </button>
          <button onClick={() => handleItemClick("purchases")}>
            <img src={orderApprov} alt="Purchases" /> Purchases
          </button>
          <button onClick={() => handleItemClick("profile")}>
            <img src={profileIcon} alt="Profile" /> Profile
          </button>
        </nav>
        <button className="logout-button" onClick={handleLogout}>
          <img src={logoutIcon} alt="Log Out" /> Log Out
        </button>
      </div>
      <main>
        <div className="home">{renderForm()}</div>
      </main>
    </div>
  );
}