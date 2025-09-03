package com.senac.daht.repository;

import com.senac.daht.entity.TabelaPremio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaPremioRepository extends JpaRepository<TabelaPremio, Integer> {

}
