// App.js
import React, { useState } from "react";
import "./App.css";
import Login from "./login";
import Signup from "./Singup";

export default function App() {
  const [activeForm, setActiveForm] = useState("none");

  const renderForm = () => {
    switch (activeForm) {
      case "login":
        return <Login />;
      case "signup":
        return <Signup />;
      default:
        return (
          <div className="about-us">
            <h2>ABOUT US</h2>
            <div className="about">
              <p>
                 PrestService
                está desarrollando un sistema integral para centralizar y
                optimizar la gestión de sus operaciones en múltiples sucursales.
                Este aplicativo permitirá la administración de servicios,
                personal, inventario y órdenes de trabajo, facilitando la
                coordinación y el control en tiempo real. Incluirá un sistema de
                aprobación para garantizar que todos los servicios cumplan con
                los estándares de calidad. Con esta herramienta, PrestService
                busca mejorar la eficiencia operativa, reducir costos y aumentar
                la satisfacción del cliente.
              </p>
            </div>
          </div>
        );
    }
  };

  return (
    <div className="app">
      <div className="left-side">
        <img src="/public/logo.svg" alt="Services App Logo" className="logo" />
      </div>
      <div className="right-side">
        <div className="top-buttons">
          <button
            className="btn-uni"
            id="login"
            onClick={() => setActiveForm("login")}
          >
            Log In
          </button>
          <button
            className="btn-uni"
            id="singup"
            onClick={() => setActiveForm("signup")}
          >
            Sign Up
          </button>
        </div>
        <div className="content-right">{renderForm()}</div>
        <div className="empty"></div>
      </div>
    </div>
  );
}
