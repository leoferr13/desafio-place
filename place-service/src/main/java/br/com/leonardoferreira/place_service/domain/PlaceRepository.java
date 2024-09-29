package br.com.leonardoferreira.place_service.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// os repositorios no Spring Data sempre são interfaces
// estamos extendendo para trabalhar de forma reativa no Spring Data
public interface PlaceRepository extends ReactiveCrudRepository<Place, Long>{

    
} 
