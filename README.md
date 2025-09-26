# Sistema de Controle de Acesso Escolar - SENAI

![Java](https://img.shields.io/badge/Java-21-blue.svg) ![Maven](https://img.shields.io/badge/Maven-4.0.0-red.svg) ![Gson](https://img.shields.io/badge/JSON-Gson-orange.svg) ![MQTT](https://img.shields.io/badge/MQTT-Eclipse_Paho-purple.svg) ![WebSocket](https://img.shields.io/badge/WebSocket-Jetty-brightgreen.svg)

Projeto acadêmico desenvolvido em grupo por alunos do SENAI, com o objetivo de criar um sistema de controle de acesso e gestão acadêmica utilizando os conceitos de **Programação Orientada a Objetos** em Java.

O sistema simula um ambiente escolar onde o acesso é controlado por RFID, e diferentes tipos de usuários possuem permissões e funcionalidades específicas. A aplicação é executada via console e utiliza tecnologias de mensageria e comunicação em tempo real.

---

## 🚀 Visão Geral das Funcionalidades

* **Controle de Acesso por RFID (Simulado):**
    * O sistema escuta um tópico **MQTT** para receber IDs de cartões RFID.
    * Processa a entrada, verifica se o aluno está atrasado com base no horário da turma e uma tolerância definida no curso.
    * Em caso de atraso, notifica os professores em tempo real via **WebSocket**.

* **Sistema de Login Baseado em Perfis:**
    * Múltiplos tipos de usuários: **Aluno, Professor, Coordenador, AQV (Agente de Qualidade de Vida) e Administrador**.
    * Menus de console dinâmicos que se adaptam ao perfil do usuário logado.
    * As senhas são armazenadas de forma segura utilizando hash SHA-256.

* **Gestão Acadêmica Completa (CRUDs):**
    * Gerenciamento de **Usuários** (Alunos, Professores, etc.).
    * Gerenciamento de **Cursos** e **Ambientes** (salas, laboratórios).
    * Gerenciamento de **Turmas** e **Horários**.
    * Registro e acompanhamento de **Ocorrências** e **Justificativas** dos alunos.

* **Painel Web para Notificações:**
    * Um arquivo `index.html` é servido para simular um painel de professor que se conecta via WebSocket para receber as notificações de atraso em tempo real.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21:** Linguagem principal do projeto.
* **Maven:** Gerenciador de dependências do projeto.
* **Gson:** Biblioteca do Google para persistência de dados através da serialização e desserialização de objetos Java para arquivos JSON.
* **Eclipse Paho:** Cliente MQTT para se inscrever em tópicos e receber mensagens (simulação de RFID).
* **Jetty & Jakarta WebSocket:** Para criar um servidor WebSocket embarcado, permitindo a comunicação em tempo real do backend com clientes (como o painel do professor).

---

## 🧑‍💻 Equipe de Desenvolvimento

* [Nome do Aluno 1]
* [Nome do Aluno 2]
* [Nome do Aluno 3]
* *(Adicione os nomes dos integrantes do grupo aqui)*

---

## 🏃‍♀️ Como Rodar a Aplicação

1.  **Pré-requisitos:**
    * Java JDK 21 ou superior.
    * Apache Maven.
    * Um broker MQTT (como o Mosquitto) rodando localmente na porta `1883`.

2.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/senai-poo-controle-de-acesso/senai-poo-controle-de-acesso.git](https://github.com/senai-poo-controle-de-acesso/senai-poo-controle-de-acesso.git)
    cd senai-poo-controle-de-acesso
    ```

3.  **Execute a aplicação:**
    * Abra o projeto em sua IDE de preferência (IntelliJ, Eclipse, etc.).
    * Execute o método `main` da classe `com.senai.view.MenuPrincipal`.

4.  **Para testar as funcionalidades:**
    * **Login:** Utilize as credenciais padrão criadas no sistema (ex: usuário `aqv`, senha `1234`).
    * **Painel Web:** Abra o arquivo `src/main/resources/index.html` em um navegador para visualizar as notificações WebSocket.
    * **Simular RFID:** Publique uma mensagem no tópico MQTT `catraca/rfid` com o ID do cartão de um aluno cadastrado. Você pode usar um cliente MQTT como `mosquitto_pub`:
        ```bash
        mosquitto_pub -h localhost -p 1883 -t catraca/rfid -m "ID_DO_RFID_AQUI"
        ```

---

## 🏗️ Estrutura do Projeto

O projeto segue o padrão **Model-View-Controller (MVC)** para garantir a separação de responsabilidades:

* **`com.senai.model`**: Contém as classes de **Modelo**, que representam os dados e a lógica de negócio.
    * **Entidades:** Classes como `Aluno`, `Professor`, `Curso`, etc., que definem a estrutura dos dados.
    * **DAO (Data Access Object):** Classes responsáveis pela persistência dos dados em arquivos JSON, como `AlunoDAO` e `CursoDAO`.

* **`com.senai.view`**: Camada de **Visão**, responsável pela interação com o usuário.
    * Contém todas as classes que gerenciam os menus do console, como `MenuPrincipal`, `AlunoView`, `CursoView`, etc.

* **`com.senai.control`**: Camada de **Controle**, que atua como intermediária entre o Model e a View.
    * Recebe as requisições da View, aciona as regras de negócio e manipula os dados através dos DAOs.

* **`com.senai.util`**: Pacote com classes utilitárias, como `CriptografiaUtil` e adaptadores para `LocalDate`/`LocalTime`.

* **`com.senai.mqtt`** e **`com.senai.websocket`**: Pacotes dedicados à configuração e lógica da comunicação via MQTT e WebSocket.
