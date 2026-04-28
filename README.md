# 🐾 Pokedex API
API REST desenvolvida em Spring Boot para gerenciar o ecossistema de treinadores, pokémons e capturas (pokébolas), inspirada no universo Pokémon.

# Membros
Pietro Paranhos Wilhelm - Rm561378
Felipe Monte - Rm562019
Pedro de Matos - Rm564093

## 📋 Objetivos do Projeto
Gerenciamento Completo: CRUD de Treinadores, Pokémons e Pokébolas.

Regras de Negócio: Implementação de validações customizadas (limite de mochila e eficácia de captura).

Performance: Endpoints otimizados com paginação (Pageable).

Arquitetura: Estrutura organizada em camadas (Controller, Service, Repository, DTO).

## 🎯 Entidades e Regras de Negócio
1. Treinador

Campos: id, nome, capacidadeMochila.

Vínculo: Pode possuir múltiplas Pokébolas (1:N).

2. Pokémon

Campos: id, nome, tipo, level, ataque.

Validação: Nível é obrigatório.

3. Pokébola (O Vínculo de Captura)

Representa a posse de um Pokémon por um Treinador.

Validação de Capacidade: Um treinador pode carregar no máximo 6 pokébolas (validação @CapacidadeMochila).

Validação de Nível (@NivelParaCaptura):

Tipo de Bola,Nível Máximo do Pokémon

COMUM: nível ≤ 20

GREAT: 21-40

ULTRA: 41-60

MASTER: > 60

## 🚀 Endpoints Principais

Aqui está a lista organizada de todas as URLs da API, separadas por entidade, para você copiar e colar no Postman.

### 🎒 Treinadores (/treinadores)
Endpoints para gerenciar os donos dos Pokémons.

Criar Treinadores

POST http://localhost:8080/treinadores
_____________________________________________

Listar Todos (Paginado)

GET http://localhost:8080/treinadores 
_____________________________________________

Buscar por ID 

GET http://localhost:8080/treinadores/{id}
_____________________________________________

Buscar por Nome (Filtro) 

GET http://localhost:8080/treinadores/search?nome=Ash 
_____________________________________________

Atualizar Treinador 

PUT http://localhost:8080/treinadores/{id} 
_____________________________________________

Excluir Treinador

DELETE http://localhost:8080/treinadores/{id}
_____________________________________________

### 👾 Pokémons (/pokemons)

Endpoints para gerenciar as espécies e dados dos Pokémons.

Criar Pokémon 

POST http://localhost:8080/pokemons

_____________________________________________

Listar Todos (Paginado)

GET http://localhost:8080/pokemons
_____________________________________________

Buscar por ID

GET http://localhost:8080/pokemons/{id}
_____________________________________________

Buscar por Tipo (Filtro)

GET http://localhost:8080/pokemons/search?tipo=FOGO
_____________________________________________

Buscar por Nome (Filtro) 

GET http://localhost:8080/pokemons/search?nome=Char 
_____________________________________________

Atualizar Pokémon 

PUT http://localhost:8080/pokemons/{id}Excluir 
_____________________________________________

Libertar Pokemom
DELETE http://localhost:8080/pokemons/{id} 


### 🔴 Pokébolas (/pokebolas)
Endpoints que realizam a lógica de captura e vínculo entre Treinador e Pokémon.

Realizar Captura (Create) 

POST http://localhost:8080/pokebolas

_____________________________________________

Listar Capturas (Paginado)

GET http://localhost:8080/pokebolas
_____________________________________________


Buscar por ID 

GET http://localhost:8080/pokebolas/{id}

_____________________________________________
Buscar por Treinador (Filtro)


GET http://localhost:8080/pokebolas/search?treinadorId=1
_____________________________________________

Buscar por Tipo de Bola (Filtro)

GET http://localhost:8080/pokebolas/search?tipo=ULTRA
_____________________________________________


Libertar Pokémon (Delete)

DELETE http://localhost:8080/pokebolas/{id}
_____________________________________________

Aqui estão as URLs agrupadas com seus respectivos JSONs de exemplo para você apenas copiar e colar no Postman.

### 🎒 1. Treinadores (/treinadores)
POST - Criar Treinador

URL: http://localhost:8080/treinadores

Body:

JSON
{

  "nome": "Ash Ketchum",
  "capacidadeMochila": 6
}

PUT - Atualizar Treinador

URL: http://localhost:8080/treinadores/1

Body:

JSON

{

  "nome": "Ash Ketchum Ketchum",
  "capacidadeMochila": 10
  
}


Buscas (GET):

Listar todos: http://localhost:8080/treinadores

Por ID: http://localhost:8080/treinadores/1

Filtro por Nome: http://localhost:8080/treinadores/search?nome=Ash

### 👾 2. Pokémons (/pokemons)
POST - Criar Pokémon

URL: http://localhost:8080/pokemons

Body:

JSON

{

  "nome": "Pikachu",
  "tipo": "ELETRICO",
  "level": 25,
  "ataque": "Choque do Trovão"
  
}

PUT - Atualizar Pokémon

URL: http://localhost:8080/pokemons/1

Body:

JSON
{

  "nome": "Raichu",
  "tipo": "ELETRICO",
  "level": 35,
  "ataque": "Trovão"
  
}


### 🔴 3. Pokébolas (/pokebolas)
POST - Realizar Captura

URL: http://localhost:8080/pokebolas

## Atenção: Certifique-se de que o treinadorId e pokemonId existem!

Body (Sucesso - Ultra Ball):

JSON

{

  "nome": "Captura Especial",
  "tipo": "ULTRA",
  "nivelPokemon": 25,
  "treinadorId": 1,
  "pokemonId": 1
  
}

POST - Teste de Erro (Validação Nível)

Body (Erro - Comum não pega nível 25):

JSON

{

  "nome": "Tentativa Falha",
  "tipo": "COMUM",
  "nivelPokemon": 25,
  "treinadorId": 1,
  "pokemonId": 1
  
}


## 🛠️ Tecnologias Utilizadas
Java 17 & Spring Boot 3

Spring Data JPA: Persistência de dados.

Bean Validation: Regras @Valid e anotações customizadas.

Lombok: Produtividade e redução de boilerplate.

H2 Database: Banco em memória para testes rápidos.

Maven: Gestão de dependências.


