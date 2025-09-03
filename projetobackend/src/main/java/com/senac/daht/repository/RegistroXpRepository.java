package com.senac.daht.repository;

import com.senac.daht.entity.RegistroXp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroXpRepository extends JpaRepository<RegistroXp,Integer>{

}
