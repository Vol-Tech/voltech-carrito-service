package cl.voltech.carrito.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "carrito_item")

public class CarritoItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Column(name = "id")
  private int id;

  @Column(name = "usuario_id", nullable = false)
  private int usuario_id;

  @Column(name = "nombre", nullable = false, length = 200)
  private String nombre;

  @Column(name = "precio", nullable = false)
  private int precio;

  @Column(name = "imagen_url", length = 500)
  private String imagenUrl; // esta columna sirve para guardar el enlace de la imagen del producto

  @Column(name = "cantidad", nullable = false)
  private int cantidad;
}
