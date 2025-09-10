package com.senac.daht.service;

import com.senac.daht.dto.request.UsuarioDTORequest;
import com.senac.daht.dto.response.UsuarioDTOResponse;
import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.PersonagemRepository;
import com.senac.daht.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonagemRepository personagemRepository;
    private final ModelMapper modelMapper;
    // O PasswordEncoder foi removido daqui

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, PersonagemRepository personagemRepository) { // E daqui do construtor
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.personagemRepository = personagemRepository;
    }

    public List<UsuarioDTOResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class))
                .collect(Collectors.toList());
    }

    public UsuarioDTOResponse listarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
        return modelMapper.map(usuario, UsuarioDTOResponse.class);
    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest) {
        if (usuarioRepository.findByEmail(usuarioDTORequest.getEmail()).isPresent()) {
            throw new EntityExistsException("Este e-mail já está cadastrado.");
        }

        Usuario usuario = modelMapper.map(usuarioDTORequest, Usuario.class);
        usuario.setSenha(usuarioDTORequest.getSenha());
        usuario.setStatus(1);

        Usuario savedUsuario = usuarioRepository.save(usuario);

        Personagem personagem = new Personagem();
        personagem.setVida(100.0);
        personagem.setOuro(0.0);
        personagem.setXp(0.0);
        personagem.setUsuario(savedUsuario);

        personagemRepository.save(personagem);

        return modelMapper.map(savedUsuario, UsuarioDTOResponse.class);
    }

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

    public void deletarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}