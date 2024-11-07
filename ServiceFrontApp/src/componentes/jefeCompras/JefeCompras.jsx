import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import suppliesIcon from "../../../public/insumos-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import Profile from "../cliente/Profile";
import Supplies from "./Supplies";

export default function JefeCompras() {
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
              alt="Supplies App Logo"
              className="logo-jefecompras"
            />
          </div>
        );
      case "supplies":
        return <Supplies />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Supplies App Logo"
              className="logo"
            />
          </div>
        );
    }
  };

  return (
    <div className="jefecompras">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>JEFE DE COMPRAS</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("supplies")}>
            <img src={suppliesIcon} alt="Supplies" /> Supplies
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
