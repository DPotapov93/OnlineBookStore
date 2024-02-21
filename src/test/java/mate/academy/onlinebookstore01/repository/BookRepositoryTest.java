package mate.academy.onlinebookstore01.repository;

import java.math.BigDecimal;
import java.util.Set;
import mate.academy.onlinebookstore01.model.Book;
import mate.academy.onlinebookstore01.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Find book by id")
    @Sql(scripts
            = "classpath:database/books/add-book-and-category-to-books-and-categories-tables.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts
            = "classpath:database/books/delete-book-and-category-"
            + "from-books-and-books-categories-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findById_BookWithCategory_ReturnsBook() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Novel");

        Set<Category> categories = Set.of(category);

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setTitle("The Great Gatsby");
        expectedBook.setAuthor("F. Scott Fitzgerald");
        expectedBook.setIsbn("9780743273565");
        expectedBook.setPrice(BigDecimal.valueOf(11));
        expectedBook.setCategories(categories);

        Book actualBook = bookRepository.findById(1L).get();

        Assertions.assertEquals(expectedBook, actualBook);
    }
}
