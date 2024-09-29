package br.com.leonardoferreira.place_service.api;

import java.time.LocalDateTime;

//resposta das informações
//usando os DTO

public record PlaceResponse(
    String name, 
    String slug, 
    String state, 
    LocalDateTime createdAt
){

}

    