package com.serviceback.serviceback.infrastructure.repositories.pedido;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceback.serviceback.application.services.IPedido;
import com.serviceback.serviceback.domain.entities.Pedido;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

@Service
public class PedidoImp implements IPedido {
    @Autowired
    private PedidoRepository detalletransaccionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Pedido> listByTransaccion(int id) {
        List<Pedido> detalletransaccions = detalletransaccionRepository.listByTransaccion(id);
        if (detalletransaccions.isEmpty()) {
            throw new GlobalExceptions("No se encontraron pedidos.");
        }
        return detalletransaccions;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Pedido> findAll() {
        List<Pedido> detalletransaccions = detalletransaccionRepository.findAll();
        if (detalletransaccions.isEmpty()) {
            throw new GlobalExceptions("No se encontraron pedidos.");
        }
        return detalletransaccions;
    }

    @Transactional(readOnly = true)
    @Override
    public Pedido findById(int id) throws GlobalExceptions {
        return detalletransaccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El pedido que intentas buscar no existe"));
    }

    @Transactional(rollbackFor = GlobalExceptions.class)
    @Override
    public Pedido save(Pedido detalletransaccion) throws GlobalExceptions {
        if (detalletransaccion == null) {
            throw new GlobalExceptions("El valor a guardar no puede ser nulo");
        }
        return detalletransaccionRepository.save(detalletransaccion);
    }

    @Transactional
    @Override
    public Pedido update(int id, Pedido newDetalleTransaccion) throws GlobalExceptions {
        Pedido detalletransaccion = detalletransaccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El pedido que intentas actualizar no existe"));
        detalletransaccion.setCantidad(newDetalleTransaccion.getCantidad());
        detalletransaccion.setInsumo(newDetalleTransaccion.getInsumo());
        detalletransaccion.setTransaccion(newDetalleTransaccion.getTransaccion());
        detalletransaccion.setEstadoAprobacion(newDetalleTransaccion.getEstadoAprobacion());
        return detalletransaccionRepository.save(detalletransaccion);
    }

    @Transactional
    @Override
    public Pedido delete(int id) throws GlobalExceptions {
        Pedido detalletransaccion = detalletransaccionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptions("El pedido que intentas eliminar no existe"));
        detalletransaccionRepository.delete(detalletransaccion);
        return detalletransaccion;
    }

    @Override
    public List<Pedido> findByTransaccionId(int transaccionId) {
        return detalletransaccionRepository.findByTransaccionId(transaccionId);
    }
}
