package cl.voltech.carrito.model;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "productos")
public class Producto {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "precio")
  private int precio;

  @Column(name = "stock")
  private int stock;

  @Column(name = "imagen_url")
  private String imagenUrl;
}