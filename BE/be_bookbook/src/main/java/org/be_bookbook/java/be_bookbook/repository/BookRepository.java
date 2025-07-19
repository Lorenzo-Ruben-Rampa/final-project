package org.be_bookbook.java.be_bookbook.repository;
import org.be_bookbook.java.be_bookbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository <Book, Integer> {
    // Nuovo metodo per cercare libri il cui nome contiene una certa stringa (case-insensitive)
    List<Book> findByNameContainingIgnoreCase(String name);
}
