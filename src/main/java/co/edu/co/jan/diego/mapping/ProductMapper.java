package co.edu.co.jan.diego.mapping;

import co.edu.co.jan.diego.dtos.ProductDto;
import co.edu.co.jan.diego.model.Product;

public class ProductMapper {
    public static ProductDto mapFrom(Product orden){
        return new ProductDto(orden.getId(), orden.getNombre(),orden.getDescripcion(), orden.getPrecio(), orden.getImagen(), orden.getCantidad());
    }
    public static Product mapFromDto(ProductDto ordenDto){
        return Product.builder()
                .id(ordenDto.id())
                .nombre(ordenDto.nombre())
                .descripcion(ordenDto.descripcion())
                .precio(ordenDto.precio())
                .imagen(ordenDto.imagen())
                .cantidad(ordenDto.cantidad())
                .build();
    }
}
