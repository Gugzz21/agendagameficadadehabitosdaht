package com.senac.daht.daht.repository;

import com.senac.daht.daht.entity.TabelaPremio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaPremioRepository extends JpaRepository<TabelaPremio, Integer> {

}
