import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

import "./Login.css";

export default function Login() {
  const userToken = localStorage.getItem("userToken");
  const navigate = useNavigate();
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setCredentials((prevCredentials) => ({
      ...prevCredentials,
      [id]: value,
    }));
  };

  const login = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(credentials),
      });

      if (!response.ok) {
        const errorData = await response.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("En el login");
      }

      const data = await response.json();
      const decodedToken = jwtDecode(data.jwt);
      console.log(data.jwt);

      const responsePersona = await fetch(
        `/api/personas/byusername/${decodedToken.sub}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (!responsePersona.ok) {
        const errorData = await responsePersona.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Encontrando la persona relacionada con el usuario");
      }

      const userInfo = await responsePersona.json();

      localStorage.setItem("userInfo", JSON.stringify(userInfo));
      localStorage.setItem("userToken", data.jwt);

      switch (decodedToken.role) {
        case "ADMINISTRADOR":
          navigate("/admin");
          break;
        case "AUX_BODEGA":
          navigate("/auxBodega");
          break;
        case "CLIENTE":
          navigate("/cliente");
          break;
        case "EMPLEADO":
          console.error("No hay pagina de inicio para los empleados");
          break;
        case "JEFE_BODEGA":
          navigate("/jefeBodega");
          break;
        case "JEFE_COMPRAS":
          navigate("/jefecompras");
          break;
        case "JEFE_MARKETING":
          navigate("/marketing");
          break;
        case "JEFE_SISTEMAS":
          console.error("No hay pagina de incio para los jefes de sistemas");
          break;
        case "PERSONAL_SEGUIMIENTO":
          navigate("/seguimiento");
          break;
        case "PROFESIONAL":
          navigate("/profesional");
          break;
        case "PROVEEDOR":
          navigate("/proveedor");
          break;
        case "RECURSOS_HUMANOS":
          navigate("/recursoh");
          break;
        default:
          break;
      }
    } catch (error) {
      console.error("Error de sesion: ", error);
    }
  };

  return (
    <div className="content-log">
      <form className="form" onSubmit={login}>
        <p className="title">Login </p>
        <p className="message">
          Lorem ipsum dolor sit amet consectetur adipisicing elit.{" "}
        </p>

        <label>
          <input
            onChange={handleChange}
            value={credentials.username}
            id="username"
            className="input"
            type="UserName"
            placeholder=""
            required
          />
          <span>UserName</span>
        </label>

        <label>
          <input
            onChange={handleChange}
            value={credentials.password}
            id="password"
            className="input"
            type="password"
            placeholder=""
            required
          />
          <span>Password</span>
        </label>
        <button className="btn-uni" id="submit">
          Submit
        </button>
        <p>Lorem ipsum dolor sit amet.</p>
      </form>
    </div>
  );
}
