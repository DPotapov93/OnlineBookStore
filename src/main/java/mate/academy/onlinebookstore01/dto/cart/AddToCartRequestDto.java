package mate.academy.onlinebookstore01.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    @NotNull(message = "Book ID cannot be null")
    @Positive(message = "Book ID must be positive")
    private Long bookId;
    @Min(value = 1, message = "Quantity must be equal/greater than 1")
    private int quantity;
}
