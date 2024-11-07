import { useState, useEffect } from "react";
import "./Services.css";

const Services = () => {
  const [services, setServices] = useState([]);
  const [requestedServices, setRequestedServices] = useState({});
  const userToken = localStorage.getItem("userToken");
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));
  const [ordenServicio, setOrdenServicio] = useState({
    motivo: "",
    cliente: {
      nrodoc: userInfo.nrodoc,
    },
    empleado: {
      nrodoc: "bot",
    },
    estadoOrden: {
      id: 1,
    },
  });
  const [selectedService, setSelectedService] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [supplies, setSupplies] = useState([]);

  useEffect(() => {
    const findAll = async () => {
      try {
        const response = await fetch("/api/servicios", {
          method: "GET",
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
          return;
        }

        const data = await response.json();
        setServices(data);
      } catch (error) {
        console.error("Error buscando: " + error);
      }
    };

    findAll();
  }, [userToken]);

  const generarOrdenServicio = async (id) => {
    console.log(ordenServicio);
    try {
      const responseOrdenServicio = await fetch(`/api/ordenservicios`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${userToken}`,
        },
        body: JSON.stringify(ordenServicio),
      });

      if (!responseOrdenServicio.ok) {
        const errorData = await responseOrdenServicio.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        return;
      }

      const dataOrdenServicio = await responseOrdenServicio.json();
      console.log(dataOrdenServicio);
      console.log(dataOrdenServicio.norden);

      const newDetalleOrden = {
        ordenServicio: {
          norden: dataOrdenServicio.norden,
        },
        servicio: {
          id: id,
        },
      };

      console.log(newDetalleOrden);

      const responseServicioInsumo = await fetch(`/api/detalleordenes`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${userToken}`,
        },
        body: JSON.stringify(newDetalleOrden),
      });

      if (!responseServicioInsumo.ok) {
        const errorData = await responseServicioInsumo.json();
        alert(
          `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
        );
        return;
      }
    } catch (error) {
      console.error("Error haciendo la petición: ", error);
    }
  };

  const handleRequest = (servicio) => {
    if (!requestedServices[servicio.id]) {
      setRequestedServices((prev) => ({
        ...prev,
        [servicio.id]: true,
      }));
    }
    generarOrdenServicio(servicio.id);
    createTransaccion(servicio);
  };

  const createTransaccion = async (servicio) => {
    if (!servicio.requiereinsumo) {
      console.log("No necesito insumos");
      return;
    }
    console.log("Necesito insumos");
    const responseIntermedia = await fetch("/api/servicioinsumos", {
      method: "GET",
      headers: {
        Authorization: `Bearer ${userToken}`,
        "Content-Type": "application/json",
      },
    });
    if (!responseIntermedia.ok) {
      const errorData = await responseIntermedia.json();
      alert(
        `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
      );
      return;
    }
    const dataIntermedia = await responseIntermedia.json();
    let total = 0;
    dataIntermedia.forEach(async (inter) => {
      if (inter.servicio.id === servicio.id) {
        total += inter.insumo.valorunitario * inter.cantidadinsumo;
      }
    });
    total += servicio.valorservicio;
    const trans = {
      total: total,
      estadoAprobacion: { id: 1 },
      persona: { nrodoc: userInfo.nrodoc },
      proveedor: { nrodoc: "bot" },
    };
    const responseTransaccion = await fetch("/api/transacciones", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${userToken}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(trans),
    });
    if (!responseTransaccion.ok) {
      const errorData = await responseTransaccion.json();
      alert(
        `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
      );
      return;
    }
    const dataTr = await responseTransaccion.json();

    dataIntermedia.forEach(async (inter) => {
      if (inter.servicio.id === servicio.id) {
        const pedido = {
          cantidad: inter.cantidadinsumo,
          estadoAprobacion: { id: 1 },
          insumo: { id: inter.insumo.id },
          transaccion: { id: dataTr.id },
        };

        const responsePedidos = await fetch("/api/pedidos", {
          method: "POST",
          headers: {
            Authorization: `Bearer ${userToken}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(pedido),
        });
        if (!responsePedidos.ok) {
          const errorData = await responsePedidos.json();
          alert(
            `${errorData.error}\n${errorData.message}\nEstado: ${errorData.status}`
          );
          return;
        }
      }
    });
  };

  const findInsumosServices = async (id) => {
    try {
      const response = await fetch(`/api/insumos/byservice/${id}`, {
        method: "GET",
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
        return;
      }

      const data = await response.json();
      console.log(data);
      setSupplies(data);
    } catch (error) {
      console.error("Error buscando: " + error);
    }
  };

  const handleViewSupplies = (service) => {
    if (service.requiereinsumo) {
      setSelectedService(service);
      findInsumosServices(service.id);
      setIsModalOpen(true);
    } else {
      alert("Este servicio no requiere insumos.");
    }
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setSelectedService(null);
    setSupplies([]);
  };

  return (
    <div className="services-container">
      {services.map((service) => (
        <div key={service.id} className="service-card">
          <h3>{service.nombre}</h3>
          <hr className="divider" />
          <p>{service.descripcion}</p>
          <p>Price: {service.valorservicio}</p>
          <div className="button-container">
            <button
              onClick={() => handleRequest(service)}
              className={requestedServices[service.id] ? "requested" : ""}
              disabled={requestedServices[service.id]}
            >
              {requestedServices[service.id] ? "Solicitado" : "Solicitar"}
            </button>
            <button
              onClick={() => handleViewSupplies(service)}
              className="view-supplies"
            >
              Ver insumos
            </button>
          </div>
        </div>
      ))}

      {isModalOpen && selectedService && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>Insumos para {selectedService.nombre}</h2>
            <ul>
              {supplies.length === 0 ? (
                <p>Cargando...</p>
              ) : (
                supplies.map((supply, index) => (
                  <li key={index}>
                    {supply.nombre}: {supply.valorunitario}
                  </li>
                ))
              )}
            </ul>
            <button onClick={closeModal}>Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Services;
