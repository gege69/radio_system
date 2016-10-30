Radio System
============

Sistema de rádio indoor com 3 níveis:

 - Administrador ( tem uma instância do sistema dedicado e pode atuar como Gerenciador )
 - Gerenciador ( pode ter vários pontos de venda como clientes )
 - Ponto de Venda ( chamado de Ambiente )


## Requerimentos

 - Tomcat 8
 - Java 8 
 - Postgres 9


## Perfis de Banco de Dados

Existem dois perfis de configuração utilizados pelo Spring para inicializar a aplicação :
 - `DESENVOLVIMENTO`
 - `PRODUCAO`
 
Para que a aplicação conecte em `DESENVOLVIMENTO` não é necessário fazer nada.
Para que a aplicação conecte em `PRODUCAO` é preciso colocar uma propriedade no `catalina.properties` do Tomcat que executa a aplicação.

Basta inserir a seguinte linha no final do `catalina.properties` :

```
spring.profiles.active=prod
```


## Configuração do Banco de Dados

O arquivo `db.properties` tem os dados de conexão do banco de dados tanto para os perfis de `DESENVOLVIMENTO` quanto para `PRODUCAO`.



 
## Instalação

