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

O termo "Produção Local" significa que o sistema vai conectar na própria máquina que está instalado com o perfil de `PRODUCAO`.

Já o termo "Produção Remota" serve para conectar usando sua máquina de desenvolvimento com o banco de dados no computador de `PRODUCAO`.

## Novo Banco de Dados

Para iniciar um banco de dados do zero basta alterar a propriedade `hibernate.hbm2ddl.auto` para `create` no `db.properties` :

```
hibernate.hbm2ddl.auto=create
``` 
Após a criação **lembre de voltar para** `none`
 
## Instalação

Basta gerar um pacote com o Maven usando o "Run As -> Maven Install".

Na pasta `target` do projeto será gerado um arquivo `radiosystem.war`.

Para não existir problemas de substituição de arquivos eu costumo apagar a pasta dentro do `webapps` do Tomcat antes de jogar o novo deploy.

## Servidor

### Segurança

O servidor atualmente é uma instância Ubuntu 14.04.

**NÃO É POSSÍVEL** logar como `root` no ssh.

Apenas o usuário `ubuntu` pode conectar por ssh. Apenas usando uma chave de ssh é possível estabelecer conexão com o servidor.

O usuário `ubuntu` tem sudo.

### Cloudflare

O servidor atualmente conta com a proteção do serviço Free da [CloudFlare](https://www.cloudflare.com/).

Ou seja, não é possível pingar o `www.rdcenter.com.br` pois o ClouFlare está na frente obfuscando o servidor propositalmente.

### Tomcat

O Tomcat do servidor está instalado em `/opt/tomcat`.

A instalação do Tomcat seguiu o seguinte tutorial : [Tomcat 8 on Ubuntu 14.04](https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-ubuntu-14-04)

### nginx 

O Tomcat não é exposto para a internet diretamente.

O nginx é utilizado como proxy reverso. Ele é que recebe as requisições na porta de Http do servidor. 

Se por acaso a requisição vier do domínio `rdcenter.com.br/` ele tem diversos redirecionamentos.

Por exemplo se vier `http://rdcenter.com.br/app` ele redireciona para a aplicação rodando no Tomcat.

Se vier `http://rdcenter.com.br/` apenas, ele redireciona para o site institucional instalado na pasta `/var/www/rdcenter.com.br/` do próprio nginx.

O nginx está utilizando SSL com o certificado do letsencrypt. O tutorial utilizado foi o seguinte : [Nginx com Lets Encrypt no Ubuntu 14-04](https://www.digitalocean.com/community/tutorials/how-to-secure-nginx-with-let-s-encrypt-on-ubuntu-14-04)

### Postgres

A instalação do Postgres seguiu o seguinte tutorial : [Postgre 9 no Ubuntu](http://www.unixmen.com/install-postgresql-9-4-and-phppgadmin-on-ubuntu-15-10/)

Para conectar no postgre de produção remotamente é preciso adicionar uma permissão no firewall do servidor. 

Os scripts que adicionam e removem essa permissão estão na HOME do usuário `ubuntu` dentro da pasta `config`. É preciso ser sudo para modificar o firewall.

