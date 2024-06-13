package co.edu.co.jan.diego.dtos;


import co.edu.co.jan.diego.model.Order;
import co.edu.co.jan.diego.model.Product;
import lombok.Builder;

import javax.validation.constraints.*;

public record OrderDetailsDto(@NotNull(message = "No puede ser null")
                              @NotBlank
                              @NotEmpty
                              Integer id,
                              @NotNull(message = "No puede ser null")
                              String nombre,
                              @NotNull(message = "No puede ser null")
                              double cantidad,
                              @NotNull(message = "No puede ser null")
                              double precio,
                              @NotNull(message = "No puede ser null")
                              double total,
                              Order order,
                              Product product
) {
}
