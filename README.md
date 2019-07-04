# UCDbBackend

> Repositório referente ao backend do projeto final da disciplina de Projeto de Software na UFCG desenvolvida em Spring MVC.

> Esse projeto tem como finalidade simular uma rede social de disciplinas onde cada disciplina tem um perfil associado, possuindo opções de likes e comentarios. 

## O projeto utilizou das seguintes ferramentas:
- Thymeleaf
- Spring Boot
- Spring Data
- Spring Security
- Java 8
- PostgreSQL
- Heroku(Deploy)

## Diretórios usados:
- `UCDbBackend/src/main/java/com/ucdb` Nesse caminho está todas as pastas nescessárias para o funcionamento do sistema.
    - `/config` Diretório onde encontra-se os arquivos EmailController.java e Swagger.java.
        - `/EmailController` Responsável pelo envio de um email de boas vindas para um usuário recém cadastrado no sistema.
        - `/Swagger` Responsável pela geração da api.
    - `/controller` Diretório onde encontra-se os arquivos CommentController.java, DisciplinaController.java, LoginController.java, PerfilController.java, ReplyCommentController.java, UserController.java. É no controller onde se encontram as rotas para comunicação com o frontend.
        - `/CommentController` Responsável pela inserção e remoção de comentarios em algum perfil de disciplina.
        - `/DisciplinaController` Responsável pelo cadastramento de uma nova disciplina, assim como o cadastramento do perfil relacionado a essa disciplina, como tambem retornar disciplinas pela substring.
        - `/LoginController` Responsável pela geração do token, autorizando um usuario a acessar o perfil das disciplinas.
        - `/PerfilController` Responsável pela adição e remoção de likes no perfil, como tambem o retorno de perfis pela estrategia escolhida pelo usuário(numero de likes, numero de comentários, getAll, busca pelo id da disciplina).
        - `/ReplyCommentController` Responsável pela inserção e remoção de uma resposta a um comentário feito por algum usuário.
        - `/UserController` Responsável pelo cadastramento de um novo usuário no sistema, pela remoção desse usuário do sistema, como pela busca dele.
    - `/dao` Diretório onde encontra-se os arquivos CommentDAO.java, DisciplinaDAO.java,	PerfilDAO.java,	ReplyCommentDAO.java, UserDAO.java.
        - `/CommentDAO` Responsável pelo DAO de comentários, com as funcionalidades de salvar um novo comentário, recuperar todos os comentarios relacionado a determinado perfil, e buscar o comentário pelo seu ID.
        - `/DisciplinaDAO` Responsável pelo DAO de disciplinas, com as funcionalidades de salvar uma nova disciplina, buscar a disciplina pelo ID, retornar todas as disciplinas cadastradas no sistema, como tambem retornar uma lista de disciplinas pela substring.
        - `/PerfilDAO` Responsável pelo DAO de perfil, com as funcionalidades de salvar um novo perfil, buscar o perfil pelo ID, além de retornar todos os perfis ordenados pelos likes e comentários.
        - `/ReplyCommentDAO` Responsável pelo DAO de comentários de respostas, com a funcionalidade de salvar um novo comentário de resposta.
        - `/UserDAO` Responsável pelo DAO de usuario, com as funcionalidades de salvar um novo usuario além de buscar um usuario pelo seu email.
    - `/model` Diretório onde encontra-se os arquivos Comment.java, Disciplina.java, Perfil.java,	ReplyComment.java, User.java. é nesse pacote que estão as entidades usadas no sistema.
        - `/Comment` Entidade responsável pelo comentário.
        - `/Disciplina` Entidade responsável pela disciplina.
        - `/Perfil` Entidade responsável pelo perfil da disciplina.
        - `/ReplyComment` Entidade responsável pela resposta de comentários.
        - `/User` Entidade responsável pelo usuário.
    - `/service` Diretório onde encontra-se os arquivos CommentService.java, DisciplinaService.java, PerfilService.java, ReplyCommentService.java, UserService.java.
        - `/CommentService` Service responsável pela inserção e remoção de comentários do sistema.
        - `/DisciplinaService` Service responsável pelo cadastramento e buscas das disciplinas.
        - `/PerfilService` Service responsável pelo cadastramento e buscas de perfis.
        - `/ReplyCommentService` Service responsável pela inserção e remoção de respostas a comentários.
        - `/UserService` Service responsável pelo cadastramento, remoção e buscas de usuários.
    - `/filter` Diretório onde encontra-se o arquivo TokenFilter.java.
        - `/TokenFilter` Responsável pela autorização do usuario para acesso ao sistema, validando ou não o token.
    - `/filter` Diretório onde encontra-se os pacotes com exceções para cada entidade do sistema, além da RestExceptionHandler.java onde é indicado o HttpStatus.
        - `/comments` Pacote que contem as exceções para situações na entidade Comments.
        - `/disciplina` Pacote que contem as exceções para situações na entidade Disciplina.
        - `/email` Pacote que contem as exceções para situações de validação de email.
        - `/perfil` Pacote que contem as exceções para situações na entidade Perfil.
        - `/replyComment` Pacote que contem as exceções para situações na entidade ReplyComment.
        - `/user` Pacote que contem as exceções para situações na entidade User.
        


> link para a documentação da API gerada pelo swagger: https://ucdb-final.herokuapp.com/api/swagger-ui.html#/

