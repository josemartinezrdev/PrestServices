import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import pedidoIcon from "../../../public/insumos-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import Pedido from "./Pedido";
import Profile from "../cliente/Profile";

export default function Proveedor() {
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
              alt="Pedido App Logo"
              className="logo"
            />
          </div>
        );
      case "pedido":
        return <Pedido />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Pedido App Logo"
              className="logo-proveedor"
            />
          </div>
        );
    }
  };

  return (
    <div className="proveedor">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>MENÚ PROVEEDOR</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("pedido")}>
            <img src={pedidoIcon} alt="Pedido" /> Pedidos
          </button>
          <button onClick={() => handleItemClick("profile")}>
            <img src={profileIcon} alt="Profile" /> Perfil
          </button>
        </nav>
        <button className="logout-button" onClick={handleLogout}>
          <img src={logoutIcon} alt="Log Out" /> Salir
        </button>
      </div>
      <main>
        <div className="home">{renderForm()}</div>
      </main>
    </div>
  );
}
