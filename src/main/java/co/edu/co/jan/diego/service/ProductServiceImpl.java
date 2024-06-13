package co.edu.co.jan.diego.service;

import java.util.List;
import java.util.Optional;

import co.edu.co.jan.diego.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.co.jan.diego.repository.IProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private IProductRepository productoRepository;

	@Override
	public Product save(Product product) {

		return productoRepository.save(product);
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
	public List<Product> findAll() {
		return productoRepository.findAll();
	}

}
