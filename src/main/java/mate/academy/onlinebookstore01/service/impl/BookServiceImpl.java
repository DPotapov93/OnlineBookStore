package mate.academy.onlinebookstore01.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.book.BookDto;
import mate.academy.onlinebookstore01.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.onlinebookstore01.dto.book.CreateBookRequestDto;
import mate.academy.onlinebookstore01.exception.EntityNotFoundException;
import mate.academy.onlinebookstore01.mapper.BookMapper;
import mate.academy.onlinebookstore01.model.Book;
import mate.academy.onlinebookstore01.repository.BookRepository;
import mate.academy.onlinebookstore01.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private static final String FIND_BY_ID_EXCEPTION = "Can`t find book by id: ";
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toModel(createBookRequestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(FIND_BY_ID_EXCEPTION + id)));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto createBookRequestDto) {
        if (bookRepository.existsById(id)) {
            Book model = bookMapper.toModel(createBookRequestDto);
            model.setId(id);
            return bookMapper.toDto(bookRepository.save(model));
        } else {
            throw new EntityNotFoundException(FIND_BY_ID_EXCEPTION + id);
        }
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId) {
        return bookRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(bookMapper::toBookDtoWithoutCategoryIds)
                .toList();
    }
}
