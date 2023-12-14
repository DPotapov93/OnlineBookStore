package mate.academy.onlinebookstore01.repository;

import mate.academy.onlinebookstore01.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
