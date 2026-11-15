package cl.voltech.carrito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.voltech.carrito.dto.AgregarItemRequest;
import cl.voltech.carrito.dto.CambiarCantidadRequest;
import cl.voltech.carrito.dto.CarritoItemResponse;
import cl.voltech.carrito.dto.CarritoResponse;
import cl.voltech.carrito.exception.ResourceNotFoundException;
import cl.voltech.carrito.exception.StockInsuficienteException;
import cl.voltech.carrito.mapper.CarritoMapper;
import cl.voltech.carrito.model.CarritoItem;
import cl.voltech.carrito.model.Producto;
import cl.voltech.carrito.repository.CarritoItemRepo;
import cl.voltech.carrito.repository.ProductoRepo;

@Service
public class CarritoService {
  private final CarritoItemRepo carritoItemRepo;
  private final ProductoRepo productoRepo;

  public CarritoService(CarritoItemRepo carritoItemRepo, ProductoRepo productoRepo) {
    this.carritoItemRepo = carritoItemRepo;
    this.productoRepo = productoRepo;
  }

  public CarritoResponse obtenerCarrito(int usuarioId) {
    List<CarritoItem> items = carritoItemRepo.findByUsuarioId(usuarioId);
    List<CarritoItemResponse> respuestas = new ArrayList<>();
    int total = 0;
    int cantidadTotal = 0;

    for (CarritoItem item : items) {
      respuestas.add(CarritoMapper.toResponse(item));
      total += item.getCantidad();
    }
    return new CarritoResponse(usuarioId, respuestas, total, cantidadTotal);
  }
}