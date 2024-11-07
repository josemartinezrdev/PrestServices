import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import empleadoIcon from "../../../public/empleados-icon.svg";
import crearservicioIcon from "../../../public/list-services-icon.svg";
import asignarordenIcon from "../../../public/assignacion-icon.svg";
import mostrarserviciosIcon from "../../../public//tareas-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import CrearEmpleado from "./CrearEmpleado";
import CrearServicio from "./CrearServicio";
import AsignarOrden from "./AsignarOrden";
import MostrarServicios from "./MostrarServicios";
import Profile from "../cliente/Profile";

export default function RecursoH() {
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
              alt="Prest CrearServicio Logo"
              className="logo-recursoh"
            />
          </div>
        );
      case "crearempleado":
        return <CrearEmpleado />;
      case "crearservicio":
        return <CrearServicio />;
      case "asignarorden":
        return <AsignarOrden />;
      case "mostrarservicios":
        return <MostrarServicios />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Prest CrearServicio Logo"
              className="logo"
            />
          </div>
        );
    }
  };

  return (
    <div className="recursoh">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>RECURSOS HUMANOS</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("crearempleado")}>
            <img src={empleadoIcon} alt="CrearEmpleado" /> Asignar Empleado
          </button>
          <button onClick={() => handleItemClick("crearservicio")}>
            <img src={crearservicioIcon} alt="CrearServicio" /> Crear Servicio
          </button>
          <button onClick={() => handleItemClick("asignarorden")}>
            <img src={asignarordenIcon} alt="AsignarOrden" /> Asignar Orden
          </button>
          <button onClick={() => handleItemClick("mostrarservicios")}>
            <img src={mostrarserviciosIcon} alt="mostrarservicios" /> Mostrar Servicios
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