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
