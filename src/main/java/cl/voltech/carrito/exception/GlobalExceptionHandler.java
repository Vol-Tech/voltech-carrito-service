package cl.voltech.carrito.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail manejarValidacion(MethodArgumentNotValidException ex) {
    ProblemDetail problema = crearProblema(
        HttpStatus.BAD_REQUEST,
        "Error de validación",
        "Los datos enviados no cumplen las reglas de validación");

    Map<String, String> errores = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errores.put(error.getField(), error.getDefaultMessage());
    }
    problema.setProperty("errores", errores);

    return problema;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ProblemDetail manejarJsonInvalido(HttpMessageNotReadableException ex) {
    return crearProblema(
        HttpStatus.BAD_REQUEST,
        "Error de lectura",
        "El cuerpo de la petición no es un JSON válido");
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ProblemDetail manejarNoEncontrado(ResourceNotFoundException ex) {
    return crearProblema(HttpStatus.NOT_FOUND, "No encontrado", ex.getMessage());
  }

  @ExceptionHandler(StockInsuficienteException.class)
  public ProblemDetail manejarStock(StockInsuficienteException ex) {
    return crearProblema(HttpStatus.CONFLICT, "Stock insuficiente", ex.getMessage());
  }

  private ProblemDetail crearProblema(HttpStatus estado, String titulo, String detalle) {
    ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, detalle);
    problema.setTitle(titulo);
    return problema;
  }
}