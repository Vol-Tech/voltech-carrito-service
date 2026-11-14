package cl.voltech.carrito.dto;

import jakarta.validation.constraints.Positive;

public record CambiarCantidadRequest(
    @Positive(message = "La cantidad debe ser mayor a 0") 
    int cantidad) {
}