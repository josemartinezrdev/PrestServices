import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import TablesIcon from "../../../public/tareas-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import Tables from "./Tables";
import Profile from "../cliente/Profile";
import "./Admin.css"

export default function Admin() {
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
              alt="Tables App Logo"
              className="logo-marqueting"
            />
          </div>
        );
      case "Tables":
        return <Tables />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Tables App Logo"
              className="logo"
            />
          </div>
        );
    }
  };

  return (
    <div className="marqueting">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>MENÚ ADMINISTRADOR</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("Tables")}>
            <img src={TablesIcon} alt="Tables" /> Tables
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
        <div className="home-admin">{renderForm()}</div>
      </main>
    </div>
  );
}
