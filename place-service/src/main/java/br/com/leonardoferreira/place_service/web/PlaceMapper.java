package br.com.leonardoferreira.place_service.web;

import br.com.leonardoferreira.place_service.api.PlaceRequest;
import br.com.leonardoferreira.place_service.api.PlaceResponse;
import br.com.leonardoferreira.place_service.domain.Place;
import org.springframework.util.StringUtils;


//Responsável por fazer o mapeamento das informações 
//percorre as informações da classe PlaceResponse
public class PlaceMapper {

    //esse método atualiza a instância Place
    public static Place updatePlaceFromDTO(PlaceRequest request, Place place){
        //StringUtils.hasText : verifica se a String não é nula ? usa um novo name : usa o nome atual
        final String name = StringUtils.hasText(request.name()) ? request.name() : place.name(); 
        final String state = StringUtils.hasText(request.state()) ? request.state() : place.state();

        return new Place(place.id(), name, place.slug(), state, place.createdAt());
    }

    public static PlaceResponse fromPlaceToResponse(Place place) { //recebe Place
        return new PlaceResponse(place.name(), place.slug(), place.state(), place.createdAt()); //resposta de acordo com o Place
    }
    
}
