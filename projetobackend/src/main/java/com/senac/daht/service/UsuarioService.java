package com.senac.daht.service;

import com.senac.daht.dto.request.UsuarioDTORequest;
import com.senac.daht.dto.response.UsuarioDTOResponse;
import com.senac.daht.dto.response.UsuarioDTOUpdateResponse;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public UsuarioDTOResponse listarPorId(Integer id) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            return modelMapper.map(usuario, UsuarioDTOResponse.class);
        }
        return null;
    }

    public List<UsuarioDTOResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class))
                .collect(Collectors.toList());
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = modelMapper.map(usuarioDTORequest, Usuario.class);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(savedUsuario, UsuarioDTOResponse.class);
    }


    public UsuarioDTOResponse atualizarUsuario(Integer id, UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            modelMapper.map(usuarioDTORequest, usuario);
            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return modelMapper.map(updatedUsuario, UsuarioDTOResponse.class);
        }
        return null;
    }

    public UsuarioDTOUpdateResponse atualizarStatusUsuario(Integer id, UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = findById(id);
        if (usuario != null) {
            usuario.setStatus(usuarioDTORequest.getStatus());
            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return modelMapper.map(updatedUsuario, UsuarioDTOUpdateResponse.class);
        }
        return null;
    }

    public void deletarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
