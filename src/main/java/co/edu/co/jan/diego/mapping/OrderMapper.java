package co.edu.co.jan.diego.mapping;

import co.edu.co.jan.diego.dtos.OrderDto;
import co.edu.co.jan.diego.model.Order;

public class OrderMapper {
    public static OrderDto mapFrom(Order orden){
        return new OrderDto(orden.getId(), orden.getNumero(), orden.getFechaCreacion(),orden.getFechaRecibida(), orden.getTotal());
    }
    public static Order mapFromDto(OrderDto ordenDto){
        return Order.builder()
                .id(ordenDto.id())
                .numero(ordenDto.numero())
                .fechaCreacion(ordenDto.fechaCreacion())
                .fechaRecibida(ordenDto.fechaRecibida())
                .total(ordenDto.total())
                .build();
    }
}
