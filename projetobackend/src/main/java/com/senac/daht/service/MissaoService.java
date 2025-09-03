package com.senac.daht.service;

import com.senac.daht.entity.Missao;
import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.MissaoRepository;
import com.senac.daht.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    UsuarioRepository usuarioRepository;

    public List<Missao> listarMissoes(int userId, int status) {
        Usuario userOpt = usuarioRepository.findById(userId).orElse(null);

        if (userOpt!=null) {
            Usuario usuario = userOpt;
            Personagem personagem = usuario.getPersonagem();

            if (personagem != null) {
                return missaoRepository.findByPersonagemAndStatus(personagem, status);
            }

        }
        return Collections.emptyList();
    }
}

