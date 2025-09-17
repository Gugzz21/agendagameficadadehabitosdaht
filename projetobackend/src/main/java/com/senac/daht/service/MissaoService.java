package com.senac.daht.service;

import com.senac.daht.dto.request.MissaoDTORequest;
import com.senac.daht.dto.response.MissaoDTOResponse;
import com.senac.daht.entity.Missao;
import com.senac.daht.entity.Personagem;
import com.senac.daht.repository.MissaoRepository;
import com.senac.daht.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissaoService {
    private final MissaoRepository missaoRepository;
    private final PersonagemRepository personagemRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository, PersonagemRepository personagemRepository) {
        this.missaoRepository = missaoRepository;
        this.personagemRepository = personagemRepository;
    }

    public List<MissaoDTOResponse> listarMissoes() {
        return missaoRepository.findAll().stream()
                .map(this::toResponseDTO) // Converte manualmente para DTO de resposta
                .collect(Collectors.toList());
    }

    public MissaoDTOResponse listarPorId(Integer id) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão com ID " + id + " não encontrada."));
        return toResponseDTO(missao); // Converte manualmente para DTO de resposta
    }

    @Transactional
    public MissaoDTOResponse criarMissao(MissaoDTORequest missaoDTORequest) {
        Missao missao = new Missao();
        updateMissaoFromDto(missao, missaoDTORequest); // Cria e associa dados

        Missao savedMissao = missaoRepository.save(missao);
        return toResponseDTO(savedMissao); // Converte manualmente para DTO de resposta
    }

    @Transactional
    public MissaoDTOResponse atualizarMissao(Integer id, MissaoDTORequest missaoDTORequest) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão com ID " + id + " não encontrada."));

        updateMissaoFromDto(missao, missaoDTORequest); // Atualiza e associa dados

        Missao updatedMissao = missaoRepository.save(missao);
        return toResponseDTO(updatedMissao); // Converte manualmente para DTO de resposta
    }

    @Transactional
    public void deletarMissao(Integer id) {
        missaoRepository.deleteById(id);
    }

    // Métodos utilitários para conversão
    private MissaoDTOResponse toResponseDTO(Missao missao) {
        MissaoDTOResponse dto = new MissaoDTOResponse();
        dto.setId(missao.getId());
        dto.setDescricao(missao.getDescricao());
        dto.setRepeticao(missao.getRepeticao());
        dto.setDificuldade(missao.getDificuldade());
        dto.setEfeito(missao.getEfeito());
        dto.setDataFinalizacao(missao.getDataFinalizacao());
        dto.setDataInicio(missao.getDataInicio());
        dto.setStatus(missao.getStatus());
        if (missao.getPersonagem() != null) {
            dto.setNomePersonagem(missao.getPersonagem().getUsuario().getNome());
        }
        return dto;
    }

    private void updateMissaoFromDto(Missao missao, MissaoDTORequest dto) {
        missao.setDescricao(dto.getDescricao());
        missao.setRepeticao(dto.getRepeticao());
        missao.setDificuldade(dto.getDificuldade());
        missao.setEfeito(dto.getEfeito());
        missao.setDataFinalizacao(dto.getDataFinalizacao());
        missao.setDataInicio(dto.getDataInicio());
        missao.setStatus(dto.getStatus());

        Personagem personagem = personagemRepository.findById(dto.getPersonagemId())
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + dto.getPersonagemId() + " não encontrado."));
        missao.setPersonagem(personagem);
    }
}