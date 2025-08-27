package com.senac.daht.daht.service;

import com.senac.daht.daht.entity.Missao;
import com.senac.daht.daht.repository.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository){
        this.missaoRepository = missaoRepository;
    }

    public Missao listarMissoesPorId(int id){
        Missao m1 = new Missao();
        return this.missaoRepository.findById(id).orElse(null);
    }
    public Missao listarMissoesPorStatus(int status){
        Missao m1 = new Missao();
        return this.missaoRepository.findById(status).orElse(null);

    }
}
