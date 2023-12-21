package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.config.MapperConfig;
import mate.academy.onlinebookstore01.dto.book.BookDto;
import mate.academy.onlinebookstore01.dto.book.CreateBookRequestDto;
import mate.academy.onlinebookstore01.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto createBookRequestDto);
}
