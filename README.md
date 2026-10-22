# CoCar

Aplicação desktop em JavaFX para gerenciar uma plataforma de caronas universitárias: passageiros, veículos, trajetos, caronas, empresas parceiras, benefícios, carteiras e transações.

Projeto da disciplina de Design de Software (RA2), PUCPR, Engenharia de Software.

**Autores:** André Murilo Pinz Gomes, Eduardo Skoroboatei Gomes, João Pedro Gadens Mosson e João Pedro Magri Pozzan.

> O relatório anterior (RA3 de Programação Orientada a Objetos) foi guardado em [`docs/README-RA3-POO.md`](docs/README-RA3-POO.md). Ele descreve uma versão anterior do código e não reflete os padrões de projeto desta entrega.

## Como executar

Requisitos: JDK 21 ou superior. O Maven vem junto, pelo wrapper `mvnw`.

```
./mvnw javafx:run
```

No PowerShell, se `./mvnw` não funcionar, use `.\mvnw.cmd javafx:run`.

Os dados são salvos na pasta `data/` (um arquivo `.dat` por entidade), criada na primeira execução e ignorada pelo Git. Para começar do zero, apague a pasta `data/`.

## Visão geral da arquitetura

Cada entidade segue o mesmo trio **Model / View / Controller**, e as telas de cadastro (CRUD) compartilham uma estrutura única:

- `Model<T>`: classe base das entidades, com `ID` e as regras de colisão (duplicidade).
- `CRUDView<T>`: tela padrão com formulário, tabela e botões Adicionar, Atualizar, Deletar e Voltar.
- `CRUDController<T, V>`: lógica comum de adicionar, ler, atualizar e deletar, mais a validação dos campos.
- `Repository<T>`: persistência por serialização Java em `data/<entidade>.dat`. A gravação é atômica (escreve em arquivo temporário e move) e, se o arquivo estiver ilegível, ele é preservado como `.corrompido-<data>.dat` em vez de ser sobrescrito.
- `Router`: navegação entre o menu e as telas.

| Entidade | Pacote | Regra de duplicidade |
|---|---|---|
| Passageiro | `Instanciacao.JoaoMosson` | mesmo CPF |
| Veículo | `Instanciacao.JoaoMosson` | mesma placa |
| Empresa | `Instanciacao.JoaoPozzan` | mesmo CNPJ |
| Carteira | `Instanciacao.JoaoPozzan` | mesmo número de cartão |
| Benefício | `Instanciacao.Andre` | mesmo nome |
| Carona | `Instanciacao.Andre` | mesmo motorista, origem e destino |
| Trajeto | `Instanciacao.Eduardo` | sem regra de duplicidade |
| Transação | `Instanciacao.Eduardo` | sem regra de duplicidade |

## Padrões de projeto (GoF)

### 1. Template Method

O esqueleto de um algoritmo fica numa classe base e os passos variáveis são delegados às subclasses.

- **`Model.colideCom`** é `final`: confere o tipo do objeto e delega a decisão ao gancho `checarColisao`, que cada entidade sobrescreve. `herdarDe` é outro gancho, chamado por `Repository.atualizar` ao substituir um registro.
- **`CRUDController.adicionar`, `atualizar` e `deletar`** são `final`: validam, montam o modelo, chamam o repositório e atualizam a tabela. Os passos `camposParaModel` e `modelParaCampos` são abstratos.
- **`CRUDView`** monta a tela no construtor e deixa `configurarColunas` e `getTitulo` para a subclasse.
- **`RegraValidacao.executar`** define a ordem da validação e delega a verificação a `valido()`.

**Por quê:** as oito telas de cadastro têm o mesmo fluxo e mudam apenas campos e colunas. O template evita repetir o fluxo oito vezes.

### 2. Strategy (pacote `validacao`)

Cada forma de validar um campo é uma estratégia intercambiável.

- **Interface:** `RegraValidacao`, com o resultado em `ResultadoValidacao`.
- **Estratégias:** `RegraTextField`, `RegraNumeroInteiro`, `RegraNumeroDecimal`, `RegraComboBox`, `RegraDatePicker`, `RegraLocalDateTime`, `RegraCartaoCredito` e `RegraTipoDominio` (CPF, CNPJ e Placa).
- **Contexto:** `CRUDController` mantém a lista `regrasValidacao`. Cada controller concreto registra as suas com `adicionarRegra`; para cada campo vale a primeira regra que o suporta.

Exemplos: `CarteiraController` usa `RegraNumeroDecimal` (saldo mínimo 0) e `RegraCartaoCredito`; `VeiculoController` usa `RegraTipoDominio` para a placa e `RegraNumeroInteiro` para o ano (1900 até o ano seguinte).

**Por quê:** cada tela precisa de regras diferentes para tipos de campo diferentes, e novas regras entram sem alterar o `CRUDController`.

