package cl.voltech.carrito.exception;

public class StockInsuficienteException extends RuntimeException {

  public StockInsuficienteException(String mensaje) {
    super(mensaje);
  }
}