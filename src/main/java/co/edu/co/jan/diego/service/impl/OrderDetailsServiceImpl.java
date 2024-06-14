package co.edu.co.jan.diego.service.impl;

import co.edu.co.jan.diego.dtos.OrderDetailsDto;
import co.edu.co.jan.diego.mapping.OrderDetailsMapper;
import co.edu.co.jan.diego.model.OrderDetails;
import co.edu.co.jan.diego.service.IDetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.co.jan.diego.repository.IDetailOrderRepository;

@Service
public class OrderDetailsServiceImpl implements IDetalleOrdenService {
	
	@Autowired
	private IDetailOrderRepository detalleOrdenRepository;

	@Override
	public OrderDetailsDto save(OrderDetails orderDetails) {

		return OrderDetailsMapper.mapFrom(detalleOrdenRepository.save(orderDetails));
	}

}
