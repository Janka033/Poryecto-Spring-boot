package co.edu.co.jan.diego.service;

import java.util.List;
import java.util.Optional;

import co.edu.co.jan.diego.model.Order;
import co.edu.co.jan.diego.model.Usuario;

public interface IOrderService {
	List<Order> findAll();
	Optional<Order> findById(Integer id);
	Order save (Order order);
	String generarNumeroOrden();
	List<Order> findByUsuario (Usuario usuario);
}
