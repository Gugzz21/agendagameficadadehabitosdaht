package com.senac.daht.daht.service;

import com.senac.daht.daht.entity.Missao;
import com.senac.daht.daht.entity.Personagem;
import com.senac.daht.daht.entity.Usuario;
import com.senac.daht.daht.repository.MissaoRepository;
import com.senac.daht.daht.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

