/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router-dom";
import "./Profile.css";

const Profile = () => {
  const [profile, setProfile] = useState(null);
  const userToken = localStorage.getItem("userToken");
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));
  const [tipoPersona, setTipoPersona] = useState("1");
  const [lstTypes, setLstTypes] = useState([]);
  const [loadingTypes, setLoadingTypes] = useState(true);
  const [errorTypes, setErrorTypes] = useState(null);
  const decodedToken = jwtDecode(userToken);

  const [personaUpdate, setPersonaUpdate] = useState({
    nombre: "",
    apellido: "",
    tipoPersona: {
      id: tipoPersona,
    },
  });

  const navigate = useNavigate();

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

  const eliminarCuenta = async () => {
    try {
      const response = await fetch(`/api/personas/${profile.nrodoc}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      });
      if (!response.ok) {
        const errorData = await response.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error eliminando persona");
      }
      const responseUser = await fetch(`/api/users/${userInfo.user.username}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      });
      if (!responseUser.ok) {
        const errorData = await responseUser.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error eliminando usuario");
      }
      navigate("/");
    } catch (error) {
      console.error(error);
    }
  };

  const findProfile = async () => {
    try {
      const response = await fetch(
        `/api/personas/byusername/${decodedToken.sub}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (!response.ok) {
        const errorData = await response.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        throw new Error("Error buscando la persona relacionada con el usuario");
      }
      const data = await response.json();
      setProfile(data);
      setPersonaUpdate({
        nombre: data.nombre,
        apellido: data.apellido,
        tipoPersona: {
          id: data.tipoPersona.id,
        },
      });
      setTipoPersona(data.tipoPersona.id);
    } catch (error) {
      console.error("Error buscando:", error.message);
    }
  };
  useEffect(() => {
    findProfile();
    findAllTypes();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "tipoPersona") {
      setTipoPersona(value);
      setPersonaUpdate((prev) => ({
        ...prev,
        tipoPersona: {
          id: value,
        },
      }));
    } else {
      setPersonaUpdate((prev) => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  const updatePersona = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`/api/personas/${profile.nrodoc}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(personaUpdate),
      });
      if (!response.ok) {
        const errorData = await response.json();
        alert(
          `Error: ${errorData.error}\nMensaje: ${errorData.message}\nEstado: ${errorData.status}`
        );
      } else {
        findProfile();
        findAllTypes();
      }
    } catch (error) {
      console.error(error);
    }
  };

  if (!profile) {
    return <div>Loading...</div>;
  }

  return (
    <div className="profile-container">
      <div className="profile-info">
        <h2>Nro Doc: {profile.nrodoc}</h2>
        <p>User Name: {userInfo.user.username}</p>
        <p>Nombre: {profile.nombre}</p>
        <p>Last Name: {profile.apellido}</p>
        <p>Fecha Registro: {profile.fecharegistro}</p>
        <p>Tipo Persona: {profile.tipoPersona.id}</p>
        <button onClick={eliminarCuenta} className="update-account">
          Delete Account
        </button>
      </div>
      <form className="profile-form" onSubmit={updatePersona}>
        <div className="form-group">
          <label htmlFor="type">Type</label>
          {loadingTypes ? (
            <p>Cargando tipos de persona...</p>
          ) : errorTypes ? (
            <p>Error: {errorTypes}</p>
          ) : (
            <select
              id="tipoPersona"
              name="tipoPersona"
              value={tipoPersona}
              onChange={handleInputChange}
              className="form-control"
            >
              {lstTypes.length > 0 ? (
                lstTypes.map((tipo) => (
                  <option key={tipo.id} value={tipo.id}>
                    {tipo.nombre}
                  </option>
                ))
              ) : (
                <option disabled>No hay tipos de personas disponibles</option>
              )}
            </select>
          )}
        </div>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            id="name"
            name="nombre"
            value={personaUpdate.nombre}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="lastName">Last Name</label>
          <input
            type="text"
            id="lastName"
            name="apellido"
            value={personaUpdate.apellido}
            onChange={handleInputChange}
          />
        </div>
        <button type="submit" className="update">
          Update
        </button>
      </form>
    </div>
  );
};

export default Profile;
