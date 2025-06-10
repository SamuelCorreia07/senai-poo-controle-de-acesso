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
        String tipo = scannerPrompt("\nTipo " +
                "\n\t1 - Aluno" +
                "\n\t2 - Professor" +
                "\n\t3 - Coordenador" +
                "\n\t4 - AQV");

        String nome = scannerPrompt("\tNome: ");
        String login = scannerPrompt("\tLogin: ");
        String senha = scannerPrompt("\tSenha: ");

        if (tipo.equals("1")) {
            int idade = scannerPromptInt("\tIdade: ");
            String rfid = scannerPrompt("\tID do cartão RFID: ");
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha, idade, rfid) + "\n");
        } else if (tipo.equals("2")) {
            String disciplina = scannerPrompt("\tDisciplina: ");
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha, disciplina) + "\n");
        } else if (tipo.equals("3")) {
            String departamento = scannerPrompt("\tDepartamento: ");
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha, departamento) + "\n");
        } else if (tipo.equals("4")) {
            System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, login, senha) + "\n");
        }
    }


    private void atualizar() {
        String tipo = scannerPrompt("\nTipo " +
                "\n\t1 - Aluno" +
                "\n\t2 - Professor" +
                "\n\t3 - Coordenador" +
                "\n\t4 - AQV");
        int id = scannerPromptInt("\nID: ");
        String nome = scannerPrompt("\tNovo nome: ");
        String login = scannerPrompt("\tNovo Login: ");
        String senha = scannerPrompt("\tNova Senha: ");

        if (tipo.equals("1")) {
            int idade = scannerPromptInt("\tNova idade: ");
            String rfid = scannerPrompt("\tNovo RFID: ");
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha, idade, rfid) + "\n");
        } else if (tipo.equals("2")) {
            String disciplina = scannerPrompt("\tNova disciplina: ");
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha, disciplina) + "\n");
        } else if (tipo.equals("3")) {
            String departamento = scannerPrompt("\tNovo departamento: ");
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha, departamento) + "\n");
        } else if (tipo.equals("4")) {
            System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, login, senha) + "\n");
        }
    }

    private void remover() {
        String tipo = scannerPrompt("\nTipo " +
                "\n\t1 - Aluno" +
                "\n\t2 - Professor" +
                "\n\t3 - AQV" +
                "\n\t4 - Coordenador");
        int id = scannerPromptInt("ID: ");
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
