package co.edu.co.jan.diego.model;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "detalles")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private double cantidad;
	private double precio;
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Product product;

	@Override
	public String toString() {
		return "DetalleOrden [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", total=" + total + "]";
	}

}
