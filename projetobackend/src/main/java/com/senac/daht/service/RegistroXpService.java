package com.senac.daht.service;

import com.senac.daht.dto.request.RegistroXpDTORequest;
import com.senac.daht.dto.response.RegistroXpDTOResponse;
import com.senac.daht.entity.RegistroXp;
import com.senac.daht.repository.RegistroXpRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroXpService {
    private final RegistroXpRepository registroXpRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RegistroXpService(RegistroXpRepository registroXpRepository, ModelMapper modelMapper) {
        this.registroXpRepository = registroXpRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistroXpDTOResponse> listarRegistrosXp() {
        return registroXpRepository.findAll().stream()
                .map(registro -> modelMapper.map(registro, RegistroXpDTOResponse.class))
                .collect(Collectors.toList());
    }

    public RegistroXpDTOResponse listarPorId(Integer id) {
        RegistroXp registroXp = registroXpRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de XP com ID " + id + " não encontrado."));
        return modelMapper.map(registroXp, RegistroXpDTOResponse.class);
    }

    public RegistroXpDTOResponse criarRegistroXp(RegistroXpDTORequest registroXpDTORequest) {
        RegistroXp registroXp = modelMapper.map(registroXpDTORequest, RegistroXp.class);
        RegistroXp savedRegistroXp = registroXpRepository.save(registroXp);
        return modelMapper.map(savedRegistroXp, RegistroXpDTOResponse.class);
    }

    public RegistroXpDTOResponse atualizarRegistroXp(Integer id, RegistroXpDTORequest registroXpDTORequest) {
        RegistroXp registroXp = registroXpRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro de XP com ID " + id + " não encontrado."));

        modelMapper.map(registroXpDTORequest, registroXp);

        RegistroXp updatedRegistroXp = registroXpRepository.save(registroXp);
        return modelMapper.map(updatedRegistroXp, RegistroXpDTOResponse.class);
    }

    public void deletarRegistroXp(Integer id) {
        registroXpRepository.deleteById(id);
    }
}