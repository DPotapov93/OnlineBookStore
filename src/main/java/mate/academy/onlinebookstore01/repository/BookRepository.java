package mate.academy.onlinebookstore01.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.onlinebookstore01.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);
}
