package com.senac.daht.service;

import com.senac.daht.dto.request.RegistroOuroDTORequest;
import com.senac.daht.dto.response.RegistroOuroDTOResponse;
import com.senac.daht.entity.RegistroOuro;
import com.senac.daht.entity.Personagem;
import com.senac.daht.repository.RegistroOuroRepository;
import com.senac.daht.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroOuroService {
    private final RegistroOuroRepository registroOuroRepository;
    private final PersonagemRepository personagemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RegistroOuroService(RegistroOuroRepository registroOuroRepository, PersonagemRepository personagemRepository, ModelMapper modelMapper) {
        this.registroOuroRepository = registroOuroRepository;
        this.personagemRepository = personagemRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistroOuroDTOResponse> listarRegistrosOuro() {
        return registroOuroRepository.findAll().stream()
                .map(registro -> modelMapper.map(registro, RegistroOuroDTOResponse.class))
                .collect(Collectors.toList());
    }

    public RegistroOuroDTOResponse listarPorId(Integer id) {
        RegistroOuro registroOuro = registroOuroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de Ouro com ID " + id + " não encontrado."));
        return modelMapper.map(registroOuro, RegistroOuroDTOResponse.class);
    }

    public RegistroOuroDTOResponse criarRegistroOuro(RegistroOuroDTORequest registroOuroDTORequest) {
        RegistroOuro registroOuro = modelMapper.map(registroOuroDTORequest, RegistroOuro.class);

        Personagem personagem = personagemRepository.findById(registroOuroDTORequest.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + registroOuroDTORequest.getPersonagemId() + " não encontrado."));
        registroOuro.setPersonagem(personagem);

        RegistroOuro savedRegistroOuro = registroOuroRepository.save(registroOuro);
        return modelMapper.map(savedRegistroOuro, RegistroOuroDTOResponse.class);
    }

    public RegistroOuroDTOResponse atualizarRegistroOuro(Integer id, RegistroOuroDTORequest registroOuroDTORequest) {
        RegistroOuro registroOuro = registroOuroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de Ouro com ID " + id + " não encontrado."));

        modelMapper.map(registroOuroDTORequest, registroOuro);

        Personagem personagem = personagemRepository.findById(registroOuroDTORequest.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + registroOuroDTORequest.getPersonagemId() + " não encontrado."));
        registroOuro.setPersonagem(personagem);

        RegistroOuro updatedRegistroOuro = registroOuroRepository.save(registroOuro);
        return modelMapper.map(updatedRegistroOuro, RegistroOuroDTOResponse.class);
    }

    public void deletarRegistroOuro(Integer id) {
        registroOuroRepository.deleteById(id);
    }
}