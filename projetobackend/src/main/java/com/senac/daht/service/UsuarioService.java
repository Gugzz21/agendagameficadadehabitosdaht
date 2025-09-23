package com.senac.daht.service;

import com.senac.daht.dto.CreateUsuarioDto;
import com.senac.daht.dto.LoginUsuarioDto;
import com.senac.daht.dto.RecoveryJwtTokenDto;
import com.senac.daht.dto.request.UsuarioDTORequest;
import com.senac.daht.dto.response.UsuarioDTOResponse;
import com.senac.daht.entity.*;
import com.senac.daht.repository.PersonagemRepository;
import com.senac.daht.repository.RegistroOuroRepository;
import com.senac.daht.repository.RegistroXpRepository;
import com.senac.daht.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private com.example.demo.config.SecurityConfig securityConfiguration;


    public RecoveryJwtTokenDto authenticateUser(LoginUsuarioDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUsuarioDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        Usuario newUser = new Usuario();
        newUser.setEmail(createUserDto.email());
        // Codifica a senha do usuário com o algoritmo bcrypt
        newUser.setSenha(securityConfiguration.passwordEncoder().encode(createUserDto.senha()));
        // Atribui ao usuário uma permissão específica
        Role role = new Role();

        role.setName(createUserDto.role());
        newUser.setRoles(List.of(role));


        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
}