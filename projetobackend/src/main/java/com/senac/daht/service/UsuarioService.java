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

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonagemRepository personagemRepository;
    private final RegistroXpRepository registroXpRepository;
    private final RegistroOuroRepository registroOuroRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, PersonagemRepository personagemRepository, RegistroXpRepository registroXpRepository, RegistroOuroRepository registroOuroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.personagemRepository = personagemRepository;
        this.registroXpRepository = registroXpRepository;
        this.registroOuroRepository = registroOuroRepository;
    }

    // ... (outros métodos)

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest) {
        if (usuarioRepository.findByEmail(usuarioDTORequest.getEmail()).isPresent()) {
            throw new EntityExistsException("Este e-mail já está cadastrado.");
        }

        Usuario usuario = modelMapper.map(usuarioDTORequest, Usuario.class);
        usuario.setSenha(usuarioDTORequest.getSenha());
        usuario.setStatus(1);
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // 1. Crie e salve RegistroXp e RegistroOuro para gerar seus IDs
        RegistroXp registroXp = new RegistroXp();
        registroXp.setQuantidade(0);
        RegistroXp savedRegistroXp = registroXpRepository.save(registroXp);

        RegistroOuro registroOuro = new RegistroOuro();
        registroOuro.setQuantidade(0);
        RegistroOuro savedRegistroOuro = registroOuroRepository.save(registroOuro);

        // 2. Crie o Personagem e associe os objetos salvos
        Personagem personagem = new Personagem();
        personagem.setVida(100.0);
        personagem.setOuro(0.0);
        personagem.setXp(0.0);
        personagem.setUsuario(savedUsuario);

        // Associa os registros recém-criados
        personagem.setRegistroXp(savedRegistroXp);
        personagem.setRegistroOuro(savedRegistroOuro);

        // 3. Salva o Personagem
        personagemRepository.save(personagem);

        return modelMapper.map(savedUsuario, UsuarioDTOResponse.class);
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