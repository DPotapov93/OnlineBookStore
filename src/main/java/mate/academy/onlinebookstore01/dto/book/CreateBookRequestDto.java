package mate.academy.onlinebookstore01.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {

    private static final String NULL_AND_LIMIT_EXCEPTION
            = "Can't be null/empty and must be no more than 50 characters";
    private static final String ISBN_EXCEPTION
            = "Should contain only digits and the number of digits should be 10 or 13";
    private static final String PRICE_EXCEPTION = "Equals 0 or more";
    @Min(1)
    private Long id;
    @NotNull(message = NULL_AND_LIMIT_EXCEPTION)
    @Size(min = 1, max = 50)
    private String title;
    @NotNull(message = NULL_AND_LIMIT_EXCEPTION)
    @Size(min = 1, max = 50)
    private String author;
    @NotNull(message = ISBN_EXCEPTION)
    @Pattern(regexp
            = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})"
            + "[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)"
            + "(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$")
    private String isbn;
    @NotNull(message = PRICE_EXCEPTION)
    @Positive
    private BigDecimal price;
    private String description;
    private String coverImage;
}
