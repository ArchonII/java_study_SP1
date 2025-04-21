package ru.experince.star_foto.Entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String uname;

    private String hash_pass;

    public User(String _name) {
        this.uname = _name;
    }

    public User() {

    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + uname + "]";
    }
}
