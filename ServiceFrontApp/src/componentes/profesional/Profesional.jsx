import { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import asignacionIcon from "../../../public/solicitud-icon.svg";
import aprovacionIcon from "../../../public/insumos-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import Asignacion from "./Asignacion";
import Aprovacion from "./Aprovaciones";

import Profile from "../cliente/Profile";

export default function Profesional() {
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
      case "asignacion":
        return <Asignacion />;
      case "aprovacion":
        return <Aprovacion />;

      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Services App Logo"
              className="logo-profesional"
            />
          </div>
        );
    }
  };

  return (
    <div className="profesional">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>PROFESIONALES DE SERVICIOS</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("asignacion")}>
            <img src={asignacionIcon} alt="asignacion" /> Asignaciones
          </button>
          <button onClick={() => handleItemClick("aprovacion")}>
            <img src={aprovacionIcon} alt="aprovacion" /> Aprovaciones
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
