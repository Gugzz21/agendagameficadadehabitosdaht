package com.senac.daht.daht.repository;
import com.senac.daht.daht.entity.RegistroOuro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RegistroOuroRepository extends JpaRepository<RegistroOuro, Integer> {
}
