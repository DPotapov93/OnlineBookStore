package mate.academy.onlinebookstore01.service;

import java.util.List;
import mate.academy.onlinebookstore01.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
