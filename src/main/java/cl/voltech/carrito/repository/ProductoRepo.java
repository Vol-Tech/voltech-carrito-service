package cl.voltech.carrito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.voltech.carrito.model.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
    List<Producto> findByUsuarioId(int usuarioId);
    Optional<Producto> findByUsuarioIdAndProductoId(int usuarioId, int productoId);
}