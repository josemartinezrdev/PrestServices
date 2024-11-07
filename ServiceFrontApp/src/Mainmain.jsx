// src/assets/components/Main.js
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import App from "./componentes/inicio/App";
import JefeBodega from "./componentes/jefeBodega/JefeBodega";
import AuxBodega from "./componentes/auxBodega/AuxBodega";
import Seguimiento from "./componentes/seguimiento/seguimiento";
import Marqueting from "./componentes/marketing/Marqueting";
import JefeCompras from "./componentes/jefeCompras/JefeCompras";
import Proveedor from "./componentes/proveedor/Proveedor";
import Profesional from "./componentes/profesional/Profesional";
import RecursoH from "./componentes/recursosh/RecursoH";
import Cliente from "./componentes/cliente/Cliente";
import Admin from "./componentes/admin/Admin"

function Mainmain() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/cliente" element={<Cliente />} />
        <Route path="/jefeBodega" element={<JefeBodega />} />
        <Route path="/auxBodega" element={<AuxBodega />} />
        <Route path="/seguimiento" element={<Seguimiento />} />
        <Route path="/marketing" element={<Marqueting />} />
        <Route path="/jefecompras" element={<JefeCompras />} />
        <Route path="/proveedor" element={<Proveedor />} />
        <Route path="/profesional" element={<Profesional />} />
        <Route path="/recursoh" element={<RecursoH />} />
        <Route path="/admin" element={<Admin />} />

      </Routes>
    </Router>
  );
}

export default Mainmain;
