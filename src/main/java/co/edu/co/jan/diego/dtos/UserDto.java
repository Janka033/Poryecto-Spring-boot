package co.edu.co.jan.diego.dtos;

import lombok.Builder;

import javax.validation.constraints.*;


public record UserDto(
        @NotNull(message = "No puede ser null")
        @NotBlank
        @NotEmpty
        Integer id,
        String nombre,
        String username,
        @Email(message = "Debe ser un email válido")
        String email,
        @NotNull(message = "No puede ser null")
        String direccion,
        @NotNull(message = "No puede ser null")

        @NotNull(message = "No puede ser null")
        String telefono,
        @NotNull(message = "No puede ser null")
        String tipo,
        String password) {
}
