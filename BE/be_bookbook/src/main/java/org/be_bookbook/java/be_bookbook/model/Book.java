package org.be_bookbook.java.be_bookbook.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.ManyToMany;
import java.util.List;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="books")
public class Book {

    @ManyToMany()
    @JoinTable(
        name="book_genre",
        joinColumns = @JoinColumn(name="book_id"),
        inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private List<Genre> genres;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Il nome non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il nome può contenere solo lettere e spazi.")
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri.")
    private String name;

    @NotEmpty(message="L'autore non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "L'autore può contenere solo lettere e spazi.")
    @Size(max = 100, message = "L'autore non può superare i 100 caratteri.")
    private String author;

    @NotEmpty(message="Il publisher non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il publisher può contenere solo lettere e spazi.")
    @Size(max = 100, message = "Il publisher non può superare i 100 caratteri.")
    private String publisher;

    @Lob
    @Column(nullable=false)
    @NotBlank(message = "La descrizione non può essere vuota.")
    @Size(max = 455, message = "La descrizione non può superare i 455 caratteri.")
    private String synopsis;

    @NotBlank(message="Questo campo è obbligatorio")
    @Column(name = "cover_Image")
    private String coverImage;

    @NotNull(message = "La data di pubblicazione non può essere vuota")
    @Column(name = "publication_date") 
    private LocalDate publicationDate;

    @NotBlank(message = "L'ISBN non può essere vuoto")
    @Pattern(regexp = "^[0-9X-]{10,17}$", message = "Il formato dell'ISBN non è valido (ISBN-13)")
    // ISBN-13 con trattini ha 17 caratteri; unique=true assicura l'unicità
    @Column(length = 17, unique = true) 
    private String ISBN;

    @NotNull(message="Il prezzo non può essere vuoto o < di zero")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal price;

    //Getters e Setters
     public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

        public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCoverImage() {
        return this.coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //Getters e Setters di genres
    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
	public String toString(){
	  return  String.format(this.name, this.synopsis, this.price);
    }
}
    