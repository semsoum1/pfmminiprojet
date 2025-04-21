package com.example.miniprojet.repository;

import com.example.miniprojet.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    Optional<Emprunt> findByUserUsernameAndLivreId(String username, Long livreId);
}