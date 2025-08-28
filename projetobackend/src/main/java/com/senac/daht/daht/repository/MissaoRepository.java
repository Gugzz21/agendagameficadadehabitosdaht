package com.senac.daht.daht.repository;

import com.senac.daht.daht.entity.Missao;
import com.senac.daht.daht.entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Integer> {

    List<Missao> findByPersonagemAndStatus(Personagem personagem, int status);
}
