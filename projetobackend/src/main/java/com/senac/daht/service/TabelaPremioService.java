package com.senac.daht.service;

import com.senac.daht.entity.TabelaPremio;
import com.senac.daht.repository.TabelaPremioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaPremioService {

    private TabelaPremioRepository tabelaPremioRepository;

    public TabelaPremioService(TabelaPremioRepository tabelaPremioRepository){
        this.tabelaPremioRepository = tabelaPremioRepository;
    }

    public List<TabelaPremio> listarPremios(){return this.tabelaPremioRepository.findAll(); }

}
