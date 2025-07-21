package org.be_bookbook.java.be_bookbook.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.be_bookbook.java.be_bookbook.service.BookService;
import org.be_bookbook.java.be_bookbook.model.Book;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("api/books")
public class BookRestController {
    
    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> index() {
        List<Book> books = service.findAll();
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> show(@PathVariable Integer id) {
        Optional<Book> bookAttempt = service.findById(id);
        if (bookAttempt.isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(bookAttempt.get(), HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> store(@RequestBody Book book) {
        return new ResponseEntity<Book>(service.create(book), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable Integer id) {
      

         if (service.findById(id).isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
          book.setId(id); 
        return new ResponseEntity<Book>(service.update(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> delete(@PathVariable Integer id) {
        if (service.findById(id).isEmpty()) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }

        service.deleteById(id);
        return new ResponseEntity<Book>(HttpStatus.OK);
    }

}
