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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final RegistroOuroRepository registroOuroRepository;
    private final RegistroXpRepository registroXpRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonagemService(PersonagemRepository personagemRepository, UsuarioRepository usuarioRepository, RegistroOuroRepository registroOuroRepository, RegistroXpRepository registroXpRepository, ModelMapper modelMapper) {
        this.personagemRepository = personagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.registroOuroRepository = registroOuroRepository;
        this.registroXpRepository = registroXpRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public PersonagemDTOResponse criarPersonagem(PersonagemDTORequest personagemDTORequest) {
        Usuario usuario = usuarioRepository.findById(personagemDTORequest.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + personagemDTORequest.getUsuarioId() + " não encontrado."));

        RegistroOuro registroOuro = new RegistroOuro();
        registroOuro.setQuantidade(0.0);
        RegistroOuro savedRegistroOuro = registroOuroRepository.save(registroOuro);

        RegistroXp registroXp = new RegistroXp();
        registroXp.setQuantidade(0.0);
        RegistroXp savedRegistroXp = registroXpRepository.save(registroXp);

        Personagem personagem = new Personagem();
        personagem.setVida(personagemDTORequest.getVida());
        personagem.setOuro(personagemDTORequest.getOuro());
        personagem.setXp(Double.valueOf(personagemDTORequest.getXp()));
        personagem.setUsuario(usuario);
        personagem.setRegistroOuro(savedRegistroOuro);
        personagem.setRegistroXp(savedRegistroXp);

        Personagem savedPersonagem = personagemRepository.save(personagem);

        return modelMapper.map(savedPersonagem, PersonagemDTOResponse.class);
    }

    @Transactional(readOnly = true)
    public List<PersonagemDTOResponse> listarPersonagens() {
        return personagemRepository.listarTodosPersonagens().stream()
                .map(personagem -> modelMapper.map(personagem, PersonagemDTOResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonagemDTOResponse listarPorId(Integer id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + id + " não encontrado."));
        return modelMapper.map(personagem, PersonagemDTOResponse.class);
    }

    @Transactional
    public PersonagemDTOResponse atualizarPersonagem(Integer id, PersonagemDTORequest personagemDTORequest) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem com ID " + id + " não encontrado."));

        modelMapper.map(personagemDTORequest, personagem);

        if (personagemDTORequest.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(personagemDTORequest.getUsuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + personagemDTORequest.getUsuarioId() + " não encontrado."));
            personagem.setUsuario(usuario);
        }

        Personagem updatedPersonagem = personagemRepository.save(personagem);
        return modelMapper.map(updatedPersonagem, PersonagemDTOResponse.class);
    }

    @Transactional
    public void deletarPersonagem(Integer id) {
        if (!personagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Personagem com ID " + id + " não encontrado para deleção.");
        }
        personagemRepository.deleteById(id);
    }
}