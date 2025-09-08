package com.senac.daht.service;

import com.senac.daht.dto.request.PersonagemDTORequest;
import com.senac.daht.dto.response.PersonagemDTOResponse;
import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.PersonagemRepository;
import com.senac.daht.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper; // Adicione 'final' para garantir a imutabilidade

    // Construtor corrigido com todas as dependências
    public PersonagemService(PersonagemRepository personagemRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.personagemRepository = personagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<PersonagemDTOResponse> listarPersonagens() {
        return personagemRepository.findAll().stream()
                .map(personagem -> modelMapper.map(personagem, PersonagemDTOResponse.class))
                .collect(Collectors.toList());
    }

    public PersonagemDTOResponse listarPorId(Integer id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + id + " não encontrado."));
        return modelMapper.map(personagem, PersonagemDTOResponse.class);
    }

    public PersonagemDTOResponse criarPersonagem(PersonagemDTORequest personagemDTORequest) {
        Personagem personagem = modelMapper.map(personagemDTORequest, Personagem.class);

        // Associa o personagem a um usuário existente
        if (personagemDTORequest.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(personagemDTORequest.getUsuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + personagemDTORequest.getUsuarioId() + " não encontrado."));
            personagem.setUsuario(usuario);
        }

        Personagem savedPersonagem = personagemRepository.save(personagem);
        return modelMapper.map(savedPersonagem, PersonagemDTOResponse.class);
    }

    public PersonagemDTOResponse atualizarPersonagem(Integer id, PersonagemDTORequest personagemDTORequest) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + id + " não encontrado."));

        // Mapeia os dados do DTO para o objeto de entidade existente
        modelMapper.map(personagemDTORequest, personagem);

        Personagem updatedPersonagem = personagemRepository.save(personagem);
        return modelMapper.map(updatedPersonagem, PersonagemDTOResponse.class);
    }

    public void deletarPersonagem(Integer id) {
        personagemRepository.deleteById(id);
    }
}