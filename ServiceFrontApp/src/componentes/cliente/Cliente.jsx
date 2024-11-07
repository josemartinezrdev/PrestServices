import { useState } from "react";
import { useNavigate } from "react-router-dom";
import homeIcon from "../../../public/home-icon.svg";
import servicesIcon from "../../../public/list-services-icon.svg";
import approvalsIcon from "../../../public/approvals-icon.svg";
import purchasesIcon from "../../../public/shopping-icon.svg";
import profileIcon from "../../../public/profile-icon.svg";
import logoutIcon from "../../../public/logout-icon.svg";
import Services from "./Services";
import Approvals from "./Approvals";
import Purchases from "./Purchases";
import Profile from "./Profile";

export default function Cliente() {
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
              className="logo-cliente"
            />
          </div>
        );
      case "services":
        return <Services />;
      case "approvals":
        return <Approvals />;
      case "purchases":
        return <Purchases />;
      case "profile":
        return <Profile />;
      default:
        return (
          <div className="home-logo">
            <img
              src="/public/logo.svg"
              alt="Services App Logo"
              className="logo"
            />
          </div>
        );
    }
  };

  return (
    <div className="cliente">
      <div className={`menu ${isOpen ? "open" : ""}`}>
        <button className="menu-toggle" onClick={toggleMenu}>
          {isOpen ? "X" : "☰"}
        </button>
        <nav className="menu-items">
          <h3>MENÚ CLIENTE</h3>
          <button onClick={() => handleItemClick("home")}>
            <img src={homeIcon} alt="Home" /> Home
          </button>
          <button onClick={() => handleItemClick("services")}>
            <img src={servicesIcon} alt="Services" /> Services
          </button>
          <button onClick={() => handleItemClick("approvals")}>
            <img src={approvalsIcon} alt="Approvals" /> Approvals
          </button>
          <button onClick={() => handleItemClick("purchases")}>
            <img src={purchasesIcon} alt="Purchases" /> Purchases
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