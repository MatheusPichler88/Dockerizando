# Introdução ao Docker

## O que é Docker?

Docker é uma ferramenta que permite empacotar sua aplicação dentro de um "container" (tipo uma caixinha) com tudo que ela precisa pra funcionar: código, bibliotecas, configurações, etc.

Pensa assim: antes do Docker, o programador falava "ué, na minha máquina funciona". Com o Docker, se funciona no container, vai funcionar em qualquer lugar.

## Pra que usar Docker?

- **Não precisar instalar tudo na máquina** - ao invés de instalar Java, MySQL, direto no seu PC, você roda tudo em containers separados
- **Garantir que o ambiente é igual** - o que roda na sua máquina vai rodar igual no servidor
- **Isolar aplicações** - cada app roda no seu container sem interferir nas outras
- **Facilitar deploy** - sobe a imagem no servidor e pronto

## Benefícios que eu entendi até agora:

| Antes do Docker | Com Docker |
|-----------------|------------|
| Instalar Java na máquina | Java vem na imagem |
| "Na minha máquina funciona" | Roda igual em qualquer lugar |
| Configurar ambiente manualmente | Tudo configurado no Dockerfile |
| Aplicações competindo por versões | Cada container isolado |
| Deploy complexo | Sobe imagem e já era |

## Conceitos principais:

- **Imagem** = é o "molde" ou "ISO" da sua aplicação (tipo um pacote fechado)
- **Container** = é a imagem rodando (aplicação de fato funcionando)
- **Dockerfile** = a receita de como criar a imagem
- **Docker Hub** = site onde ficam guardadas as imagens (tipo GitHub mas de imagens)


## O que dá pra fazer:

- Pegar imagens prontas (ex: MySQL, Redis, Java)
- Criar suas próprias imagens (ex: sua aplicação Spring)
- Subir suas imagens pro Docker Hub
- Rodar containers em qualquer lugar

## Resumindo:

Docker resolve aquele problema chato de "funciona aqui mas não funciona lá". Com ele, sua aplicação vira um pacote portátil que roda em qualquer lugar que tenha Docker instalado.

---


# Fluxograma:

<img width="1170" height="637" alt="dockerEstrutura" src="https://github.com/user-attachments/assets/b99df632-64fc-444e-95ab-ae4758095e0f" />

# Aula 01 - Anotações 

Primeiro precisamos criar uma aplicação com Spring, na primeira aula criaremos uma aplicação simples.

Primeiro precisamos criar um arquivo chamado Dockerfile -> vai transformar a app spring em uma imagem docker que posso mandar pro DockerHub ou posso pegar a minha imagem e rodar dentro de um container.

Precisamos saber alguns comandos:
- para dar um build no dockerfile pra transformar em imagem precisamos usar o comando build: `docker build`
- para rodar a imagem e virar container: `docker run` 
- para mandar pro docker hub: `docker push`

## Passo a passo:

1- Criamos a aplicação pelo Spring Initializr

2- Criamos a classe controller e para teste setaremos uma rota de msg dizendo que é uma aplicação docker

3- Abrimos a barra lateral do maven, clicamos no projeto e Lifecycle, daremos primeiro um clean para limpar o projeto, depois daremos um package onde ele ira criar um executavel .jar para preparar a nossa aplicação.
*Na pasta target vai estar o arquivo .jar que criamos e precisamos usar ele

4- Agora precisamos criar um dockerfile, na pasta raiz do projeto criaremos um file, sempre prestando atenção na grafia, o certo é Dockerfile

## Conceitos que entendi:

- **Dockerfile** = é o arquivo que vai transformar nossa app em imagem
- **Imagem** = tipo um arquivo ISO que a gente pode guardar ou compartilhar
- **Container** = é quando a imagem tá rodando
- **DockerHub** = lugar na nuvem pra guardar as imagens

# Aula 02 - Anotações

Continuando o Dockerfile que começamos na aula passada.

Utilizaremos o FROM para dizer o tipo de imagem base que vamos trabalhar, no caso como vamos usar um .jar precisamos de uma imagem com Java.

