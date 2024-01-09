package mate.academy.onlinebookstore01.service;

import java.util.List;
import mate.academy.onlinebookstore01.dto.book.BookDto;
import mate.academy.onlinebookstore01.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.onlinebookstore01.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId);
}
