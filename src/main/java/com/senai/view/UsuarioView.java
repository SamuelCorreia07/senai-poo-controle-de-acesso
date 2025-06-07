package com.senai.view;

import com.senai.control.usuario.UsuarioController;
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
        String tipo = scannerPrompt("\nTipo (1=Aluno, 2=Professor): ");
        String nome = scannerPrompt("Nome: ");
        String login = scannerPrompt("Login: ");
        String senha = scannerPrompt("Senha: ");
        String dadoExtra = tipo.equals("1")
                ? scannerPrompt("ID do cartão RFID: ")
                : scannerPrompt("Disciplina: ");

        System.out.println("\n" + controller.cadastrarUsuario(tipo, nome, dadoExtra, login, senha) + "\n");
    }

    private void atualizar() {
        String tipo = scannerPrompt("\nTipo " +
                "\n\t1 - Aluno" +
                "\n\t2 -Professor");
        int id = scannerPromptInt("\nID: ");
        String nome = scannerPrompt("\tNovo nome: ");
        String login = scannerPrompt("\tNovo Login: ");
        String senha = scannerPrompt("\tNova Senha: ");
        String dadoExtra = tipo.equals("1")
                ? scannerPrompt("\tNovo RFID: ")
                : scannerPrompt("\tNova disciplina: ");

        System.out.println("\n" + controller.atualizarUsuario(tipo, id, nome, dadoExtra, login, senha) + "\n");
    }

    private void remover() {
        String tipo = scannerPrompt("\nTipo " +
                "\n\t1 - Aluno" +
                "\n\t2 -Professor");
        int id = scannerPromptInt("ID: ");
        System.out.println("\n" + controller.removerUsuario(tipo, id) + "\n");
    }

    private void listar() {
        System.out.println("\n📚 --- Alunos Cadastrados ---");
        for (Aluno a : controller.listarAlunos()) {
            System.out.printf("ID: %d | Nome: %s | RFID: %s\n", a.getId(), a.getNome(), a.getIdCartaoRfid());
        }

        System.out.println("\n🧑‍🏫 --- Professores Cadastrados ---");
        for (Professor p : controller.listarProfessores()) {
            System.out.printf("ID: %d | Nome: %s | Disciplina: %s\n", p.getId(), p.getNome(), p.getDisciplina());
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
