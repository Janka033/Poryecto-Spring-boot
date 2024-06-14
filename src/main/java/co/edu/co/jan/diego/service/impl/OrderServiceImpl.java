package co.edu.co.jan.diego.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.co.jan.diego.dtos.OrderDto;
import co.edu.co.jan.diego.dtos.UserDto;
import co.edu.co.jan.diego.mapping.OrderMapper;
import co.edu.co.jan.diego.model.Order;
import co.edu.co.jan.diego.model.Usuario;
import co.edu.co.jan.diego.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.co.jan.diego.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private IOrderRepository ordenRepository;

	@Override
	public OrderDto save(Order order) {
		return OrderMapper.mapFrom(ordenRepository.save(order));
	}

	@Override
	public List<OrderDto> findAll() {
		return ordenRepository.findAll().stream().map(OrderMapper::mapFrom).collect(Collectors.toList());
	}
	// 0000010
	public String generarNumeroOrden() {
		int numero=0;
		String numeroConcatenado="";
		
		List<OrderDto> ordenes = findAll();
		
		List<Integer> numeros= new ArrayList<Integer>();
		
		ordenes.stream().forEach(o -> numeros.add( Integer.parseInt( o.numero())));
		
		if (ordenes.isEmpty()) {
			numero=1;
		}else {
			numero= numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if (numero<10) { //0000001000
			numeroConcatenado="000000000"+String.valueOf(numero);
		}else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
		}else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}else if(numero<10000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
		}		
		
		return numeroConcatenado;
	}

	@Override
	public List<Order> findByUsuario(Usuario usuario) {
		return ordenRepository.findByUsuario(usuario);
	}


	@Override
	public Optional<Order> findById(Integer id) {
		return ordenRepository.findById(id);
	}

}
