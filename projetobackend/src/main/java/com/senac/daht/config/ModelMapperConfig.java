package com.senac.daht.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.PropertyMap;
import com.senac.daht.dto.response.PersonagemDTOResponse;
import com.senac.daht.entity.Personagem;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Mapeamento explícito para a conversão de Personagem para PersonagemDTOResponse
        modelMapper.addMappings(new PropertyMap<Personagem, PersonagemDTOResponse>() {
            @Override
            protected void configure() {
                // Mapeia o nome do usuário aninhado na entidade Personagem para o DTO
                map().setNomeUsuario(source.getUsuario().getNome());
            }
        });

        // ... (outros mapeamentos se necessário)

        return modelMapper;
    }
}