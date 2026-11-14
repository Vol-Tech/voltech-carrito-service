package cl.voltech.carrito.mapper;

import cl.voltech.carrito.dto.AgregarItemRequest;
import cl.voltech.carrito.dto.CarritoItemResponse;
import cl.voltech.carrito.model.CarritoItem;
import cl.voltech.carrito.model.Producto;

public interface CarritoMapper {

  public static CarritoItem toModel(AgregarItemRequest request, Producto producto) {
    return new CarritoItem(
        0,
        request.usuarioId(),
        producto.getId(),
        producto.getNombre(),
        producto.getPrecio(),
        producto.getImagenUrl(),
        request.cantidad());
  }

  public static CarritoItemResponse toResponse(CarritoItem item) {
    return new CarritoItemResponse(
        item.getId(),
        item.getProductoId(),
        item.getNombre(),
        item.getPrecio(),
        item.getImagenUrl(),
        item.getCantidad(),
        item.getPrecio() * item.getCantidad());
  }
}