package com.example.miniprojet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emprunt")
public class Emprunt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private User user;
    @ManyToOne private Livre livre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getEmpruntDate() {
        return empruntDate;
    }

    public void setEmpruntDate(LocalDate empruntDate) {
        this.empruntDate = empruntDate;
    }

    public LocalDate getRetourDate() {
        return retourDate;
    }

    public void setRetourDate(LocalDate retourDate) {
        this.retourDate = retourDate;
    }

    private LocalDate empruntDate;
    private LocalDate retourDate;
}