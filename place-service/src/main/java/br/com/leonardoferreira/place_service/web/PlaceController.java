package br.com.leonardoferreira.place_service.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardoferreira.place_service.api.PlaceRequest;
import br.com.leonardoferreira.place_service.api.PlaceResponse;
import br.com.leonardoferreira.place_service.domain.PlaceService;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

// O controller trás as informações do service e expoe atráves de um url que recebe um JSON (se necessário) ou retorna.

@RestController
@RequestMapping("/places")
public class PlaceController {
    private PlaceService placeService; // precisamos configurar o service para o nosso projeto (criado na classe PlaceConfig,java)

        // criando o construtor para receber o serivce, porém, podemos utilizar o @Autoriwed também 
       public PlaceController(PlaceService placeService){
        this.placeService = placeService;
    }

    @PostMapping
    //meu controlador esta retornando um PlaceResponse(resposta) e recebendo uma PlaceRequest(entrada)
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request){ //@RequestBody indicando que o place é o corpo da requisição 
        var response = placeService.create(request).map(PlaceMapper::fromPlaceToResponse); //a partir do place criado (request), mapeamos o resultado (dentro do PlaceMapper) e transformamos em um PlaceResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(response); //retornando o response 
    }
    
}
