🐾 Pokedex API
API REST desenvolvida em Spring Boot para gerenciar o ecossistema de treinadores, pokémons e capturas (pokébolas), inspirada no universo Pokémon.

📋 Objetivos do Projeto
Gerenciamento Completo: CRUD de Treinadores, Pokémons e Pokébolas.

Regras de Negócio: Implementação de validações customizadas (limite de mochila e eficácia de captura).

Performance: Endpoints otimizados com paginação (Pageable).

Arquitetura: Estrutura organizada em camadas (Controller, Service, Repository, DTO).

🎯 Entidades e Regras de Negócio
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

🚀 Endpoints Principais
Pokébolas (/pokebolas)
POST /pokebolas - Realiza uma captura (Valida regras de nível e mochila).

GET /pokebolas - Lista todas as capturas (Paginado).

GET /pokebolas/{id} - Busca detalhada.

GET /pokebolas/search?treinadorId={id} - Filtra pokémons de um treinador específico.

GET /pokebolas/search?tipo={tipo} - Filtra por tipo de bola.

DELETE /pokebolas/{id} - Liberta um Pokémon.

Pokémons (/pokemons)
POST /pokemons - Cadastra nova espécie.

GET /pokemons/search?tipo={tipo} - Filtra por elemento.

PUT /pokemons/{id} - Atualiza dados ou evolução.

Treinadores (/treinadores)
POST /treinadores - Cadastra novo treinador.

GET /treinadores/search?nome={nome} - Busca por nome.

PUT /treinadores/{id} - Atualiza perfil ou capacidade.

🛠️ Tecnologias Utilizadas
Java 17 & Spring Boot 3

Spring Data JPA: Persistência de dados.

Bean Validation: Regras @Valid e anotações customizadas.

Lombok: Produtividade e redução de boilerplate.

H2 Database: Banco em memória para testes rápidos.

Maven: Gestão de dependências.


