package co.edu.co.jan.diego.dtos;

import lombok.Builder;

import javax.validation.constraints.*;
import java.util.Date;
public record OrderDto(@NotNull(message = "No puede ser null")
                       @NotBlank
                       @NotEmpty
                       Integer id,
                       @NotNull
                       @NegativeOrZero
                       String numero,
                       Date fechaCreacion,
                       Date fechaRecibida,
                       double total) {
}
