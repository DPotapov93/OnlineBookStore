package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.dto.BookDto;
import mate.academy.onlinebookstore01.dto.CreateBookRequestDto;
import mate.academy.onlinebookstore01.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setIsbn(book.getIsbn());
        dto.setCoverImage(book.getCoverImage());
        dto.setDescription(book.getDescription());
        return dto;
    }

    @Override
    public Book toModel(CreateBookRequestDto createBookRequestDto) {
        Book book = new Book();
        book.setId(createBookRequestDto.getId());
        book.setTitle(createBookRequestDto.getTitle());
        book.setAuthor(createBookRequestDto.getAuthor());
        book.setPrice(createBookRequestDto.getPrice());
        book.setIsbn(createBookRequestDto.getIsbn());
        book.setIsbn(book.getIsbn());
        book.setCoverImage(createBookRequestDto.getCoverImage());
        book.setDescription(createBookRequestDto.getDescription());
        return book;
    }
}
