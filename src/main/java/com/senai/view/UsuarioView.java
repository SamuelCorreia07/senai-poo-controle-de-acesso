package com.senai.view;

import com.senai.control.usuario.UsuarioController;
import com.senai.model.usuario.AQV;
import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.Professor;

import java.util.Scanner;

public class UsuarioView {
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioController controller = new UsuarioController();

    public void menu() {
        String opcao;
        String menuUsuario = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar usuário                               |
                |       2 - Atualizar usuário                               |
                |       3 - Remover usuário                                 |
                |       4 - Listar usuários                                 |
                |       5 - Atribuir/Alterar RFID                           |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                
                """;
        do {
            System.out.print(menuUsuario);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "5" -> atribuirRfid();
                case "0" -> System.out.println("\nVoltando ao menu anterior...\n");
                default -> System.out.println("\nOpção inválida. Tente novamente.\n");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        String opcao = scannerPrompt(
                """
                Tipo:
                    1 - Aluno
                    2 - Professor
                    3 - Coordenador
                    4 - AQV
                
                """);

        String nome = scannerPrompt("\tNome: ");
        String login = scannerPrompt("\tLogin: ");
        String senha = scannerPrompt("\tSenha: ");

        if (opcao.equals("1")) {
            String tipo = "Aluno";
            int idade = scannerPromptInt("\tIdade: ");
            String rfid = scannerPrompt("\tID do cartão RFID: ");
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha, idade, rfid) + "\n");
        } else if (opcao.equals("2")) {
            String tipo = "Professor";
            String disciplina = scannerPrompt("\tDisciplina: ");
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha, disciplina) + "\n");
        } else if (opcao.equals("3")) {
            String tipo = "Coordenador";
            String departamento = scannerPrompt("\tDepartamento: ");
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha, departamento) + "\n");
        } else if (opcao.equals("4")) {
            String tipo = "AQV";
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha) + "\n");
        }
    }


    private void atualizar() {
        String opcao = scannerPrompt("""
                Tipo:
                    1 - Aluno
                    2 - Professor
                    3 - Coordenador
                    4 - AQV
                
                """);
        String tipo;
        int id = scannerPromptInt("\nID: ");
        String nome = scannerPrompt("\tNovo nome: ");
        String login = scannerPrompt("\tNovo Login: ");
        String senha = scannerPrompt("\tNova Senha: ");

        if (opcao.equals("1")) {
            tipo = "Aluno";
            int idade = scannerPromptInt("\tNova idade: ");
            String rfid = scannerPrompt("\tNovo RFID: ");
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha, idade, rfid) + "\n");
        } else if (opcao.equals("2")) {
            tipo = "Professor";
            String disciplina = scannerPrompt("\tNova disciplina: ");
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha, disciplina) + "\n");
        } else if (opcao.equals("3")) {
            tipo = "Coordenador";
            String departamento = scannerPrompt("\tNovo departamento: ");
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha, departamento) + "\n");
        } else if (opcao.equals("4")) {
            tipo = "AQV";
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha) + "\n");
        }
    }

    private void remover() {
        String opcao = scannerPrompt("""
                Tipo:
                    1 - Aluno
                    2 - Professor
                    3 - Coordenador
                    4 - AQV
                
                """);
        String tipo = null;
        int id = scannerPromptInt("ID: ");
        if (opcao.equals("1")) {
            tipo = "Aluno";
        } else if (opcao.equals("2")) {
            tipo = "Professor";
        } else if (opcao.equals("3")) {
            tipo = "Coordenador";
        } else if (opcao.equals("4")) {
            tipo = "AQV";
        }
        System.out.println("\n" + controller.removerUsuario(tipo, id) + "\n");
    }

    private void listar() {
        System.out.println("\n--- Alunos Cadastrados ---");
        for (Aluno a : controller.listarAlunos()) {
            System.out.printf("ID: %d | Nome: %s | RFID: %s\n", a.getId(), a.getNome(), a.getIdCartaoRfid());
        }

        System.out.println("\n--- Professores Cadastrados ---");
        for (Professor p : controller.listarProfessores()) {
            System.out.printf("ID: %d | Nome: %s | Disciplina: %s\n", p.getId(), p.getNome(), p.getDisciplina());
        }
        System.out.println();

        System.out.println("\n--- AQVs Cadastrados ---");
        for (AQV aqv : controller.listarAqvs()) {
            System.out.printf("ID: %d | Nome: %s | Disciplina: %s\n", aqv.getId(), aqv.getNome(), aqv.getTipo());
        }
        System.out.println();

        System.out.println("\n--- Coordenadores Cadastrados ---");
        for (Coordenador coordenador : controller.listarCoordenadores()) {
            System.out.printf("ID: %d | Nome: %s | Disciplina: %s\n", coordenador.getId(), coordenador.getNome(), coordenador.getTipo());
        }
        System.out.println();
    }

    public void atribuirRfid() {
        System.out.println("\nAtribuir/Alterar RFID");
        int id = scannerPromptInt("\tID do aluno: ");
        String rfid = scannerPrompt("\tNovo RFID: ");
        System.out.println("\n" + controller.atribuirRfid(id, rfid) + "\n");
    }

    public void mudarRfid(Aluno aluno) {
        String rfid = scannerPrompt("Novo RFID: ");
        System.out.println("\n" + controller.atribuirRfid(aluno.getId(), rfid) + "\n");
    }

    private String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nEntrada inválida. Digite um número.");
            return scannerPromptInt(msg);
        }
    }
}
