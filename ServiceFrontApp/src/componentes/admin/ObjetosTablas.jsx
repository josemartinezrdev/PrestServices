// ObjetosTablas.js
const tablas = {
  aprobacionservicios: {
    id: null,
    hallazgo: "",
    solucion: "",
    cliente: {
      nrodoc: null,
    },
    estadoaprobacion: {
      id: null,
    },
    ordenservicio: {
      norden: null,
    },
    servicio: {
      id: null,
    },
  },
  ciudades: {
    id: null,
    nombre: "",
    region: {
      id: null,
    },
  },
  detalleordenes: {
    id: null,
    ordenservicio: {
      norden: null,
    },
    servicio: {
      id: null,
    },
  },
  detalleordentrabajos: {
    id: null,
    fecha: null,
    idempleado: {
      nrodoc: null,
    },
    estado: {
      id: null,
    },
    ordentrabajo: {
      id: null,
    },
    servicio: {
      id: null,
    },
  },
  direcciones: {
    id: null,
    calle: "",
    carrera: "",
    ciudad: {
      id: null,
    },
  },
  emailpersonas: {
    id: null,
    email: "",
    persona: {
      nrodoc: "",
    },
    tipoemail: {
      id: null,
    },
  },
  empresas: {
    id: null,
    nombre: "",
    tipoempresa: {
      id: null,
    },
  },
  estadoaprobaciones: {
    id: null,
    nombre: "",
  },
  estadoordenes: {
    id: null,
    nombre: "",
  },
  estadoserordenes: {
    id: null,
    nombre: "",
  },
  insumos: {
    id: null,
    codinterno: "",
    nombre: "",
    stock: null,
    stockmax: null,
    stockmin: null,
    valorunitario: null,
  },
  ordenservicios: {
    norden: null,
    fecha: null,
    motivo: "",
    idcliente: {
      nrodoc: "",
    },
    idempleado: {
      nrodoc: "",
    },
    estadoorden: {
      id: null,
    },
  },
  ordentrabajos: {
    id: null,
    fechaasignacion: null,
    horaasignacion: null,
    nrotrabajo: "",
    idempleado: {
      nrodoc: "",
    },
    orden: {
      norden: "",
    },
  },
  paises: {
    id: null,
    nombre: "",
  },
  pedidos: {
    id: null,
    cantidad: null,
    estadoaprobacion: {
      id: null,
    },
    insumo: {
      id: null,
    },
    transaccion: {
      id: null,
    },
  },
  personainsumos: {
    insumo: {
      id: null,
    },
    servicio: {
      id: null,
    },
    persona: {
      nrodoc: "",
    },
  },
  personas: {
    nrodoc: "",
    apellido: "",
    fecharegistro: null,
    nombre: "",
    sucursal: {
      id: null,
    },
    tipopersona: {
      id: null,
    },
    user: {
      id: null,
    },
  },
  regiones: {
    id: null,
    nombre: "",
    pais: {
      id: null,
    },
  },
  roles: {
    id: null,
    nombre: "",
  },
  servicioinsumos: {
    insumo: {
      id: null,
    },
    servicio: {
      id: null,
    },
    cantidadinsumo: null,
    valortotal: null,
  },
  servicios: {
    id: null,
    descripcion: "",
    nombre: "",
    requiereinsumo: false,
  },
  sucursales: {
    id: null,
    fechacreacion: null,
    nit: "",
    nombre: "",
    direccion: {
      id: null,
    },
    empresa: {
      id: null,
    },
  },
  sucursalservicio: {
    servicio: {
      id: null,
    },
    sucursal: {
      id: null,
    },
    valorservicio: null,
  },
  telpersonas: {
    id: null,
    nro: "",
    persona: {
      nrodoc: "",
    },
    tipotelefono: {
      id: null,
    },
  },
  tipoemails: {
    id: null,
    nombre: "",
  },
  tipopersonas: {
    id: null,
    nombre: "",
  },
  tipotelefonos: {
    id: null,
    nombre: "",
  },
  transacciones: {
    id: null,
    descripcion: "",
  },
  usuarios: {
    id: null,
    username: "",
    password: "",
  },
  tesucursales: {
    id: null,
    nombre: "",
    direccion: {
      id: null,
    },
    empresa: {
      id: null,
    },
  },
  tipoempresas: {
    id: null,
    nombre: "",
  },
  userroles: {
    rol: {
      id: null,
    },
    user: {
      id: null,
    },
  },
  users: {
    id: null,
    enable: true,
    nombre: "",
    password: "",
  },
  // Agrega más objetos aquí
};

export default tablas;
