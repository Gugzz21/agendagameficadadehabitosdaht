package com.senac.daht.service;

import com.senac.daht.dto.request.TabelaPremioDTORequest;
import com.senac.daht.dto.response.TabelaPremioDTOResponse;
import com.senac.daht.entity.TabelaPremio;
import com.senac.daht.entity.Missao;
import com.senac.daht.entity.Premio;
import com.senac.daht.repository.TabelaPremioRepository;
import com.senac.daht.repository.MissaoRepository;
import com.senac.daht.repository.PremioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabelaPremioService {
    private final TabelaPremioRepository tabelaPremioRepository;
    private final PremioRepository premioRepository;
    @Autowired
    public TabelaPremioService(TabelaPremioRepository tabelaPremioRepository,  PremioRepository premioRepository) {
        this.tabelaPremioRepository = tabelaPremioRepository;

        this.premioRepository = premioRepository;
    }

    public List<TabelaPremioDTOResponse> listarTabelasPremios() {
        return tabelaPremioRepository.findAll().stream()
                .map(this::toResponseDTO) // Converte manualmente para DTO de resposta
                .collect(Collectors.toList());
    }

    public TabelaPremioDTOResponse listarPorId(Integer id) {
        TabelaPremio tabelaPremio = tabelaPremioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tabela de Prêmio com ID " + id + " não encontrada."));
        return toResponseDTO(tabelaPremio); // Converte manualmente para DTO de resposta
    }

    @Transactional
    public TabelaPremioDTOResponse criarTabelaPremio(TabelaPremioDTORequest tabelaPremioDTORequest) {
        TabelaPremio tabelaPremio = new TabelaPremio();
        updateTabelaPremioFromDto(tabelaPremio, tabelaPremioDTORequest); // Cria e associa dados

        TabelaPremio savedTabelaPremio = tabelaPremioRepository.save(tabelaPremio);
        return toResponseDTO(savedTabelaPremio); // Converte manualmente para DTO de resposta
    }
    @Transactional
    public void deletarTabelaPremio(Integer id) {
        tabelaPremioRepository.deleteById(id);
    }
    private TabelaPremioDTOResponse toResponseDTO(TabelaPremio tabelaPremio) {
        TabelaPremioDTOResponse dto = new TabelaPremioDTOResponse();
        dto.setId(tabelaPremio.getId());
        if (tabelaPremio.getPremio() != null) {
            dto.setNomePremio(tabelaPremio.getPremio().getNome());
        }
        return dto;
    }

    private void updateTabelaPremioFromDto(TabelaPremio tabelaPremio, TabelaPremioDTORequest dto) {
        Premio premio = premioRepository.findById(dto.getPremioId())
                .orElseThrow(() -> new EntityNotFoundException("Prêmio com ID " + dto.getPremioId() + " não encontrado."));
        tabelaPremio.setPremio(premio);
    }
}