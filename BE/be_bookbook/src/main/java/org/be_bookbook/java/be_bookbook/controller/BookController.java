package org.be_bookbook.java.be_bookbook.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.be_bookbook.java.be_bookbook.repository.BookRepository;
import org.be_bookbook.java.be_bookbook.service.BookService;
import org.be_bookbook.java.be_bookbook.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.be_bookbook.java.be_bookbook.model.Book;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.be_bookbook.java.be_bookbook.repository.GenreRepository;


@Controller
@RequestMapping("/books")
public class BookController {

    private final GenreRepository genresRepository;

    @Autowired
    private BookService bookService;

    BookController(GenreRepository genresRepository, BookService bookService) {
        this.genresRepository = genresRepository;
        this.bookService = bookService;
    }

    //INDEX
    @GetMapping
    public String index (Model model, Authentication authentication, @RequestParam(name="bookName", required=false) String searchBookName) {
        List<Book> books;
        if (searchBookName != null && !searchBookName.isBlank()) {
            books = bookService.findByName(searchBookName);
            model.addAttribute("searchBookName", searchBookName);
        } else {
            books = bookService.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("username", authentication.getName());
        return "books/index";
    }
    
    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "/books/bookDetail";
        }

     // CREATE 
    @GetMapping("/new") 
    public String create(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        // Fetch di tutti i generi per il form di creazione
        model.addAttribute("allGenres", genresRepository.findAll()); 
        return "books/create-or-edit";
    }

    // store del CREATE
    @PostMapping("/new")
    public String store (@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/books/create-or-edit";
        }
        bookService.create(book);
        return "redirect:/books";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("edit", true);                   
        model.addAttribute("allGenres", genresRepository.findAll()); 
        return "books/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("allGenres", genresRepository.findAll()); 
            return "books/create-or-edit";
        }
        bookService.update(book);
        return  "redirect:/books";
    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
