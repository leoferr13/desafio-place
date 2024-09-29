package br.com.leonardoferreira.place_service.api;

import jakarta.validation.constraints.NotBlank;

//requisição das informações
//usando os DTO

public record PlaceRequest(
    @NotBlank    
    String name, 
    @NotBlank
    String state
   
){

}