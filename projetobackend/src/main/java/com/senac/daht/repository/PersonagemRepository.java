package com.senac.daht.repository;

import com.senac.daht.entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {
    Optional<Personagem> findByUsuarioId(int userId);
}
