package mate.academy.onlinebookstore01.dto.cart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemQuantityRequestDto {
    @Positive(message = "Quantity must be positive")
    private int quantity;
}
