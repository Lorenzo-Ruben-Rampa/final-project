package org.be_bookbook.java.be_bookbook.repository;
import org.be_bookbook.java.be_bookbook.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}