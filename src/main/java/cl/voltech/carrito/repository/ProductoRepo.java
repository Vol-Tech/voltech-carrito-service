package cl.voltech.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.voltech.carrito.model.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
}