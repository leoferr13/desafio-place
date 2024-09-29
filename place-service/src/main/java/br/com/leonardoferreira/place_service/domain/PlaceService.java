package br.com.leonardoferreira.place_service.domain;

import com.github.slugify.Slugify;

import br.com.leonardoferreira.place_service.api.PlaceRequest;
import reactor.core.publisher.Mono;

// o service é responsável pelas regras de negócio na aplicação
// ele que faz todo o trabalho, cria, lista, atualiza e deleta (CRUD)

public class PlaceService {
    
    private PlaceRepository placeRepository;
    private Slugify slg;

    // o repositório tem que ser injetado via spring
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build(); //buildando com as configurações padrão 
    }

    //como estamos trabalhando com api reativa, retornaos o MONO dela
    //criando a entidade com os dados recebidos e salvando no repositório 
    // slg.slugify(placeRequest.name()) esta recebendo o nome e transformando em algo do tipo (nome-slug)
    public Mono<Place> create(PlaceRequest placeRequest) { // recebendo uma PlaceRequest(entrada) 
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(),
        null); //montando a variável place com o id nulo
        return placeRepository.save(place); //salvando no banco 
    }
    
}
