package br.com.leonardoferreira.place_service.domain;

import com.github.slugify.Slugify;

import br.com.leonardoferreira.place_service.api.PlaceRequest;
import br.com.leonardoferreira.place_service.web.PlaceMapper;
import reactor.core.publisher.Flux;
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
    public Mono<Place> create(PlaceRequest request) { // recebendo uma PlaceRequest(entrada) 
        var place = new Place(null, request.name(), slg.slugify(request.name()), request.state(),
        null); //montando a variável place com o id nulo
        return placeRepository.save(place); //salvando no banco 
    }

    public Flux<Place> listAll(){ //flux retorna uma lista de place
        return placeRepository.findAll(); //retorna todas as informações da lista
    }
    
    public Mono<Place> update(Long id, PlaceRequest request) { // mono retorna um place
        return placeRepository.findById(id) //procura o id desse place
        .map(place -> PlaceMapper.updatePlaceFromDTO(request, place)) //mapeia esse place e retorna uma placeRequest (atualizado)
        .map(place -> place.withSlug(slg.slugify(place.name()))) // atualiza o slug desse place atualizado
        .flatMap((placeRepository::save)); //salvo no repositório
    }

    public Mono<Void> delete(Long id){ // esse mono não retorna nada porque eu só vou trabalhar com o ID
        return placeRepository.deleteById(id);
    }
}
