package br.com.leonardoferreira.place_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import br.com.leonardoferreira.place_service.api.PlaceRequest;

// estamos usando o teste de integração

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // montando um ambiente web que roda em uma porta aleatória 
class PlaceServiceApplicationTests {

	@Autowired
	WebTestClient webTestClient; //é um cliente web reativo para fazer testes

	@Test
	public void testCreatePlaceSuccess(){
		var name = "Valid Name";
		var state = "State Valid";
		var slug = "valid-name";

		webTestClient
			.post() //informamos o verbo (post, get, put ou delete)
			.uri("/places") //a uri (o caminho)
			.bodyValue( // corpo da equisição, as informações que precisamos passar
				new PlaceRequest(name, state)
			)
			.exchange() //efetuando a requisição
			.expectBody() //expectativa do corpo da resposta
			.jsonPath("name").isEqualTo(name) //criando o Json e verificando se o nome recebido no Json é igual ao nome esperado
			.jsonPath("state").isEqualTo(state)
			.jsonPath("slug").isEqualTo(slug)
			.jsonPath("createdAt").isNotEmpty();

}		

	@Test
    public void testCreatePlaceFailure(){
		var name = ""; //precisa ser validada no controller
		var state = ""; //precisa ser validada no controller

		webTestClient
			.post() //informamos o verbo (post, get, put ou delete)
			.uri("/places") //a uri (o caminho)
			.bodyValue( // corpo da equisição, as informações que precisamos passar
				new PlaceRequest(name, state)
			)
			.exchange() 
			.expectStatus().isBadRequest(); //status retornado quando passamos dados inválidos
	}

	

}


/*
 * Para conhecimento
 * TDD - Testes antes do desenvolvimento
 */