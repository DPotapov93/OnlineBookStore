package mate.academy.onlinebookstore01.service;

import java.util.List;
import mate.academy.onlinebookstore01.dto.BookDto;
import mate.academy.onlinebookstore01.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
