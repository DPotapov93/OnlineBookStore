package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.dto.BookDto;
import mate.academy.onlinebookstore01.dto.CreateBookRequestDto;
import mate.academy.onlinebookstore01.model.Book;

public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto createBookRequestDto);
}
