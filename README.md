# Gerenciamento de Preços e Produtos 💸

Bem-vindo ao repositório do projeto de Gerenciamento de Preços e Produtos! Este sistema foi desenvolvido para auxiliar no cadastro, consulta, atualização e exclusão de lojas, produtos e preços, com tratamento robusto de exceções e uso eficiente de banco de dados relacionais.

## 🔧 **Funcionalidades do Sistema**

1. **Gestão de Lojas:**
   - Inserir, listar, buscar por ID, atualizar e deletar lojas.
2. **Gestão de Produtos:**
   - Inserir, listar, buscar por ID, atualizar e deletar produtos.
3. **Gestão de Preços:**
   - Associar preços a produtos e lojas com controle de datas.
4. **Tratamento de Erros:**
   - Manipulação de restrições de chave estrangeira para evitar inconsistências no banco de dados.
5. **Integração com Banco de Dados:**
   - Utilização de tabelas normalizadas para "lojas", "produtos" e "precos".

---

## 📊 **Modelo de Dados**

### Estrutura das Tabelas:

#### **Tabela `lojas`**
| Coluna  | Tipo       | Descrição                     |
|---------|------------|---------------------------------|
| id      | INT (PK)   | Identificador da loja          |
| nome    | VARCHAR    | Nome da loja                   |

#### **Tabela `produtos`**
| Coluna  | Tipo       | Descrição                     |
|---------|------------|---------------------------------|
| id      | INT (PK)   | Identificador do produto       |
| nome    | VARCHAR    | Nome do produto                |

#### **Tabela `precos`**
| Coluna         | Tipo       | Descrição                        |
|----------------|------------|--------------------------------|
| id            | INT (PK)   | Identificador do preço         |
| produto_id    | INT (FK)   | Referência ao produto          |
| loja_id       | INT (FK)   | Referência à loja             |
| preco         | DECIMAL    | Valor do produto              |
| data_insercao | DATETIME   | Data de registro do preço     |

---

## 🔨 **Tecnologias Utilizadas**

- **Linguagem de Programação:** Java
- **Banco de Dados:** MySQL
- **Bibliotecas:**
  - JDBC para conexão ao banco de dados.
  - Utilização de `try-catch` e `try-with-resources` para manipulação segura de recursos.
- **Padrões de Projeto:** Programação Orientada a Objetos (POO) e Data Access Object (DAO)

---

## 🌐 **Configuração do Ambiente**

1. **Pré-requisitos:**
   - Java 8+
   - MySQL 5.7+
   - IDE para desenvolvimento (Eclipse, IntelliJ IDEA, etc.)
2. **Configuração do Banco de Dados:**
   - Crie o banco de dados `rastreamentovalores`.
   - Execute os scripts de criação de tabelas descritos na seção de modelo de dados.
3. **Configuração do Projeto:**
   - Configure o arquivo `db.properties` com as credenciais do banco de dados:
     ```properties
     db.url=jdbc:mysql://localhost:3306/rastreamentovalores
     db.user=seu_usuario
     db.password=sua_senha
     ```
---

## 🏆 **Diferenciais do Projeto**

- **Manutenção Fácil:** Métodos bem documentados e organizados.
- **Tratamento de Exceções:** Uso de mensagens claras e informativas para erros de chave estrangeira.
- **Formato Avançado de Saída:**
  - Exemplo do método `toString` da classe `Preco`:
    ```java
    @Override
    public String toString() {
        return "Preco {" +
               "\n  id: " + id +
               ",\n  produtoId: " + produtoId +
               ",\n  lojaId: " + lojaId +
               ",\n  preco: " + preco +
               ",\n  dataInsercao: " + dataInsercao +
               "\n}";
    }
    ```

---

## 🌐 **Contribuições**

Se você quiser contribuir para o projeto, siga os passos abaixo:

1. Realize um fork deste repositório.
2. Crie um branch para sua nova funcionalidade:
   ```bash
   git checkout -b minha-funcionalidade
   ```
3. Faça o commit das suas alterações:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Envie para o repositório remoto:
   ```bash
   git push origin minha-funcionalidade
   ```
5. Abra um Pull Request!

---

## 🎮 **Contato**

Criado por: **Victor da Silva Fernandes**

- **LinkedIn:** [www.linkedin.com/in/vicksfernandes](#)
- **GitHub:** [https://github.com/swaggyvitu](#)
- **E-mail:** victor.fernandes2004@gmail.com

Obrigado por visitar este repositório! :rocket:

