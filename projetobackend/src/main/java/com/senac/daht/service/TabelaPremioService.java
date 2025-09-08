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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabelaPremioService {
    private final TabelaPremioRepository tabelaPremioRepository;
    private final MissaoRepository missaoRepository;
    private final PremioRepository premioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TabelaPremioService(TabelaPremioRepository tabelaPremioRepository, MissaoRepository missaoRepository, PremioRepository premioRepository, ModelMapper modelMapper) {
        this.tabelaPremioRepository = tabelaPremioRepository;
        this.missaoRepository = missaoRepository;
        this.premioRepository = premioRepository;
        this.modelMapper = modelMapper;
    }

    public List<TabelaPremioDTOResponse> listarTabelasPremios() {
        return tabelaPremioRepository.findAll().stream()
                .map(tabela -> modelMapper.map(tabela, TabelaPremioDTOResponse.class))
                .collect(Collectors.toList());
    }

    public TabelaPremioDTOResponse listarPorId(Integer id) {
        TabelaPremio tabelaPremio = tabelaPremioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tabela de Prêmio com ID " + id + " não encontrada."));
        return modelMapper.map(tabelaPremio, TabelaPremioDTOResponse.class);
    }

    public TabelaPremioDTOResponse criarTabelaPremio(TabelaPremioDTORequest tabelaPremioDTORequest) {
        TabelaPremio tabelaPremio = modelMapper.map(tabelaPremioDTORequest, TabelaPremio.class);

        Premio premio = premioRepository.findById(tabelaPremioDTORequest.getPremioId())
                .orElseThrow(() -> new EntityNotFoundException("Prêmio com ID " + tabelaPremioDTORequest.getPremioId() + " não encontrado."));
        tabelaPremio.setPremio(premio);

        Missao missao = missaoRepository.findById(tabelaPremioDTORequest.getMissaoId())
                .orElseThrow(() -> new EntityNotFoundException("Missão com ID " + tabelaPremioDTORequest.getMissaoId() + " não encontrada."));
        tabelaPremio.setMissao(missao);

        TabelaPremio savedTabelaPremio = tabelaPremioRepository.save(tabelaPremio);
        return modelMapper.map(savedTabelaPremio, TabelaPremioDTOResponse.class);
    }

    public TabelaPremioDTOResponse atualizarTabelaPremio(Integer id, TabelaPremioDTORequest tabelaPremioDTORequest) {
        TabelaPremio tabelaPremio = tabelaPremioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tabela de Prêmio com ID " + id + " não encontrada."));

        modelMapper.map(tabelaPremioDTORequest, tabelaPremio);

        Premio premio = premioRepository.findById(tabelaPremioDTORequest.getPremioId())
                .orElseThrow(() -> new EntityNotFoundException("Prêmio com ID " + tabelaPremioDTORequest.getPremioId() + " não encontrado."));
        tabelaPremio.setPremio(premio);

        Missao missao = missaoRepository.findById(tabelaPremioDTORequest.getMissaoId())
                .orElseThrow(() -> new EntityNotFoundException("Missão com ID " + tabelaPremioDTORequest.getMissaoId() + " não encontrada."));
        tabelaPremio.setMissao(missao);

        TabelaPremio updatedTabelaPremio = tabelaPremioRepository.save(tabelaPremio);
        return modelMapper.map(updatedTabelaPremio, TabelaPremioDTOResponse.class);
    }

    public void deletarTabelaPremio(Integer id) {
        tabelaPremioRepository.deleteById(id);
    }
}