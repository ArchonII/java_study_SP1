package ru.experince.star_foto_2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
public class ApodImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String title;

    @Column(name = "date_gen")
    private LocalDate dategen;

    @Column(name = "date_fetch")
    private LocalDate datefetch;

    private String url;


    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public ApodImage(String _title, String _url, LocalDate _dateGen, User _user) {
        this.title = _title;
        this.url = _url;
        this.dategen = _dateGen;
        this.datefetch = LocalDate.now();
        user = _user;
    }

    public ApodImage() {
    }

}
