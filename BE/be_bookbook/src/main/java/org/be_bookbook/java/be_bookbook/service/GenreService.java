package org.be_bookbook.java.be_bookbook.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.be_bookbook.java.be_bookbook.model.Genre;
import java.util.List;
import java.util.Optional;
import org.be_bookbook.java.be_bookbook.repository.GenreRepository;

@Service
public class GenreService {
      @Autowired
    private GenreRepository genresRepository;

    public List<Genre> findAll() {
        return genresRepository.findAll();
    }

    public Genre create (Genre genre) {
        return genresRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genresRepository.save(genre);
    }

    public Genre getById(Integer id) {
        Optional<Genre> genreAttempt = genresRepository.findById(id);
        if (genreAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "genere con ID " + id + " non trovato.");
        }
        return genreAttempt.get();
    }

    public void delete(Genre genre) {
        genresRepository.delete(genre);
    }

    public void deleteById(Integer id) {
        Genre genre = getById(id);
        delete(genre);
    }

}
