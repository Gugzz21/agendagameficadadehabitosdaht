package com.senac.daht.daht.repository;

import com.senac.daht.daht.entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {
    Optional<Personagem> findByUsuarioId(int userId);
}
