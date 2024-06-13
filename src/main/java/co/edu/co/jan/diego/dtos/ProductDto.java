package co.edu.co.jan.diego.dtos;

import lombok.Builder;

import javax.validation.constraints.*;
public record ProductDto(@NotNull(message = "No puede ser null")
                         @NotBlank
                         @NotEmpty
                         Integer id,
                         String nombre,
                         String descripcion,
                         double precio,
                         String imagen,
                         int cantidad) {
}
