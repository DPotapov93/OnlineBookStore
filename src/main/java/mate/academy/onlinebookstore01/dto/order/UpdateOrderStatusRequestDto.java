package mate.academy.onlinebookstore01.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderStatusRequestDto {
    @NotNull
    private String status;
}
