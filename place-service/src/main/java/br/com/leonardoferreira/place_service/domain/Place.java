package br.com.leonardoferreira.place_service.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;

// função record gera metodos como construtor, equals(), hashCode() e toString()
// utilizado para encapsulamento sem a necessidade de escrever código muito repetitivo
@Table("places")
public record Place(
    @Id
    Long id,
    @NotBlank
    String name, 
    @NotBlank
    String slug, 
    @NotBlank
    String state, 

    @CreatedDate // automaticamente já insere a data da criação 
    LocalDateTime createdAt
    ){
        public Place withSlug(String slug) {
            return new Place(id, name, slug, state, createdAt);
        }

}


