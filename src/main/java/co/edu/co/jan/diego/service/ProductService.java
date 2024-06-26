package co.edu.co.jan.diego.service;

import java.util.List;
import java.util.Optional;

import co.edu.co.jan.diego.dtos.ProductDto;
import co.edu.co.jan.diego.model.Product;

public interface ProductService {
	ProductDto save(Product product);
	Optional<Product> get(Integer id);
	void update(Product product);
	void delete(Integer id);
	List<ProductDto> findAll();

}
