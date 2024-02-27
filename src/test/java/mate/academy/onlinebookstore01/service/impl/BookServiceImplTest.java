package mate.academy.onlinebookstore01.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mate.academy.onlinebookstore01.dto.book.BookDto;
import mate.academy.onlinebookstore01.dto.book.CreateBookRequestDto;
import mate.academy.onlinebookstore01.mapper.BookMapper;
import mate.academy.onlinebookstore01.model.Book;
import mate.academy.onlinebookstore01.model.Category;
import mate.academy.onlinebookstore01.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("Verify save() method works")
    public void save_ValidCreateBookRequestDto_ReturnsBookDto() {
        CreateBookRequestDto bookRequestDto = new CreateBookRequestDto();
        bookRequestDto.setId(1L);
        bookRequestDto.setTitle("The Great Gatsby");
        bookRequestDto.setAuthor("F. Scott Fitzgerald");
        bookRequestDto.setIsbn("9780743273565");
        bookRequestDto.setPrice(new BigDecimal("10.99"));
        bookRequestDto.setDescription("A novel by F. Scott Fitzgerald about the American Dream");
        bookRequestDto.setCoverImage("great-gatsby-cover.jpg");
        bookRequestDto.setCategoryIds(Set.of(1L));

        Category category = new Category();
        category.setId(1L);
        category.setName("Novel");
        category.setDescription("Novel description");

        List<Category> categories = List.of(category);

        Book book = new Book();
        book.setId(bookRequestDto.getId());
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPrice(bookRequestDto.getPrice());
        book.setDescription(bookRequestDto.getDescription());
        book.setCoverImage(bookRequestDto.getCoverImage());
        book.setCategories(new HashSet<>(Set.of(categories.get(0))));

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescription(book.getDescription());
        bookDto.setCoverImage(book.getCoverImage());
        bookDto.setCategoryIds(Set.of(1L));

        when(bookMapper.toModel(bookRequestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto savedBook = bookService.save(bookRequestDto);

        assertEquals(bookDto, savedBook);
    }
}
