package org.be_bookbook.java.be_bookbook.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Integer id;
    
    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    @JsonBackReference
    
    private Set<User> users;

    // Getters and Setters
    public Integer getId() {
        return id;
    }   

    public void setId(Integer id) {
        this.id = id;
    }   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
