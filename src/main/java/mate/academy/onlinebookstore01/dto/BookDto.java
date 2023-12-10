package mate.academy.onlinebookstore01.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    BigDecimal price;
    String description;
    String coverImage;
}
