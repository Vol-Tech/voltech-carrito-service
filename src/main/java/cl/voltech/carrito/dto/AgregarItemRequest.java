package cl.voltech.carrito.dto;

import jakarta.validation.constraints.Positive;

public record AgregarItemRequest(
    @Positive(message = "El usuario es obligatorio") 
    int usuarioId,

    @Positive(message = "El producto es obligatorio") 
    int productoId,

    @Positive(message = "La cantidad debe ser mayor a 0") 
    int cantidad) {
}