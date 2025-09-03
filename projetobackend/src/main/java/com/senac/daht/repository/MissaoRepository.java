package com.senac.daht.repository;

import com.senac.daht.entity.Missao;
import com.senac.daht.entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Integer> {

    List<Missao> findByPersonagemAndStatus(Personagem personagem, int status);
}