### 3. Singleton (`Router`)

Há um único `Stage` e um único menu principal, então toda a navegação passa por uma instância única: construtor privado e `Router.getInstance()`. O `App` chama `inicializar(stage, menu)` uma vez e as telas navegam com `Router.getInstance().navegarPara(controller)`.

### 4. Factory Method (`CRUDController.criarView`)

O `CRUDController` precisa de uma view, mas não sabe qual. Ele declara `protected abstract V criarView()`, chamado no construtor, e cada controller concreto devolve a sua (`CaronaController` cria `CaronaView`, `VeiculoController` cria `VeiculoView`, e assim por diante). São 8 criadores concretos e 8 produtos concretos.

### 5. State (status da Carona)

A carona muda de comportamento conforme o estado. Em vez de um campo de texto com `if`s, cada estado é uma classe.

- **Contexto:** `CaronaModel`, que guarda um `EstadoCarona` e expõe `confirmar()`, `concluir()` e `cancelar()`.
- **Interface:** `EstadoCarona` (`nome`, `confirmar`, `concluir`, `cancelar`).
- **Estados concretos** (pacote `estado`): `EstadoAberta`, `EstadoConfirmada`, `EstadoConcluida`, `EstadoCancelada`.

Transições válidas:

```
Aberta ──confirmar──▶ Confirmada ──concluir──▶ Concluída
  │                        │
  └────────cancelar────────┴──────────────────▶ Cancelada
```

Qualquer outra transição lança `IllegalStateException` ("Uma carona Concluída não pode ser Cancelada."), e a tela mostra a mensagem sem alterar o status. Na `CaronaView`, os botões **Confirmar**, **Concluir** e **Cancelar** substituíram o antigo ComboBox de status.

**Por quê:** o status da carona tem regras de transição reais. Com State, cada regra fica no estado correspondente e novos estados entram sem mexer nos existentes.

## Validação e regras de negócio

- Campos obrigatórios não podem ficar vazios.
- Números aceitam vírgula ou ponto (`10,50`) e rejeitam `NaN`, infinito e texto.
- Saldo da Carteira não aceita valor negativo; o valor da Transação precisa ser maior que zero; vagas da Carona e funcionários da Empresa precisam ser pelo menos 1.
- CPF, CNPJ e placa são validados pelo formato (`Types.CPF`, `Types.CNPJ`, `Types.Placa`).
- Transação com origem igual ao destino é rejeitada. A hora da transação é definida na criação e não muda ao editar.
- Se um passageiro de uma transação for excluído, a tela mostra "(passageiro removido)".
- Ao dar duplo clique numa linha, ela vira o item em edição: o botão Atualizar altera aquele item, mesmo que outra linha seja selecionada depois.

## Testes

Não há testes automatizados no projeto. A última rodada foi manual, pela interface, com a pasta `data/` apagada antes, e cobriu 24 itens em 7 grupos, todos aprovados:

1. Carona e State (criar, Confirmar, Concluir, transições inválidas, aviso sem seleção).
2. Edição da Carona preservando o status.
3. Atualizar alterando a linha certa.
4. Transação (origem igual ao destino, hora preservada, passageiro removido).
5. Valores (`NaN`, `-5`, `abc`, `10,50`, valor `0`).
6. Casos antigos (empresa com 67 funcionários, ano 3000, CPF curto, placa inválida).
7. Persistência ao fechar e reabrir o app.

## Limitações conhecidas

- O domínio é simples: a Transação não movimenta o saldo da Carteira, e os vínculos entre entidades (motorista da Carona, titular da Carteira) são texto livre, sem referência a Passageiro.
- CPF e CNPJ são validados só pelo formato, sem dígitos verificadores.
- O próximo ID é o maior existente mais um, então o ID do último registro excluído pode ser reutilizado.
- A persistência usa serialização Java: se a estrutura de uma classe mudar, os arquivos `.dat` antigos deixam de ser lidos e devem ser apagados.

## Estrutura do projeto

```
src/main/java/com/poo/javafx/
├── App.java, MenuView.java, Router.java
├── Model.java, Repository.java
├── CRUDController.java, CRUDView.java
├── Types/            CPF, CNPJ, Placa
├── validacao/        Strategy das regras de validação
└── Instanciacao/     Model, View e Controller de cada entidade
    ├── Andre/        Benefício e Carona (com o pacote estado/)
    ├── Eduardo/      Trajeto e Transação
    ├── JoaoMosson/   Passageiro e Veículo
    └── JoaoPozzan/   Carteira e Empresa
docs/                 imagens das telas e o relatório antigo (RA3 de POO)
```

Tecnologias: Java 21, JavaFX 21.0.6, Maven (wrapper incluso) e jfxtras-controls 17-r1.
