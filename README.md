# Aceleração em Java


## Projeto: Central de Erros

## Objetivo

Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros de erros em um local, de onde podem monitorar e tomar decisões mais acertadas. Neste projeto vamos implementar uma API Rest para centralizar registros de erros de aplicações.
Abaixo estão os requisitos desta API, o time terá total liberdade para tomar as decisões técnicas e de arquitetura da API, desde que atendam os requisitos abaixo.


## API

*Tecnologia*
- Utilizar a tecnologia base da aceleração para o desenvolvimento (Exemplo: Java, Node.js)
*Premissas*
- A API deve ser pensada para atender diretamente um front-end
- Deve ser capaz de gravar os logs de erro em um banco de dados relacional
- O acesso a ela deve ser permitido apenas por requisições que utilizem um token de acesso válido
*Funcionalidades*
- Deve permitir a autenticação do sistema que deseja utilizar a API gerando o Token de Acesso
- Pode ser acessado por multiplos sistemas
- Deve permitir gravar registros de eventos de log salvando informações de Level(error, warning, info), Descrição do Evento, LOG do Evento, ORIGEM(Sistema ou Serviço que originou o evento), DATA(Data do evento), QUANTIDADE(Quantidade de Eventos de mesmo tipo)
- Deve permitir a listagem dos eventos juntamente com a filtragem de eventos por qualquer parâmetro especificado acima
- Deve suportar Paginação
- Deve suportar Ordenação por diferentes tipos de atributos
- A consulta de listagem não deve retornar os LOGs dos Eventos
- Deve permitir a busca de um evento por um ID, dessa maneira exibindo o LOG desse evento em específico


## Tecnologias utilizadas

* Java 11
* SpringBoot
* Spring Data Jpa
* Maven
* Lombok
* MySql database
* Swagger
* Spring Fox
* Oauth
* React
* Bibliotecas: chakra-ui e codesandbox-io
* Junit
* Heroku
* Trello [Link](https://trello.com/b/mdnufyaX/central-de-erros)
* Excalidraw [Link - desenho telas](https://drive.google.com/file/d/1JQi0kRQ1SySDwn35S1jA5TmXY94W_kri/view?usp=sharing)


## Instalação

- Clone o projeto:
```bash
$ git clone https://github.com/flaviolunaferreira/AceleracaoCodenation
```

- Instale o Maven:
[https://maven.apache.org/install.html](https://maven.apache.org/install.html).
- Entre na pasta do projeto:
```bash
$ cd squad-1-ad-java-e-vue-online-ca-back
```
- No arquivo 'application.properties':
Informe seu username e password do Postgres.

- No banco de dados (usamos o postgres):
[x] Criar o Database 'tableerros' (usamos o pgAdmin);

- Iniciar o projeto:
mvn spring-boot:run

- Para testar se a aplicação está em execução, acesse o endereço ```http://localhost:8080/user/``` ou utilize o Insomnia/Postman. Deverá retornar um array vazio.


# Documentação com Swagger
Após executar a aplicação, você poderá acessar a documentação da API, contendo os endpoints implementados, no endereço ```http://localhost:8080/swagger-ui.html```.


# Vendo funcionar com exemplo prático

- Copie o arquivo 'import' disponibilizado na pasta 'resources' do projeto;
- Cole em uma Query do PgAdmin e o execute;
- Após o Banco de dados populado, poderá ver funcionando no frontEnd que disponibilizamos no Link: [FrontEnd api de erros](https://github.com/flaviolunaferreira/central-de-erros/tree/master)


# Deploy

O deploy da aplicação foi efetuado no Heroku e está disponível no endereço: ```https://cryptic-beach-76961.herokuapp.com/```.


## Autores

<table>
<tr>
<td align="center">
<a href="https://github.com/DanielePerse">
<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/66958813?v=4" width="100px;" alt=""/><br />
<a href="https://www.linkedin.com/in/daniele-perse">
<img style="height: 20px" alt="LinkedIn" src="https://img.shields.io/badge/Daniele Perse-0077B5?logo=LinkedIn&logoColor=white&style=for-the-badge" />
</a>
</td>
<td align="center">
<a href="https://github.com/flaviolunaferreira">
<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/40308391?v=4" width="100px;" alt=""/><br />
</a>
<a href="https://www.linkedin.com/in/flavio-luna-ferreira-9b4812165/">
<img style="height: 20px" alt="LinkedIn" src="https://img.shields.io/badge/theCoyote-0077B5?logo=LinkedIn&logoColor=white&style=for-the-badge" />
</a>
</td>
<td align="center">
<a href="https://github.com/Darthurmoura">
<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/54224755?v=4" width="100px;" alt=""/><br />
</a>
<a href="https://www.linkedin.com/in/darthurmoura/">
<img style="height: 20px" alt="LinkedIn" src="https://img.shields.io/badge/Daniel Moura-0077B5?logo=LinkedIn&logoColor=white&style=for-the-badge" />
</a>
</td>
<td align="center">
<a href="https://github.com/josiasviveiro">
<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/62304302?v=4" width="100px;" alt=""/><br />
</a>
<a href="https://www.linkedin.com/in/josias-danny-de-viveiro">
<img style="height: 20px" alt="LinkedIn" src="https://img.shields.io/badge/Josias Viveiro-0077B5?logo=LinkedIn&logoColor=white&style=for-the-badge" />
</a>
</td>
<td align="center">
<a href="https://github.com/JorgeHSOsiro">
<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/34973260?v=4" width="100px;" alt=""/><br />
</a>
<a href="https://www.linkedin.com/in/jorge-osiro/">
<img style="height: 20px" alt="LinkedIn" src="https://img.shields.io/badge/Jorge Osiro-0077B5?logo=LinkedIn&logoColor=white&style=for-the-badge" />
</a>
</td>

</tr>
</table>
