package com.senac.daht.service;

import com.senac.daht.dto.request.PremioDTORequest;
import com.senac.daht.dto.response.PremioDTOResponse;
import com.senac.daht.entity.Premio;
import com.senac.daht.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {

    private PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PremioService(PremioRepository premioRepository){
        this.premioRepository = premioRepository;
    }

    public List<Premio> listarPremios(){
        return this.premioRepository.findAll();
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelMapper.map(premioDTORequest, Premio.class);
        Premio savedPremio = premioRepository.save(premio);
        return modelMapper.map(savedPremio, PremioDTOResponse.class);
    }

    public PremioDTOResponse atualizarPremio(Integer id, PremioDTORequest premioDTORequest) {
        Premio premio = premioRepository.findById(id).orElse(null);
        if (premio != null) {
            modelMapper.map(premioDTORequest, premio);
            Premio updatedPremio = premioRepository.save(premio);
            return modelMapper.map(updatedPremio, PremioDTOResponse.class);
        }
        return null;
    }

    public void deletarPremio(Integer id) {
        premioRepository.deleteById(id);
    }
}
