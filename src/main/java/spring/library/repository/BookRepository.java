package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.library.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
}
