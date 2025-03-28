# Como Rodar o Projeto

## 1. Preparar a Infraestrutura

Antes de iniciar a aplicação, é necessário subir os serviços necessários utilizando Docker. Execute o seguinte comando na raiz do projeto:

```sh
docker-compose up -d
```

Isso iniciará os serviços essenciais, como banco de dados e Elasticsearch.

## 2. Iniciar a Aplicação Spring Boot

Após garantir que a infraestrutura está rodando corretamente, inicie a aplicação Spring Boot:

```sh
./mvnw spring-boot:run
```

Ou, se estiver usando Gradle:

```sh
./gradlew bootRun
```

## 3. Persistir Dados Necessários

Antes de testar a API, é necessário popular o sistema com produtos, pedidos e templates do Elasticsearch. Execute os scripts na seguinte ordem:

```sh
./scripts/persist-products.sh
./scripts/persist-orders.sh
./scripts/put-elastic-search-template.sh
```

Esses scripts garantirão que os produtos e pedidos estejam cadastrados e que a pesquisa no Elasticsearch funcione corretamente.

## 4. Testando a API

Após seguir os passos anteriores, você pode acessar a documentação interativa da API pelo Swagger:

[Swagger UI](http://localhost:8080/swagger-ui.html)

Ou, se preferir, utilize o template de requisições disponível no repositório para o Postman.

Agora a API está pronta para ser utilizada!

