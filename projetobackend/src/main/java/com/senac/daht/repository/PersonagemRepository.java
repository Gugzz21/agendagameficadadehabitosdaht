package com.senac.daht.repository;

import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.Usuario;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {
    @Query("SELECT p FROM Personagem p WHERE p.id > 0")
    @Lock(LockModeType.NONE)
    List<Personagem> listarTodosPersonagens();

    @Override
    @Lock(LockModeType.NONE)
    Optional<Personagem> findById(Integer id);


}