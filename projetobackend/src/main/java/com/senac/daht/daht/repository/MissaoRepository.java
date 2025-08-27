package com.senac.daht.daht.repository;

import com.senac.daht.daht.entity.Missao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Integer> {
}
