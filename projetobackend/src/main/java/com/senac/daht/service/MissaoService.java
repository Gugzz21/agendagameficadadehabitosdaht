package com.senac.daht.service;

import com.senac.daht.dto.request.MissaoDTORequest;
import com.senac.daht.dto.response.MissaoDTOResponse;
import com.senac.daht.entity.Missao;
import com.senac.daht.entity.Personagem;
import com.senac.daht.repository.MissaoRepository;
import com.senac.daht.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissaoService {
    private final MissaoRepository missaoRepository;
    private final PersonagemRepository personagemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository, PersonagemRepository personagemRepository, ModelMapper modelMapper) {
        this.missaoRepository = missaoRepository;
        this.personagemRepository = personagemRepository;
        this.modelMapper = modelMapper;
    }

    public List<MissaoDTOResponse> listarMissoes() {
        return missaoRepository.findAll().stream()
                .map(missao -> modelMapper.map(missao, MissaoDTOResponse.class))
                .collect(Collectors.toList());
    }

    public MissaoDTOResponse listarPorId(Integer id) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão com ID " + id + " não encontrada."));
        return modelMapper.map(missao, MissaoDTOResponse.class);
    }

    public MissaoDTOResponse criarMissao(MissaoDTORequest missaoDTORequest) {
        Missao missao = modelMapper.map(missaoDTORequest, Missao.class);

        Personagem personagem = personagemRepository.findById(missaoDTORequest.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + missaoDTORequest.getPersonagemId() + " não encontrado."));
        missao.setPersonagem(personagem);

        Missao savedMissao = missaoRepository.save(missao);
        return modelMapper.map(savedMissao, MissaoDTOResponse.class);
    }

    public MissaoDTOResponse atualizarMissao(Integer id, MissaoDTORequest missaoDTORequest) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão com ID " + id + " não encontrada."));

        modelMapper.map(missaoDTORequest, missao);

        if (missaoDTORequest.getPersonagemId() != null) {
            Personagem personagem = personagemRepository.findById(missaoDTORequest.getPersonagemId())
                    .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + missaoDTORequest.getPersonagemId() + " não encontrado."));
            missao.setPersonagem(personagem);
        }

        Missao updatedMissao = missaoRepository.save(missao);
        return modelMapper.map(updatedMissao, MissaoDTOResponse.class);
    }

    public void deletarMissao(Integer id) {
        missaoRepository.deleteById(id);
    }
}