package br.com.leonardoferreira.place_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import br.com.leonardoferreira.place_service.domain.PlaceRepository;
import br.com.leonardoferreira.place_service.domain.PlaceService;

@Configuration
@EnableR2dbcAuditing //habilita a auditoria do R2DBC (necessário para preencher a data de criação automaticamente pelo framework, não pelo banco)
public class PlaceConfig {
    
    @Bean
    //dessa forma o serviço está configurado para sempre receber um repositório
    PlaceService placeService(PlaceRepository placeRepository){  
        return new PlaceService(placeRepository);
    }
}
