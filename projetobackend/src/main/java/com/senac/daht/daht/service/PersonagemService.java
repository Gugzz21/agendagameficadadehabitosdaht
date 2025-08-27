package com.senac.daht.daht.service;

import com.senac.daht.daht.entity.Personagem;
import com.senac.daht.daht.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonagemService {

    private PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository){
        this.personagemRepository = personagemRepository;
    }

    public Personagem mostrarPersonagem(){
        Personagem p1 = new Personagem();
        return this.personagemRepository.findById(p1.getId()).orElse(null);
    }
}
