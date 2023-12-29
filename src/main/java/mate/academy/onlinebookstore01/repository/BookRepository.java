package mate.academy.onlinebookstore01.repository;

import mate.academy.onlinebookstore01.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
