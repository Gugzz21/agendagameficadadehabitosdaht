package com.senac.daht.service;

import com.senac.daht.dto.request.UsuarioDTORequest;
import com.senac.daht.dto.response.UsuarioDTOResponse;
import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.RegistroOuro;
import com.senac.daht.entity.RegistroXp;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.PersonagemRepository;
import com.senac.daht.repository.RegistroOuroRepository;
import com.senac.daht.repository.RegistroXpRepository;
import com.senac.daht.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional; // Importação necessária

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonagemRepository personagemRepository;
    private final RegistroXpRepository registroXpRepository;
    private final RegistroOuroRepository registroOuroRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper,
                          PersonagemRepository personagemRepository, RegistroXpRepository registroXpRepository,
                          RegistroOuroRepository registroOuroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.personagemRepository = personagemRepository;
        this.registroXpRepository = registroXpRepository;
        this.registroOuroRepository = registroOuroRepository;
    }

    @Transactional
    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest) {
        if (usuarioRepository.findByEmail(usuarioDTORequest.getEmail()).isPresent()) {
            throw new EntityExistsException("Este e-mail já está cadastrado.");
        }

        Usuario usuario = modelMapper.map(usuarioDTORequest, Usuario.class);
        usuario.setSenha(usuarioDTORequest.getSenha());
        usuario.setStatus(1);
        Usuario savedUsuario = usuarioRepository.save(usuario);

        RegistroXp registroXp = new RegistroXp();
        registroXp.setQuantidade(0.0);
        RegistroXp savedRegistroXp = registroXpRepository.save(registroXp);

        RegistroOuro registroOuro = new RegistroOuro();
        registroOuro.setQuantidade(0.0);
        RegistroOuro savedRegistroOuro = registroOuroRepository.save(registroOuro);

        Personagem personagem = new Personagem();
        personagem.setVida(100.0);
        personagem.setOuro(0.0);
        personagem.setXp(0.0);
        personagem.setUsuario(savedUsuario); // Associa o usuário ao personagem
        personagem.setRegistroXp(savedRegistroXp);
        personagem.setRegistroOuro(savedRegistroOuro);

        personagemRepository.save(personagem);

        return modelMapper.map(savedUsuario, UsuarioDTOResponse.class);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTOResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTOResponse listarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
        return modelMapper.map(usuario, UsuarioDTOResponse.class);
    }

    @Transactional
    public UsuarioDTOResponse atualizarUsuario(Integer id, UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));

        modelMapper.map(usuarioDTORequest, usuario);

        if(usuarioDTORequest.getSenha() != null && !usuarioDTORequest.getSenha().isEmpty()) {
            usuario.setSenha(usuarioDTORequest.getSenha());
        }

        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(updatedUsuario, UsuarioDTOResponse.class);
    }

    @Transactional
    public void deletarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));

        // Com a configuração de `orphanRemoval = true` na entidade Usuario,
        // o JPA irá deletar o Personagem e suas entidades dependentes automaticamente
        // ao remover o usuário.
        usuarioRepository.delete(usuario);
    }
}