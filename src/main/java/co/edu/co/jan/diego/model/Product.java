package co.edu.co.jan.diego.model;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "productos")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private String imagen;
	private double precio;
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;


}
