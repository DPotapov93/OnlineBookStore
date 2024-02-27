package mate.academy.onlinebookstore01.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryRequestDto {
    @NotBlank
    @Size(max = 25)
    private String name;
    @Size(max = 255)
    private String description;
}
