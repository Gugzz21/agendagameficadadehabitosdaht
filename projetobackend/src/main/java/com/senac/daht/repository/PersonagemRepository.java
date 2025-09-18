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

    // Sobrescreve o comportamento padrão de findAll() com uma consulta que
    // lida com duplicatas, selecionando apenas uma linha por usuário.
    @Query("SELECT p FROM Personagem p WHERE p.id IN (SELECT MIN(p2.id) FROM Personagem p2 GROUP BY p2.usuario.id)")
    @Lock(LockModeType.NONE)
    List<Personagem> findAll();

    @Override
    @Lock(LockModeType.NONE)
    Optional<Personagem> findById(Integer id);

    Optional<Personagem> findByUsuario(Usuario usuario);
    Optional<Personagem> findByUsuarioId(Integer usuarioId);
    List<Personagem> findByUsuarioStatus(int status);

}