package mate.academy.onlinebookstore01.repository;

import java.util.List;
import mate.academy.onlinebookstore01.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
