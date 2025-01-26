# Rick and Morty - Android App 🚀

Este é um aplicativo Android que exibe uma lista de personagens do universo *Rick and Morty* 🛸. O usuário pode visualizar detalhes de cada personagem ao clicar sobre ele. O app utiliza o **Jetpack Compose** para a interface de usuário, **MVVM** (Model-View-ViewModel) para a arquitetura, **Clean Architecture** para organização de código e **Koin** para injeção de dependências.

## Funcionalidades ✨

### Tela de Lista de Personagens 🧑‍🤝‍🧑
- Exibe uma lista de personagens utilizando `LazyVerticalGrid`.
- A cada item, são mostrados o nome e a imagem do personagem.
- Ao clicar em um personagem, o app navega para a tela de detalhes daquele personagem.

### Tela de Detalhes do Personagem 🦸‍♂️
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

### Navegação 🔄
- O app usa **Jetpack Navigation Compose** para gerenciar a navegação entre as telas.
- A navegação é feita passando o `characterId` como argumento entre as telas de lista e de detalhes.

### Gerenciamento de Estado ⚡
- Utiliza **LiveData** ou **StateFlow** para gerenciar o estado da UI na `ViewModel`.
- Exibe uma animação de carregamento enquanto os dados estão sendo buscados.
- Os dados dos personagens são carregados de uma API de maneira assíncrona.

### Arquitetura Limpa 🧹
- O projeto segue a **Clean Architecture** para manter o código bem organizado e escalável.
- **Camadas separadas** para:
  - **Data** (Repositório e fontes de dados)
  - **Domain** (Casos de uso e entidades)
  - **Presentation** (ViewModel, UI e navegação)

## Dependências 📦
- **Jetpack Compose**: Para construção da interface de usuário declarativa.
- **Navigation Compose**: Para navegação entre telas.
- **Koin**: Para injeção de dependência.
- **Coil**: Para carregamento de imagens.
- **Coroutines**: Para gerenciamento de tarefas assíncronas.

## Estrutura do Projeto 🏗️

- **Model**: Contém as entidades que representam os dados (ex: `CharacterEntity`).
- **ViewModel**: Gerencia o estado da UI e faz a comunicação com o repositório.
- **View**: Telas de exibição (listagem de personagens e detalhes).
- **Repository**: Faz a comunicação com a API para buscar dados.
- **Navigation**: Gerencia a navegação entre telas usando o `NavController`.
- **Domain**: Contém os casos de uso que representam a lógica de negócios.

## Como Rodar o Projeto 🛠️

1. Abra o projeto no Android Studio.
2. Sincronize as dependências no Android Studio.
3. Execute o aplicativo em um dispositivo físico ou emulador.

## Tecnologias Utilizadas 🖥️

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Compose**: Framework para construção de UI declarativa.
- **Navigation Compose**: Biblioteca para navegação entre telas.
- **Koin**: Framework de injeção de dependência.
- **Coil**: Biblioteca para carregamento de imagens.
- **Coroutines**: Para operações assíncronas.

## Contribuições 💡

Se você deseja contribuir para o projeto, siga os seguintes passos:

1. Faça um fork do repositório.
2. Crie uma branch para a sua feature:
   ```bash
   git checkout -b feature/nova-feature
3. Commit suas mudanças:
    ```bash
    git commit -m 'Adicionando nova feature'
4. Envie para o seu fork:
    ```bash
    git push origin feature/nova-feature
5. Abra um Pull Request.


## Licença

Este projeto está sob a licença MIT - veja o arquivo LICENSE para mais detalhes.
