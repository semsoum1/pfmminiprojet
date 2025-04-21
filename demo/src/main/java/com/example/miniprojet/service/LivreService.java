package com.example.miniprojet.service;


import com.example.miniprojet.model.*;
import com.example.miniprojet.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import java.time.LocalDate;
import java.util.*;


@Service
public class LivreService {
    @Autowired private LivreRepository livreRepo;
    @Autowired private EmpruntRepository empruntRepo;
    @Autowired private UserRepository userRepo;

    public List<Livre> getAvailableLivres() {
        return livreRepo.findByAvailableTrue();
    }

    public ResponseEntity<String> empruntLivre(Long livreId, String username) {
        Optional<Livre> livreOpt = livreRepo.findById(livreId);
        if (livreOpt.isEmpty() || !livreOpt.get().isAvailable()) return ResponseEntity.badRequest().body("non disponible");
        Livre livre = livreOpt.get();
        User user = userRepo.findByUsername(username).orElseThrow();
        livre.setAvailable(false);
        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setUser(user);
        emprunt.setEmpruntDate(LocalDate.now());
        empruntRepo.save(emprunt);
        livreRepo.save(livre);
        return ResponseEntity.ok("Book borrowed");
    }

    public ResponseEntity<String> retourLivre(Long livreId, String username) {
        Optional<Emprunt> empruntOpt = empruntRepo.findByUserUsernameAndLivreId(username, livreId);
        if (empruntOpt.isEmpty()) return ResponseEntity.badRequest().body("disponible");
        Emprunt emprunt = empruntOpt.get();
        emprunt.getLivre().setAvailable(true);
        emprunt.setRetourDate(LocalDate.now());
        empruntRepo.save(emprunt);
        livreRepo.save(emprunt.getLivre());
        return ResponseEntity.ok("Book returned");
    }

    public Livre save(Livre livre) {
        return livreRepo.save(livre);
    }

    public Livre update(Long id, Livre livre) {
        Livre existing = livreRepo.findById(id).orElseThrow();
        existing.setTitle(livre.getTitle());
        existing.setAuthor(livre.getAuthor());
        return livreRepo.save(existing);
    }

    public void delete(Long id) {
        livreRepo.deleteById(id);
    }
}
