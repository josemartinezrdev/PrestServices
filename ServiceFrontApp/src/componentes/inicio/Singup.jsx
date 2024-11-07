import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import "./Singup.css";

export default function Signup() {
  const [tipoPersona, setTipoPersona] = useState("1");
  const navigate = useNavigate();
  const [rol, setRol] = useState("CLIENTE");
  const [persona, setPersona] = useState({
    nrodoc: "",
    nombre: "",
    apellido: "",
    user: { username: "" },
  });
  const [user, setUser] = useState({
    username: "",
    password: "",
    repeatedPassword: "",
    rol: rol,
  });
  const [lstRoles, setLstRoles] = useState([]);
  const [lstTypes, setLstTypes] = useState([]);
  const [loadingRoles, setLoadingRoles] = useState(true);
  const [loadingTypes, setLoadingTypes] = useState(true);
  const [errorRoles, setErrorRoles] = useState(null);
  const [errorTypes, setErrorTypes] = useState(null);

  useEffect(() => {
    const findAllRoles = async () => {
      try {
        const response = await fetch("/api/roles");
        if (!response.ok) throw new Error("Error en la respuesta de roles");
        const data = await response.json();

        const filteredRoles = data.filter(
          (role) => role.nombre === "CLIENTE" || role.nombre === "PROFESIONAL"
        );
        setLstRoles(filteredRoles);
        setRol(filteredRoles.length > 0 ? filteredRoles[0].nombre : "CLIENTE");
      } catch (error) {
        setErrorRoles(error.message);
      } finally {
        setLoadingRoles(false);
      }
    };

    const findAllTypes = async () => {
      try {
        const response = await fetch("/api/tipopersonas");
        if (!response.ok)
          throw new Error("Error en la respuesta de tipos de personas");
        const data = await response.json();
        setLstTypes(data);
      } catch (error) {
        setErrorTypes(error.message);
      } finally {
        setLoadingTypes(false);
      }
    };

    findAllTypes();
    findAllRoles();
  }, []);

  const handleChange = (e) => {
    const { id, value } = e.target;

    if (id === "username" || id === "password" || id === "repeatedPassword") {
      setUser((prevUser) => ({
        ...prevUser,
        [id]: value,
      }));

      if (id === "username") {
        setPersona((prevPersona) => ({
          ...prevPersona,
          user: { username: value },
        }));
      }
    } else {
      setPersona((prevPersona) => ({
        ...prevPersona,
        [id]: value,
      }));
    }
  };

  const handleRoleChange = (e) => {
    const selectedRole = e.target.value;
    setRol(selectedRole);
  };

  const signup = async (e) => {
    e.preventDefault();

    if (user.password !== user.repeatedPassword) {
      alert("Las contraseñas no coinciden.");
      return;
    }

    try {
      const responseUser = await fetch("/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...user,
          rol: rol,
          nombre: persona.nombre,
        }),
      });

      if (!responseUser.ok) {
        const errorData = await responseUser.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        return;
      }

      const dataUser = await responseUser.json();

      const responsePerson = await fetch("/api/personas", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nrodoc: persona.nrodoc,
          nombre: persona.nombre,
          apellido: persona.apellido,
          user: { username: dataUser.username },
          tipoPersona: { id: tipoPersona },
        }),
      });

      if (!responsePerson.ok) {
        const errorData = await responsePerson.text();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        return;
      }

      const dataPerson = await responsePerson.json();
      localStorage.setItem("userInfo", JSON.stringify(dataPerson));

      const decodedToken = jwtDecode(dataUser.jwt);
      console.log(dataUser.jwt);
      localStorage.setItem("userToken", dataUser.jwt);
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
          alert("Sesion invalida");
          break;
      }
    } catch (error) {
      console.error("Error registrando user o persona: ", error);
    }
  };

  return (
    <div className="content-singup">
      <form className="form" onSubmit={signup}>
        <p className="title">Registro de Usuario</p>
        <p className="message">Completa todos los campos para registrarte</p>

        <div className="form-columns">
          <div className="form-group">
            <label htmlFor="tipoPersona">
              {loadingTypes ? (
                <p>Cargando tipos de persona...</p>
              ) : errorTypes ? (
                <p>Error: {errorTypes}</p>
              ) : (
                <>
                  <select
                    id="tipoPersona"
                    value={tipoPersona}
                    onChange={(e) => setTipoPersona(e.target.value)}
                    className="form-control input"
                  >
                    {lstTypes.length > 0 ? (
                      lstTypes.map((tipo) => (
                        <option key={tipo.id} value={tipo.id}>
                          {tipo.nombre}
                        </option>
                      ))
                    ) : (
                      <option disabled>
                        No hay tipos de personas disponibles
                      </option>
                    )}
                  </select>
                  <span>Tipo de Persona</span>
                </>
              )}
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="nrodoc">
              <input
                id="nrodoc"
                type="text"
                required
                value={persona.nrodoc}
                onChange={handleChange}
                placeholder=" "
                className="input"
              />
              <span>Nro. de Documento</span>
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="nombre">
              <input
                id="nombre"
                type="text"
                required
                value={persona.nombre}
                onChange={handleChange}
                placeholder=" "
                className="input"
              />
              <span>Nombre de la Persona</span>
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="apellido">
              <input
                id="apellido"
                type="text"
                required
                value={persona.apellido}
                onChange={handleChange}
                placeholder=" "
                className="input"
              />
              <span>Apellidos</span>
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="username">
              <input
                id="username"
                type="text"
                required
                value={user.username}
                onChange={handleChange}
                placeholder=" "
                className="input"
              />
              <span>Usuario</span>
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="password">
              <input
                id="password"
                type="password"
                required
                value={user.password}
                onChange={handleChange}
                placeholder=" "
                className="input"
              />
              <span>Contraseña</span>
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="repeatedPassword">
              <input
                id="repeatedPassword"
                type="password"
                required
                value={user.repeatedPassword}
                onChange={handleChange}
                placeholder=" "
                className="input"
              />
              <span>Repetir Contraseña</span>
            </label>
          </div>

          <div className="form-group">
            <label htmlFor="rol">
              {loadingRoles ? (
                <p>Cargando roles...</p>
              ) : errorRoles ? (
                <p>Error: {errorRoles}</p>
              ) : (
                <>
                  <select
                    id="rol"
                    value={rol}
                    onChange={handleRoleChange}
                    className="form-control input"
                  >
                    {lstRoles.map((role) => (
                      <option key={role.id} value={role.nombre}>
                        {role.nombre}
                      </option>
                    ))}
                  </select>
                  <span>Rol</span>
                </>
              )}
            </label>
          </div>
        </div>

        <button type="submit" className="btn btn-primary submit">
          Registrarse
        </button>
      </form>
    </div>
  );
}
