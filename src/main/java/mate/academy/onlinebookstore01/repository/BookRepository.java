package mate.academy.onlinebookstore01.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.onlinebookstore01.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN FETCH b.categories c WHERE c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);

    @Query("SELECT b FROM Book b JOIN FETCH b.categories WHERE b.id = :id")
    Optional<Book> findById(Long id);
}
