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
