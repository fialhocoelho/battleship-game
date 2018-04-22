# battleship-game
Repositório para criação do jogo de Batalha naval da matéria de Engenharia de Software III (IES-300) da FATEC-SP

### Requisitos mínimos para rodar o projeto:
0. Instalar o MySQL com usuário "**root**" e senha "**root**". 
0. Criar um Schema chamado "**battleship**" no MySQL.
0. Instalar o JDK 8 e configurar a variável de ambiente **JAVA_HOME**.

### Como rodar o projeto
* Caso esteja utilizando **Linux/Mac**, rodar o comando "**./mvnw spring-boot:run**" de dentro da pasta raiz do projeto.
* Caso esteja utilizando **Windows**, rodar o comando "**mvnw.bat spring-boot:run**" de dentro da pasta raiz do projeto.

Seguindo esses passos o sistema deverá subir, criar todas as tabelas no banco de dados e criar um usuario administrador (**admin** - **admin**)