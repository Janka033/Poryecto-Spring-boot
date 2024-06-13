package co.edu.co.jan.diego.repository;

import java.util.List;

import co.edu.co.jan.diego.model.Order;
import co.edu.co.jan.diego.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByUsuario (Usuario usuario);
}
