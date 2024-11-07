import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import suppliers from "../../../public/providers-icon.svg";
import suplies from "../../../public/insumos-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import Suppliers from "./Suppliers";
import Suplies from "./Suplies";
import Profile from "../cliente/Profile";

export default function JefeBodega() {
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
              alt="Services App Logo"
              className="logo"
            />
          </div>
        );
      case "suppliers":
        return <Suppliers />;
      case "suplies":
        return <Suplies />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Services App Logo"
              className="logo-jefebodega"
            />
          </div>
        );
    }
  };

  return (
    <div className="jefebodega">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>JEFE DE BODEGA</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("suppliers")}>
            <img src={suppliers} alt="Suppliers" /> Suppliers
          </button>
          <button onClick={() => handleItemClick("suplies")}>
            <img src={suplies} alt="Suplies" /> Suplies
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
