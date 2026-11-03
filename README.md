# Migração para EclipseLink

## 1. Reorganização dos Pacotes (*Package by Feature*)

* **Alteração:** Remoção do pacote genérico `Instanciacao/` e das subpastas nomeadas por membros da equipe (`Andre`, `Eduardo`, `JoaoMosson`, `JoaoPozzan`). O código foi redistribuído em pacotes de funcionalidade: `beneficio`, `carona`, `carteira`, `empresa`, `passageiro`, `trajeto`, `transacao` e `veiculo`.
* **Justificativa:**
* Adoção das convenções idiomáticas do Java (pacotes estritamente em minúsculas e no singular).
* Eliminação de silos por autor de código, agrupando `Model`, `View` e `Controller` de cada domínio em um mesmo local (*alta coesão*), o que facilita a manutenção e modularidade do sistema.



---

## 2. Manutenção e Adaptação da Camada de Repositório (`Repository<T>`)

* **Alteração:** A classe genérica `Repository<T>` foi mantida com sua interface pública inalterada (`adicionar`, `atualizar`, `remover`, `comID`, `objetos`), mas seu motor interno foi reescrito. As operações de leitura/escrita em arquivos binários (`.dat`) via `ObjectInputStream`/`ObjectOutputStream` foram substituídas pela API do JPA (`EntityManager`, Criteria API e transações).
* **Justificativa:**
* **Desacoplamento e preservação de contrato:** Como todos os controllers JavaFX já dependiam dos métodos de `Repository<T>`, a manutenção da assinatura evitou retrabalho e quebras em cascata na interface gráfica.
* **Arquitetura em três camadas:** O repositório encapsula o ciclo de vida do `EntityManager` e o controle transacional (`begin`, `commit`, `rollback`), impedindo o vazamento de detalhes de persistência para as camadas de apresentação e controle.
* **Consultas com Criteria API:** O método `objetos()` passou a usar a Criteria API do JPA, tornando as listagens seguras contra erros de digitação em strings JPQL e compatíveis com qualquer anotação de tabela.



---

## 3. Generalização dos Tipos de Domínio e Conversores JPA

* **Alteração:**
* Criação da classe abstrata `Tipo` no pacote `com.poo.javafx.tipo`, centralizando o atributo `valor`, a interface `Serializable` e as implementações canônicas de `toString()`, `equals()` e `hashCode()`. As classes `CPF`, `CNPJ` e `Placa` passaram a herdar dela, contendo apenas suas regras específicas de validação/formatação.
* Implementação de classes concretas de conversão com `@Converter(autoApply = true)`: `CPFConverter`, `CNPJConverter` e `PlacaConverter` (implementando `AttributeConverter<T, String>`).


* **Justificativa:**
* **Eliminação de código duplicado:** `CPF`, `CNPJ` e `Placa` compartilhavam a mesma estrutura de validação e comparação por valor, que foi unificada na base `Tipo`.
* **Compatibilidade com o EclipseLink:** Tipos de domínio personalizados não são mapeados nativamente pelo banco. O uso de `AttributeConverter` instrui o EclipseLink a persistir esses objetos diretamente como colunas relacionais do tipo `VARCHAR`, evitando falhas de serialização binária (`BLOB`) em tempo de inicialização.



---

## 4. Refatoração dos Modelos de Domínio

### 4.1. Classe Base `Model<T>`

* **Alteração:** Anotada com `@MappedSuperclass`, atribuindo `@Id` e `@GeneratedValue(strategy = GenerationType.IDENTITY)` ao campo `id`. Remoção dos métodos manuais `colideCom()`, `checarColisao()` e `herdarDe()`.
* **Justificativa:** Transferência da geração de chaves primárias autoincrementais para o banco de dados e remoção de lógica que existia apenas para contornar limitações da persistência manual em arquivos.

### 4.2. Construtores Vazios Obrigatórios

* **Alteração:** Inclusão de construtores `protected` sem argumentos em todas as 8 classes de modelo.
* **Justificativa:** Exigência formal da especificação JPA para instanciação via *reflection* durante a materialização das entidades pelo provedor de persistência.

### 4.3. Relacionamentos Reais: `TransacaoModel`

* **Alteração:** Substituição dos campos primitivos `userOrigemID` e `userDestinoID` por relacionamentos diretos `@ManyToOne` com `PassageiroModel`, mapeados via `@JoinColumn`.
* **Justificativa:** Eliminação de buscas manuais no repositório dentro da entidade. O EclipseLink passa a gerenciar as chaves estrangeiras e a integridade referencial automaticamente, realizando *joins* otimizados.

### 4.4. Preservação de Dados Imutáveis (`timestamp`)

* **Alteração:** Anotação do campo `timestamp` com `@Column(name = "timestamp", updatable = false)`.
* **Justificativa:** Substituição do gancho `herdarDe()`. Ao marcar a coluna como não atualizável, o ORM omite esse campo nos comandos `UPDATE`, preservando o momento exato de criação do registro sem exigir cópia manual de atributos.

### 4.5. Persistência do Padrão State: `CaronaModel`

* **Alteração:** Criação de `EstadoCaronaConverter` para transformar instâncias da interface `EstadoCarona` em strings descritivas (`"Aberta"`, `"Confirmada"`, etc.) e vice-versa.
* **Justificativa:** O JPA não persiste interfaces polimórficas diretamente. O conversor permitiu manter o padrão comportamental *State* intacto na camada de negócio, enquanto o banco armazena uma coluna textual simples.

### 4.6. Comparação de Entidades (`equals` e `hashCode`)

* **Alteração:** Sobrescrita de `equals()` e `hashCode()` baseados no `id` (ou identificador único) nas entidades, como `PassageiroModel`.
* **Justificativa:** Garantir o funcionamento correto dos componentes visuais do JavaFX (especialmente `ComboBox` e `TableView`), que dependem de `.equals()` para reconciliar seleções de instâncias carregadas em sessões distintas do `EntityManager`.

---

## 5. Migração da Unicidade para o Banco de Dados

* **Alteração:** Remoção das checagens manuais em laço de repetição (`checarUnicidade`) e aplicação de restrições relacionais:
* Restrições simples via `@Column(unique = true)` em `cpf`, `cnpj`, `placa`, `numero_cartao` e `nome` de benefício.
* Restrição composta via `@UniqueConstraint` na tabela `caronas` para a combinação `(motorista, origem, destino)`.
* Captura de `RollbackException` no `Repository<T>`, relançando como `IllegalArgumentException`.


* **Justificativa:**
* **Performance e atomicidade:** Validações de duplicidade em memória sofrem com condições de corrida e exigem carregar tabelas inteiras para verificar colisões. Delegar essa regra aos índices únicos do banco garante integridade transacional com custo $O(1)$.
* **Transparência de interface:** Ao mapear a exceção de violação de *constraint* do banco para `IllegalArgumentException`, os alertas de erro visuais já implementados na camada de controle continuaram funcionando sem modificações.
