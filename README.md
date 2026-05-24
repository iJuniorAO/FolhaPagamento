# 📊 Sistema de Folha de Pagamento

## 📋 Descrição do Projeto

Sistema de gestão de folha de pagamento desenvolvido em Java, permitindo o cadastro e calculo e gerenciamento de três tipos de funcionários com cálculos salariais automáticos. Este é um projeto final de conclusão da disciplina de Lógica de Programação.

---

## 🎓 Informações Acadêmicas

- **Instituição:** UNA
- **Disciplina:** Lógica de Programação
- **Desenvolvedor:** Ismael Junior
- **Matrícula:** 2026|1S

---

## 🎯 Objetivos

- Aplicar conceitos de Programação Orientada a Objetos (POO)
- Desenvolver uma aplicação com interface de linha de comando
- Interatividade entre entradas e saída de dados
- Aplicação de estruturas de decisão, laçoes de repetição e armazenamento de dados
- Implementar herança e polimorfismo em Java
- Gerenciar dados de funcionários e cálculos salariais
- Gerar relatórios de folha de pagamento

---

## ✨ Recursos Principais

### 1. **Cadastro de Funcionários**

- **Funcionário Padrão:** Recebe salário fixo de R$ 2.000,00
- **Funcionário Vendedor:** Recebe salário fixo + comissão sobre vendas
- **Funcionário de Operação:** Recebe salário fixo + bônus por peças produzidas

### 2. **Cálculo de Salários Automático**

- Salário Final = Salário Base + Extras (Comissão/Bônus)
- Diferentes tipos de remuneração por categoria

### 3. **Relatório de Folha de Pagamento**

- Exibição formatada de todos os funcionários
- Cálculo de subtotais por tipo
- Percentual de distribuição do orçamento
- Total geral da folha de pagamento

### 4. **Validação de Dados**

- Validação de entrada do usuário
- Prevenção de dados inválidos
- Sistema de alerta e erro com formatação visual

---

## 📁 Estrutura do Projeto

```
FolhaPagamento/
├── README.md
├── src/
│   ├── App.java                          # Classe principal - Menu de controle
│   ├── Tela.java                         # Classe responsável pela interface
│   ├── model/
│   │   ├── Funcionario.java              # Classe base dos funcionários
│   │   ├── FuncionarioVendedor.java      # Classe filha - Funcionário vendedor
│   │   └── FuncionarioOperacao.java      # Classe filha - Funcionário operação
│   └── factory/
│       └── Seeder.java                   # Classe para carregamento de dados de teste
```

---

## 🏗️ Arquitetura e Design Patterns

### Hierarquia de Classes

```
Funcionario (Classe Base)
├── FuncionarioVendedor (Herança)
└── FuncionarioOperacao (Herança)
```

### Design Patterns Utilizados

- **Factory Pattern:** Classe `Seeder` para criar dados de demonstração
- **Template Method:** Métodos abstratos na classe base implementados nas subclasses

---

## 💻 Como Usar

### Pré-requisitos

- Java 8 ou superior instalado
- Compilador Java (javac)

### Menu de Opções

```
1. Cadastrar Funcionário Padrão
2. Cadastrar Funcionário Vendedor
3. Cadastrar Funcionário Operação
4. Verificar Folha de Pagamento
0. Sair
```

---

## 📊 Tipos de Funcionários

### **Funcionário Padrão**

- **Tipo:** PADR
- **Salário:** R$ 2.000,00 (fixo)
- **Extras:** Nenhum

### **Funcionário Vendedor**

- **Tipo:** VEND
- **Salário Fixo:** R$ 2.000,00
- **Bônus:** Comissão sobre valor de vendas
- **Fórmula:** Salário Final = 2.000 + (Valor Vendas × Percentual Comissão)

### **Funcionário de Operação**

- **Tipo:** PROD
- **Salário Fixo:** R$ 2.000,00
- **Bônus:** Produtividade por peça produzida
- **Fórmula:** Salário Final = 2.000 + (Quantidade Peças × Valor Unitário)

---

## 🔍 Dados de Demonstração (Modo Teste)

Para carregar dados de teste automaticamente, altere o valor da constante `SEEDER_FUNCIONARIO` em `App.java` para `true`:

```java
static boolean SEEDER_FUNCIONARIO = true;
```

Serão carregados 6 funcionários de exemplo para teste do sistema.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Paradigma:** Orientação a Objetos
- **Entrada de Dados:** Scanner (Console)
- **Estrutura de Dados:** ArrayList

---

## 🎨 Interface

O sistema utiliza uma interface de linha de comando (CLI) com formatação visual:

- Bordas e separadores para melhor legibilidade
- Mensagens de alerta e erro destacadas
- Tabelas formatadas para o relatório de folha de pagamento
- Feedback visual das operações realizadas

---

## 📌 Notas Importantes

- O salário base padrão é de **R$ 2.000,00**
- Todas as operações de inserção podem ser canceladas
- O sistema valida todos os dados de entrada

---

## 👤 Autor

**Ismael Junior**

- Estudante de Programação
- UNA 2026 | 1º Semestre
- Projeto Final: Lógica de Programação

---

## 📄 Licença

Este projeto foi desenvolvido como trabalho acadêmico e é fornecido como-está para fins educacionais.

---

## 🚀 Melhorias Futuras

- [ ] Criar interface gráfica (GUI) com Swing ou JavaFX
- [ ] Adicionar funcionalidade de edição e exclusão de funcionários
- [ ] Exportar relatórios em PDF
- [ ] Implementar persistência de dados em banco de dados
- [ ] Implementar controle de acesso (login)
- [ ] Adicionar diferentes departamentos e filiais

---

**Versão:** 1.2.1
**Data:** Maio de 2026