LABEL é os metadados, tipo a empresa que ta disponibilizando o arquivo, no meu caso sou eu.

WORKDIR é o diretorio que vamos trabalhar dentro do container.

COPY é como vamos fazer a copia da nossa aplicação pra dentro da imagem.

ENTRYPOINT é qual o tipo de ação que iremos realizar quando o container iniciar, no caso rodar o .jar.

EXPOSE (opcional) serve pra expor a porta que a aplicação vai rodar.

## Passo a passo:

1- **FROM** - Fomos no dockerhub.com, na barra de pesquisa colocamos "java" e apareceram as imagens oficiais. Escolhi o eclipse temurin, copiei o nome e coloquei no FROM. Depois usando : coloquei a versao do java que to usando no projeto (21).

2- **LABEL** - passei a empresa (no caso sou eu), entao ficou: LABEL maintainer="contato@java10x.dev" (pode ser meu email)

3- **WORKDIR** - passei o diretorio que vou trabalhar dentro do container:

4- **COPY** - aqui copiamos o .jar que ta na pasta target pra dentro da imagem. Primeiro a gente coloca o caminho do arquivo de origem (no nosso pc) e depois o destino (dentro do container). No destino podemos ate renomear o arquivo:
(peguei o arquivo da pasta target e joguei na pasta /app do container com o nome aula-docker.jar)

5- **ENTRYPOINT** - aqui defino o comando que vai rodar quando o container iniciar. No caso vou mandar o java executar o .jar que copiei:
(o ENTRYPOINT vai fazer o container rodar esse comando assim que iniciar)

6- **EXPOSE** (opcional) - se quiser expor a porta que a aplicação vai rodar, no spring normalmente é a 8080:


## Dockerfile completo até agora:
```dockerfile
FROM eclipse-temurin:21
LABEL maintainer="contato@java10x.dev"
WORKDIR /app
COPY target/DockerAulaJava10x-0.0.1-SNAPSHOT.jar /app/aula-docker.jar
ENTRYPOINT ["java", "-jar", "aula-docker.jar"]
```

# Aula 03 - Anotações

Hoje aprendemos na prática como fazer o build da imagem Docker e verificar se ela foi criada.

## Comandos que usamos:

### 1. docker build docker build
Esse comando pega nosso Dockerfile e transforma em uma imagem de verdade.

```bash
docker build -t imagem-spring-aula:0.0.1-RELEASE .
```
Explicando cada parte:
docker build = comando pra criar a imagem

-t = tag (nome que vamos dar pra imagem)

imagem-spring-aula:0.0.1-RELEASE = nome da imagem + versão

. = o contexto (pasta atual onde tá o Dockerfile)
### 2.  docker images
   Depois do build, usamos esse comando pra ver se a imagem foi criada e listar todas as imagens que temos no computador.
   ```bash
docker images
```

# Aula 04 - Anotações

Hoje aprendemos a rodar o container e verificar se ele está funcionando.

## Comandos que usamos:

### 1. docker run
Esse comando pega a imagem que criamos e transforma em um container rodando.

```bash
docker run -d -p 8080:8080 imagem-spring-aula:0.0.1-RELEASE
```
Explicando cada parte:

docker run = comando pra criar e iniciar um container

-d = modo "detach" (roda em background, não prende o terminal)

-p 8080:8080 = mapeia a porta 8080 do container pra porta 8080 do meu PC

imagem-spring-aula:0.0.1-RELEASE = nome da imagem que vamos usar

O que acontece: Agora nossa aplicação Spring tá rodando dentro do container e acessível em http://localhost:8080

### 2. docker ps
   Esse comando mostra os containers que estão rodando no momento.
### 3. docker ps -a
   Esse comando mostra todos os containers, inclusive os que já pararam.
### 4. docker stop abc123...(Hash da aplicação)
O container continua existindo, mas não está mais rodando. Dá pra ver ele parado com docker ps -a.
### 5. docker rm abc123...
Depois de rodar docker rm, o container é apagado de vez. Não dá mais pra iniciar ele de novo.