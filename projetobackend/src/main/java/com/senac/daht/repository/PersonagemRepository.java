package com.senac.daht.repository;

import com.senac.daht.entity.Personagem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {

    // O método findById já está definido no JpaRepository, mas você pode sobrescrevê-lo para adicionar o bloqueio
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Personagem> findById(Integer id);


    List<Personagem> findByUsuarioStatus(int status);
}