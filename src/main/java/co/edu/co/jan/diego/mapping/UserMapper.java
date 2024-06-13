package co.edu.co.jan.diego.mapping;

import co.edu.co.jan.diego.dtos.UserDto;
import co.edu.co.jan.diego.model.Usuario;

public class UserMapper {
    public static UserDto mapFrom(Usuario orden){
        return new UserDto(orden.getId(), orden.getNombre(), orden.getUsername(), orden.getEmail(), orden.getDireccion(), orden.getTelefono(), orden.getTipo(), orden.getPassword());
    }
    public static Usuario mapFromDto(UserDto ordenDto){
        return Usuario.builder()
                .id(ordenDto.id())
                .nombre(ordenDto.nombre())
                .username(ordenDto.username())
                .email(ordenDto.email())
                .direccion(ordenDto.direccion())
                .telefono(ordenDto.telefono())
                .password(ordenDto.password())
                .tipo(ordenDto.tipo())
                .build();
    }
}
