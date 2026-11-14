package cl.voltech.carrito.dto;

import java.util.List;

public record CarritoResponse(
    int usuarioId,
    List<CarritoItemResponse> items,
    int total,
    int cantidadTotal) {
}