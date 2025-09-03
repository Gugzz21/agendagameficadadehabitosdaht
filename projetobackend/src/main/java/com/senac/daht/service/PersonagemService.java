package com.senac.daht.service;

import com.senac.daht.entity.Personagem;
import com.senac.daht.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonagemService {

    private PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository){
        this.personagemRepository = personagemRepository;
    }

   public Optional<Personagem> listarPersonagem(int userId){
        return this.personagemRepository.findByUsuarioId(userId);
   }
}
