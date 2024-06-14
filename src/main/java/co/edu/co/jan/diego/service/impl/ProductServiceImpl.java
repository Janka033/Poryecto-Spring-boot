package co.edu.co.jan.diego.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.co.jan.diego.dtos.ProductDto;
import co.edu.co.jan.diego.mapping.ProductMapper;
import co.edu.co.jan.diego.model.Product;
import co.edu.co.jan.diego.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.co.jan.diego.repository.IProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private IProductRepository productoRepository;

	@Override
	public ProductDto save(Product product) {

		return ProductMapper.mapFrom(productoRepository.save(product));
	}

	@Override
	public Optional<Product> get(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void update(Product product) {
		productoRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);		
	}

	@Override
	public List<ProductDto> findAll() {
		return productoRepository.findAll().stream().map(ProductMapper::mapFrom).collect(Collectors.toList());
	}

}
