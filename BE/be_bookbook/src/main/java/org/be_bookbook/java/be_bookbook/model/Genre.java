package org.be_bookbook.java.be_bookbook.model;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="genres")

public class Genre {
    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Il nome non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il nome può contenere solo lettere e spazi.")
    @Size(max = 50, message = "Il nome non può superare i 50 caratteri.")
    private String name;
    
    //Getters e Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
    