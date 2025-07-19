package org.be_bookbook.java.be_bookbook.service;
import org.be_bookbook.java.be_bookbook.repository.GenreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.be_bookbook.java.be_bookbook.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.be_bookbook.java.be_bookbook.model.Genre;
import org.be_bookbook.java.be_bookbook.model.Book;

@Service
public class BookService {
    @Autowired
    private GenreRepository genresRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
        }
    public List<Book> findAllSortedByTitle() {
        return bookRepository.findAll(Sort.by("title"));
    }

    public List<Book> findAllSortedByAuthor() {
        return bookRepository.findAll(Sort.by("author"));
    }

    //Aggiunta per BookRestController
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    public Book getById(Integer id) {
        Optional<Book> bookAttempt = bookRepository.findById(id);
        if (bookAttempt.isEmpty()) {
            //lancio una notFound con response entity
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro con ID " + id + " non trovato.");
        }
            return bookAttempt.get();
        }

        public List<Book> findByName(String title) {
            return bookRepository.findByNameContainingIgnoreCase(title);
        }

        public Book create(Book book) {
            return bookRepository.save(book);
        }

        public Book update(Book book) {
            return bookRepository.save(book);
        }

        public void delete(Book book) {
            for (Genre genreToDelete : book.getGenres()) {
                genresRepository.delete(genreToDelete);
            }
            bookRepository.delete(book);
        }

        public void deleteById(Integer id) {
            Book book = getById(id);
            for (Genre genreToDelete : book.getGenres()) {
                genresRepository.delete(genreToDelete);
            }
            bookRepository.delete(book);
        }

        public Boolean existsById(Integer id) {
            return bookRepository.existsById(id);
        }

        public Boolean exists(Book book) {
            return existsById(book.getId());
        }
}
