Código feito para treinar meu desenvolvimento em Springboot
Meu objetivo foi replicar uma desafio feito pela Giuliana Bezerra e entender a lógica
Fiz apenas o end point(Post) e estou me desafiando para fazer os demais.
Apliquei os testes de integração
Utilizei o Imsonia para testar meu endpoint

URL
    - http://localhost:8082/places

Nele utilizei as dependências : 
    - WebFlux : Alternativa reativa que escala muito bem soluções com serviços Web em Javae Spring 
	- Spring Data R2DBC : Interagir com o banco também de forma reativa
	- Spring DevTools : Fazer teste sem reiniciar a aplicação
	- H2 : Para realizar os testes no Banco de Dados
	- Validation I/O : usada para validar uma requisição @Valid

Tecnologias
    - Spring Boot
    - Spring WebFlux
    - Spring Data + R2DBC