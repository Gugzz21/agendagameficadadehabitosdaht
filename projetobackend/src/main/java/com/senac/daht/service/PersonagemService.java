package com.senac.daht.service;


import com.senac.daht.dto.request.PersonagemDTORequest;
import com.senac.daht.dto.response.PersonagemDTOResponse;
import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.PersonagemRepository;
import com.senac.daht.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {
    private final PersonagemRepository personagemRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PersonagemService(PersonagemRepository personagemRepository, UsuarioRepository usuarioRepository) {
        this.personagemRepository = personagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PersonagemDTOResponse> listarPersonagens() {
        return personagemRepository.findAll().stream()
                .map(personagem -> modelMapper.map(personagem, PersonagemDTOResponse.class))
                .collect(Collectors.toList());
    }

    public PersonagemDTOResponse listarPorId(Integer id) {
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        if (personagem != null) {
            return modelMapper.map(personagem, PersonagemDTOResponse.class);
        }
        return null;
    }

    public PersonagemDTOResponse criarPersonagem(PersonagemDTORequest personagemDTORequest) {
        Personagem personagem = modelMapper.map(personagemDTORequest, Personagem.class);
        // Associa o personagem a um usuário existente
        if (personagemDTORequest.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(personagemDTORequest.getUsuarioId()).orElse(null);
            if (usuario != null) {
                personagem.setUsuario(usuario);
            }
        }
        Personagem savedPersonagem = personagemRepository.save(personagem);
        return modelMapper.map(savedPersonagem, PersonagemDTOResponse.class);
    }

    public PersonagemDTOResponse atualizarPersonagem(Integer id, PersonagemDTORequest personagemDTORequest) {
        Personagem personagem = personagemRepository.findById(id).orElse(null);
        if (personagem != null) {
            modelMapper.map(personagemDTORequest, personagem);
            Personagem updatedPersonagem = personagemRepository.save(personagem);
            return modelMapper.map(updatedPersonagem, PersonagemDTOResponse.class);
        }
        return null;
    }

    public void deletarPersonagem(Integer id) {
        personagemRepository.deleteById(id);
    }
}