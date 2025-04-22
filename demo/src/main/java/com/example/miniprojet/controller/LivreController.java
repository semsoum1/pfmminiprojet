package com.example.miniprojet.controller;

import com.example.miniprojet.model.Livre;
import com.example.miniprojet.service.LivreService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livres")
public class LivreController {
    @Autowired private LivreService livreService;


    @GetMapping
    public List<Livre> getAvailableLivres() {
        return livreService.getAvailableLivres();
    }

    @PostMapping("/emprunt/{livreId}")
    public ResponseEntity<String> empruntLivre(@PathVariable Long livreId, Authentication auth) {
        return livreService.empruntLivre(livreId, auth.getName());
    }

    @PostMapping("/retour/{livreId}")
    public ResponseEntity<String> retourLivre(@PathVariable Long livreId, Authentication auth) {
        return livreService.retourLivre(livreId, auth.getName());
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return livreService.save(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        return livreService.update(id, livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id) {
        livreService.delete(id);
    }
}