package br.com.leonardoferreira.place_service.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardoferreira.place_service.api.PlaceRequest;
import br.com.leonardoferreira.place_service.api.PlaceResponse;
import br.com.leonardoferreira.place_service.domain.PlaceService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
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
    //ResponseEntity representa uma resposta HTTP, permitindo que eu controle corpo da resposta, cabeçalhos e status http
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request){ //@RequestBody indicando que o place é o corpo da requisição 
        var response = placeService.create(request).map(PlaceMapper::fromPlaceToResponse); //a partir do place criado (request), mapeamos o resultado (dentro do PlaceMapper) e transformamos em um PlaceResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(response); //retornando o response 
    }
    
    /*
     Mono : Trabalha com um unico valor
     Flux : Trabalha com multiplos valores
     */

    @GetMapping
    public ResponseEntity<Flux<PlaceResponse>> list() { //Flux é apropriado para trabalhar com listas de dados de forma reativa
        var response = placeService.listAll().map(PlaceMapper::fromPlaceToResponse); //convertendo tipo Place para PlaceResponse / :: é uma referência ao metodo, ele chama o método static fromPlaceToResponse da classe PlaceMapper
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    
    @PutMapping("/{id}")
    public Mono<PlaceResponse> update(@PathVariable("id") Long id, @Valid @RequestBody PlaceRequest request){
        return placeService.update(id, request).map(PlaceMapper::fromPlaceToResponse);
}

    @DeleteMapping("/{id}")
    //metodo retorno um HTTP sem corpo
    public Mono<ResponseEntity<Object>> delete(@PathVariable("id") Long id){
        return placeService.delete(id)
            .then(Mono.just(ResponseEntity.noContent().build())) //apos a execução bem sucedida, retorna a resposta do HTTP 204 no content
            .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build())); //caso o id não for encontrado, retorna uma resposta http 404 not found
    }
}