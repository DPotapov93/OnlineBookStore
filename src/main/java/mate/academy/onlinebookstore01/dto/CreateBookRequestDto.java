package mate.academy.onlinebookstore01.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @Min(1)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
