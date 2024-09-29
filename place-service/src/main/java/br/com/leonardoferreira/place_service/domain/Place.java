package br.com.leonardoferreira.place_service.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;

// função record gera metodos como construtor, equals(), hashCode() e toString()
// utilizado para encapsulamento sem a necessidade de escrever código muito repetitivo
@Table("places")
public record Place(
    Long id,
    String name, 
    String slug, 
    String state, 

    @CreatedDate // automaticamente já insere a data da criação 
    LocalDateTime createdAt
    ){
    

}