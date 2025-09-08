package com.senac.daht.service;

import com.senac.daht.dto.request.PremioDTORequest;
import com.senac.daht.dto.response.PremioDTOResponse;
import com.senac.daht.entity.Premio;
import com.senac.daht.repository.PremioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremioService {
    private final PremioRepository premioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PremioService(PremioRepository premioRepository, ModelMapper modelMapper) {
        this.premioRepository = premioRepository;
        this.modelMapper = modelMapper;
    }

    public List<PremioDTOResponse> listarPremios() {
        return premioRepository.findAll().stream()
                .map(premio -> modelMapper.map(premio, PremioDTOResponse.class))
                .collect(Collectors.toList());
    }

    public PremioDTOResponse listarPorId(Integer id) {
        Premio premio = premioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prêmio com ID " + id + " não encontrado."));
        return modelMapper.map(premio, PremioDTOResponse.class);
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelMapper.map(premioDTORequest, Premio.class);
        Premio savedPremio = premioRepository.save(premio);
        return modelMapper.map(savedPremio, PremioDTOResponse.class);
    }

    public PremioDTOResponse atualizarPremio(Integer id, PremioDTORequest premioDTORequest) {
        Premio premio = premioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prêmio com ID " + id + " não encontrado."));

        modelMapper.map(premioDTORequest, premio);
        Premio updatedPremio = premioRepository.save(premio);
        return modelMapper.map(updatedPremio, PremioDTOResponse.class);
    }

    public void deletarPremio(Integer id) {
        premioRepository.deleteById(id);
    }
}