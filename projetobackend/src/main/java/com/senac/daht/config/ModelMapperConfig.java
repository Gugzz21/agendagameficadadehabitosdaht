package com.senac.daht.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.PropertyMap;
import com.senac.daht.dto.request.TabelaPremioDTORequest;
import com.senac.daht.entity.TabelaPremio;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // Mapeamento explícito para TabelaPremio
        modelMapper.addMappings(new PropertyMap<TabelaPremioDTORequest, TabelaPremio>() {
            @Override
            protected void configure() {
                // Ignora o mapeamento automático do ID, já que o DTO tem múltiplos IDs de FK.
                skip(destination.getId());
            }
        });

        return modelMapper;
    }
}