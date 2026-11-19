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
      total += item.getPrecio() * item.getCantidad();
      cantidadTotal += item.getCantidad();
    }

    return new CarritoResponse(usuarioId, respuestas, total, cantidadTotal);
  }

  public CarritoResponse agregarItem(AgregarItemRequest request) {
    Producto producto = buscarProducto(request.productoId());
    Optional<CarritoItem> existente = carritoItemRepo
        .findByUsuarioIdAndProductoId(request.usuarioId(), request.productoId());

    int cantidadActual = 0;
    if (existente.isPresent()) {
      cantidadActual = existente.get().getCantidad();
    }

    int nuevaCantidad = cantidadActual + request.cantidad();
    validarStock(producto, nuevaCantidad);

    if (existente.isPresent()) {
      CarritoItem item = existente.get();
      item.setCantidad(nuevaCantidad);
      carritoItemRepo.save(item);
    } else {
      carritoItemRepo.save(CarritoMapper.toModel(request, producto));
    }

    return obtenerCarrito(request.usuarioId());
  }

  public CarritoResponse cambiarCantidad(int id, CambiarCantidadRequest request) {
    CarritoItem item = buscarItem(id);
    Producto producto = buscarProducto(item.getProductoId());

    validarStock(producto, request.cantidad());

    item.setCantidad(request.cantidad());
    carritoItemRepo.save(item);

    return obtenerCarrito(item.getUsuarioId());
  }

  public CarritoResponse eliminarItem(int id) {
    CarritoItem item = buscarItem(id);
    carritoItemRepo.deleteById(id);

    return obtenerCarrito(item.getUsuarioId());
  }

  public void vaciarCarrito(int usuarioId) {
    List<CarritoItem> items = carritoItemRepo.findByUsuarioId(usuarioId);
    carritoItemRepo.deleteAll(items);
  }

  private Producto buscarProducto(int productoId) {
    return productoRepo.findById(productoId)
        .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + productoId));
  }

  private CarritoItem buscarItem(int id) {
    return carritoItemRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Item del carrito no encontrado con id " + id));
  }

  private void validarStock(Producto producto, int cantidad) {
    if (cantidad > producto.getStock()) {
      throw new StockInsuficienteException(
          "Stock insuficiente: quedan " + producto.getStock() + " unidades de " + producto.getNombre());
    }
  }
}