package cl.voltech.carrito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.voltech.carrito.dto.AgregarItemRequest;
import cl.voltech.carrito.dto.CambiarCantidadRequest;
import cl.voltech.carrito.dto.CarritoResponse;
import cl.voltech.carrito.service.CarritoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carrito")
@CrossOrigin(origins = "http://localhost:5173")
public class CarritoController {

  private final CarritoService carritoService;

  public CarritoController(CarritoService carritoService) {
    this.carritoService = carritoService;
  }

  @GetMapping("/usuario/{usuarioId}")
  public ResponseEntity<CarritoResponse> obtenerCarrito(@PathVariable("usuarioId") int usuarioId) {
    return ResponseEntity.ok(carritoService.obtenerCarrito(usuarioId));
  }

  @PostMapping
  public ResponseEntity<CarritoResponse> agregarItem(@Valid @RequestBody AgregarItemRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.agregarItem(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarritoResponse> cambiarCantidad(
      @PathVariable("id") int id,
      @Valid @RequestBody CambiarCantidadRequest request) {
    return ResponseEntity.ok(carritoService.cambiarCantidad(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CarritoResponse> eliminarItem(@PathVariable("id") int id) {
    return ResponseEntity.ok(carritoService.eliminarItem(id));
  }

  @DeleteMapping("/usuario/{usuarioId}")
  public ResponseEntity<Void> vaciarCarrito(@PathVariable("usuarioId") int usuarioId) {
    carritoService.vaciarCarrito(usuarioId);
    return ResponseEntity.noContent().build();
  }
}