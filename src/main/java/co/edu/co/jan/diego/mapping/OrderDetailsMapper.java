package co.edu.co.jan.diego.mapping;

import co.edu.co.jan.diego.dtos.OrderDetailsDto;
import co.edu.co.jan.diego.model.OrderDetails;

public class OrderDetailsMapper {
    public static OrderDetailsDto mapFrom(OrderDetails orden){
        return new OrderDetailsDto(orden.getId(), orden.getNombre(), orden.getCantidad(), orden.getPrecio(), orden.getTotal(),orden.getOrder(),orden.getProduct());
    }
    public static OrderDetails mapFromDto(OrderDetailsDto ordenDto){
        return OrderDetails.builder()
                .id(ordenDto.id())
                .nombre(ordenDto.nombre())
                .cantidad(ordenDto.cantidad())
                .precio(ordenDto.precio())
                .total(ordenDto.total())
                .order(ordenDto.order())
                .product(ordenDto.product())
                .build();
    }
}
