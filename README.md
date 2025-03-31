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

## 3. Persistir Dados Necessários

Antes de testar a API, é necessário popular o sistema com produtos, pedidos e templates do Elasticsearch. Execute os scripts na seguinte ordem:

```sh
./scripts/persist-products.sh
./scripts/persist-orders.sh
./scripts/put-elastic-search-template.sh
```

Esses scripts garantirão que os produtos e pedidos estejam cadastrados e que a pesquisa no Elasticsearch funcione corretamente. O script `persist-orders.sh` pode ser executado várias vezes para gerar pedidos aleatórios, simulando várias compras.

Após isso, você pode executar o script `try-to-pay-orders.sh` para fazer com que todos os usuários tentem realizar o pagamento de seus pedidos:

```sh
./scripts/try-to-pay-orders.sh
```

## 4. Testando a API

Após seguir os passos anteriores, você pode acessar a documentação interativa da API pelo Swagger:

http://localhost:8080/swagger-ui.html

Ou, se preferir, utilize o template de requisições disponível no repositório para o Postman.

Agora a API está pronta para ser utilizada!