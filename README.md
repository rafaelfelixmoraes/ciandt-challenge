# Desafio CI&T

### Desafio Developer Hands On - Centro Logístico

Projeto desenvolvido com as seguintes tecnologias:

  - Java 8
  - Framework Spring (boot, data, mvc)
  - JPA/Hibernate
  - Banco de dados em memória (H2)
  - Junit com MockMVC
  - APIs Rest
  - Swagger
  - Spring Actuator


Para executar o projeto, siga os seguintes passos:

- Realize o clone do repositório e dentro da IDE desejada e em seguida acesse Arquivo/Importar, selecione a opção Maven/Existing Maven Projects, na próxima tela aponte para o diretório para onde foi feito o clone do projeto do git, e no final clique em Finalizar.

- Em seguida, realize o build do projeto, Clique com o botão direito do mouse sobre a pasta do projeto e navegue até Maven > Update Project. Na próxima tela, clique em OK e aguarde o build do projeto.

- Abra o package com.rafaelfelix.ciandt.challenge; 

- Clique na classe CiandtChallengeApplication.java 

- Clique com o botão direito do mouse  e navegue para Run As > Java Application. No console, Aguarde o servidor subir, enquanto isso abra o browser (IE, Firefox, Chrome). 

## Lista das APIs disponibilizadas

#### - Listagem dos dados de todos os conjuntos de cargas de veículos
```
http://localhost:8080/delivery/all
```

#### - Inserção de dados de um conjunto de cargas para um veículo
```
http://localhost:8080/delivery
```
#### Payload de entrada
```
{
  "vehicle" : "",
	"deliveryId" : "",
	"packages" : [
		{ "id": 0, "weight": 0},
		{ "id": 0, "weight": 0},
		{ "id": 0, "weight": 0}
	]
}
```

#### - Consulta de instruções para abastecimento de carga no veículo
```
http://localhost:8080/delivery/{deliveryId}/step
```

#### - Swagger - Documentação das APIs
```
http://localhost:8080/swagger-ui.html
```

#### - Spring Actuator - APIs para monitoramento da aplicação
```
http://localhost:8080/actuator
```

## Autor

### Rafael Felix de Moraes

### <a href="mailto:rafaelfelix1433@gmail.com?Subject=Java%20CIandT%20-%20Cielo" target="_top">Envie-me um e-mail</a>
