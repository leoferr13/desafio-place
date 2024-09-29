package br.com.leonardoferreira.place_service.web;

import br.com.leonardoferreira.place_service.api.PlaceResponse;
import br.com.leonardoferreira.place_service.domain.Place;


//Responsável por fazer o mapeamento das informações 
//percorre as informações da classe PlaceResponse
public class PlaceMapper {
    public static PlaceResponse fromPlaceToResponse(Place place) { //recebe Place
        return new PlaceResponse(place.name(), place.slug(), place.state(), place.createdAt()); //resposta de acordo com o Place
    }
    
}
