# CookAI

## 1. Introdução

Combata a fome global com o CookAi: um aplicativo revolucionário com um toque de inteligência artificial. Gerencie alimentos de forma eficiente, crie receitas incríveis e evite desperdícios. Chega de pensar "o que vou cozinhar hoje?" - o CookAi torna a tarefa fácil e divertida!

## 2. Endpoints

### 2.1 AuthController

- **Endpoint:** POST /login
- **Descrição:** Este endpoint é utilizado para autenticar o usuário.
- **Corpo da Solicitação:** O corpo da solicitação deve ser um JSON contendo o login e a senha do usuário.
  
  **Requisição:**
  ```json
  {
    "login": "neurotrix@example.com",
    "password": "teste123"
  }
  ```
  
**Resposta:** Caso a autenticação seja um sucesso, é retornado um JWT de autenticação com validade de 30 minutos.

### 2.2 RecipeController

- **Endpoint:** POST /recipe
- **Descrição:** Este endpoint é utilizado para o usuário enviar os ingredientes que serão utilizados para a criação da receita.
- **Corpo da Solicitação:** O corpo da solicitação deve ser um JSON contendo a mensagem do usuário.

 **Requisição:**
  ```json
  {
    "ingredientes": "macarrão gravata, tomate, sal, pimenta, salsicha",
    "dificuldade": "facil",
    "tempoMaximo" : "60 minutos"
  }
  ```

**Resposta:** Em caso de sucesso, é retornada a resposta do modelo de inteligência artificial “GPT-3.5-turbo”

- **Endpoint:** GET /recipe?pageNumber=0
- **Descrição:** Este endpoint é utilizado para obter as receitas enviadas pelo usuário utilizando o parametro "pageNumber" = 0.

 **Requisição:**
  ```URL
   GET http://localhost:8080/recipe
  ```
**Resposta:** Em caso de sucesso, retorna uma lista de receitas enviadas pelo usuário com as respostas do modelo de inteligência artificial.

### 2.3 UserController

- **Endpoint:** POST /user/register
- **Descrição:** Este endpoint é utilizado para registrar um novo usuário.
- **Corpo da Solicitação:** O corpo da solicitação deve ser um JSON contendo as informações do usuário (nome, login, senha, etc.).

 **Requisição:**
  ```json
{
  "name": "Leandro",
  "email": "neurotrix@fiap.com",
  "password": "teste123"
}
  ```

**Resposta:** Retorna 200 OK caso o usuário seja criado corretamente

- **Endpoint:** GET /user/all 
- **Descrição:** Este endpoint é utilizado para obter uma lista de todos os usuários.

 **Requisição:**
  ```URL
   GET http://localhost:8080/user/all
  ```
  
**Resposta:** Em caso de sucesso, retorna uma lista de todos os usuários.

## 4. Arquitetura da solução

![Arquitetura da Solucao](./img/arquitetura-solucao.png)

## 5. Arquitetura da Aplicação

![Arquitetura da Solucao](./img/arquitetura-comunicacao.png)

## 6. Conexão com a API OpenAI

A conexão com a API OpenAI é realizada utilizando a biblioteca openai-java, que permite realizar requisições POST à API, especificando o prompt, o modelo e os tokens máximos.

Exemplo da requisição em JAVA :

```java
    OpenAiService openai = new OpenAiService("seu_token_api");

    public String sendMessageGpt(String message) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Responda essa pergunta: " + message)
                .maxTokens(2000)
                .model("text-davinci-003")
                .build();

       return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }
```
