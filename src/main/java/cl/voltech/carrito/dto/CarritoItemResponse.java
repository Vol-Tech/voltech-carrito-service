package cl.voltech.carrito.dto;

public record CarritoItemResponse(
    int id,
    int productoId,
    String nombre,
    int precio,
    String imagenUrl,
    int cantidad,
    int subtotal) {
}