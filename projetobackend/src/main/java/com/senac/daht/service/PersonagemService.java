package com.senac.daht.service;

import com.senac.daht.dto.request.PersonagemDTORequest;
import com.senac.daht.dto.response.PersonagemDTOResponse;
import com.senac.daht.entity.Personagem;
import com.senac.daht.entity.RegistroOuro;
import com.senac.daht.entity.RegistroXp;
import com.senac.daht.entity.Usuario;
import com.senac.daht.repository.PersonagemRepository;
import com.senac.daht.repository.RegistroOuroRepository;
import com.senac.daht.repository.RegistroXpRepository;
import com.senac.daht.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final RegistroOuroRepository registroOuroRepository;
    private final RegistroXpRepository registroXpRepository;

    public PersonagemService(PersonagemRepository personagemRepository, UsuarioRepository usuarioRepository, RegistroOuroRepository registroOuroRepository, RegistroXpRepository registroXpRepository) {
        this.personagemRepository = personagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.registroOuroRepository = registroOuroRepository;
        this.registroXpRepository = registroXpRepository;
    }

    public List<PersonagemDTOResponse> listarPersonagens() {
        // Agora usa o novo método que filtra por status ativo (1)
        return personagemRepository.findByUsuarioStatus(1).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PersonagemDTOResponse listarPorId(Integer id) {
        Personagem personagem = personagemRepository.findById(id) // Remova o Lock do repositório para este método.
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + id + " não encontrado."));
        return toResponseDTO(personagem);
    }

    public PersonagemDTOResponse criarPersonagem(PersonagemDTORequest personagemDTORequest) {
        Personagem personagem = new Personagem();
        updatePersonagemFromDto(personagem, personagemDTORequest); // Cria e associa dados

        // Crie e salve as entidades relacionadas antes de salvar o Personagem
        RegistroOuro registroOuro = new RegistroOuro();
        registroOuro.setQuantidade(personagemDTORequest.getOuro());
        RegistroOuro savedRegistroOuro = registroOuroRepository.save(registroOuro);
        personagem.setRegistroOuro(savedRegistroOuro);

        RegistroXp registroXp = new RegistroXp();
        registroXp.setQuantidade(personagemDTORequest.getXp());
        RegistroXp savedRegistroXp = registroXpRepository.save(registroXp);
        personagem.setRegistroXp(savedRegistroXp);

        Personagem savedPersonagem = personagemRepository.save(personagem);
        return toResponseDTO(savedPersonagem);
    }

    @Transactional // A transação é necessária para o bloqueio pessimista
    public PersonagemDTOResponse atualizarPersonagem(Integer id, PersonagemDTORequest personagemDTORequest) {
        // O método findById agora usará o bloqueio pessimista
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + id + " não encontrado."));

        // ... lógica de atualização ...

        Personagem updatedPersonagem = personagemRepository.save(personagem);
        return toResponseDTO(updatedPersonagem);
    }

    public void deletarPersonagem(Integer id) {
        personagemRepository.deleteById(id);
    }

    // Métodos utilitários para conversão
    private PersonagemDTOResponse toResponseDTO(Personagem personagem) {
        PersonagemDTOResponse dto = new PersonagemDTOResponse();
        dto.setId(personagem.getId());
        dto.setVida(personagem.getVida());
        dto.setOuro(personagem.getOuro());
        dto.setXp(personagem.getXp());
        if (personagem.getUsuario() != null) {
            dto.setNomeUsuario(personagem.getUsuario().getNome());
        }
        return dto;
    }

    private void updatePersonagemFromDto(Personagem personagem, PersonagemDTORequest dto) {
        personagem.setVida(dto.getVida());
        personagem.setOuro(dto.getOuro());
        personagem.setXp(dto.getXp());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + dto.getUsuarioId() + " não encontrado."));
        personagem.setUsuario(usuario);
    }
}