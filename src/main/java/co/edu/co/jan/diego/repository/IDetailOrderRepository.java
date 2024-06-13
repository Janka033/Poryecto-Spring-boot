package co.edu.co.jan.diego.repository;

import co.edu.co.jan.diego.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetailOrderRepository extends JpaRepository<OrderDetails, Integer> {

}
