# AddressCRUD

## Questões 1 e 2

Para as questões 1 e 2 foi feito um projeto gradle, utilizando java 8 + spring-framework.

A camada web foi desenvolvida utilizando SparkJava[http://sparkjava.com/]

Está commitado no repositório um arquivo de coleção que pode ser importado no Postman.

Essa coleção contém os templates usados para realizar as requisições.

A camada de persistencia foi feita utilizando o H2-Database, que salva
as alterações em um arquivo na home do usuário(~/.h2/database/public)

## Questão 3

A questão 3 está no módulo "usecases" em uma classe chamada CharFinder, a mesma possui um teste para comprovar seu funcionamento.

## Questão 4

Quando qualquer endereço é digitado em um cliente:

Ao digitar [http://www.google.com.br]:
* Primeiro é necessário resolver o endereço IP do servidor;
* o cliente irá até um servidor de DNS;
* O servidor de DSN retornará o endereço IP associado ao domínio;
* Com o endereço IP registrado, o cliente tenta fazer a requisição até o servidor;
    * Se for HTTP, a requisição será feita na porta 80, caso seja HTTPS, a requisição será na porta 443.

* Ao receber a requisição o servidor processará os dados recebidos;
* Tentará enviar ao cliente a resposta dos dados processados;
* Após enviar a resposta o servidor fecha a conexão.