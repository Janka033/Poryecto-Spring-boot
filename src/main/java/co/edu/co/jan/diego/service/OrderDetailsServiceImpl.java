package co.edu.co.jan.diego.service;

import co.edu.co.jan.diego.model.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.co.jan.diego.repository.IDetailOrderRepository;

@Service
public class OrderDetailsServiceImpl implements IDetalleOrdenService{
	
	@Autowired
	private IDetailOrderRepository detalleOrdenRepository;

	@Override
	public OrderDetails save(OrderDetails orderDetails) {
		return detalleOrdenRepository.save(orderDetails);
	}

}
