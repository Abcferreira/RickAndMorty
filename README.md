# Rick and Morty - Android App

Este é um aplicativo Android que exibe uma lista de personagens do universo *Rick and Morty*. O usuário pode visualizar detalhes de cada personagem ao clicar sobre ele. O app utiliza o Jetpack Compose para a interface de usuário, MVVM (Model-View-ViewModel) para a arquitetura, e Koin para injeção de dependências.

## Funcionalidades

### Tela de Lista de Personagens
- Exibe uma lista de personagens utilizando `LazyVerticalGrid`.
- A cada item, são mostrados o nome e a imagem do personagem.
- Ao clicar em um personagem, o app navega para a tela de detalhes daquele personagem.

### Tela de Detalhes do Personagem
- Exibe informações detalhadas do personagem selecionado:
  - Nome
  - Espécie
  - Status
  - Gênero
  - Localização
  - Origem
  - Episódios
  - Imagem
- Carrega os dados de um personagem usando o ID do personagem.

### Navegação
- O app usa **Jetpack Navigation Compose** para gerenciar a navegação entre as telas.
- A navegação é feita passando o `characterId` como argumento entre as telas de lista e de detalhes.

### Gerenciamento de Estado
- Utiliza **StateFlow** para gerenciar o estado da UI na `ViewModel`.
- Exibe uma animação de carregamento enquanto os dados estão sendo buscados.
- Os dados dos personagens são carregados de uma API de maneira assíncrona.

### Dependências
- **Jetpack Compose**: Para construção da interface de usuário declarativa.
- **Navigation Compose**: Para navegação entre telas.
- **Koin**: Para injeção de dependência.
- **Coil**: Para carregamento de imagens.
- **Coroutines**: Para gerenciamento de tarefas assíncronas.

## Estrutura do Projeto

- **Model**: Contém as entidades que representam os dados (ex: `CharacterEntity`).
- **ViewModel**: Gerencia o estado da UI e faz a comunicação com o repositório.
- **View**: Telas de exibição (listagem de personagens e detalhes).
- **Repository**: Faz a comunicação com a API para buscar dados.
- **Navigation**: Gerencia a navegação entre telas usando o `NavController`.

## Como Rodar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/seuusuario/rick-and-morty-app.git
2. Abra o projeto no Android Studio.
3. Sincronize as dependências no Android Studio.
4. Execute o aplicativo em um dispositivo físico ou emulador.

## Tecnologias Utilizadas

 - Kotlin: Linguagem de programação principal.
 - Jetpack Compose: Framework para construção de UI declarativa.
 - Navigation Compose: Biblioteca para navegação entre telas.
 - Koin: Framework de injeção de dependência.
 - Coil: Biblioteca para carregamento de imagens.
 - Coroutines: Para operações assíncronas.

## Contribuições

Se você deseja contribuir para o projeto, siga os seguintes passos:

 - Faça um fork do repositório.
 - Crie uma branch para a sua feature (git checkout -b feature/nova-feature).
 - Commit suas mudanças (git commit -m 'Adicionando nova feature').
 - Envie para o seu fork (git push origin feature/nova-feature).
 - Abra um Pull Request.

## Licença

Este projeto está sob a licença MIT - veja o arquivo LICENSE para mais detalhes.
