package cl.voltech.carrito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.voltech.carrito.model.CarritoItem;

@Repository
public interface CarritoItemRepo extends JpaRepository<CarritoItem, Integer> {
    List<CarritoItem> findByUsuarioId(int usuarioId);
    Optional<CarritoItem> findByUsuarioIdAndProductoId(int usuarioId, int productoId);
}