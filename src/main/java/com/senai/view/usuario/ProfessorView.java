package com.senai.view.usuario;

import com.senai.control.usuario.ProfessorController;
import com.senai.model.usuario.Professor;

import java.util.List;
import java.util.Scanner;

public class ProfessorView {

    private final Scanner scanner = new Scanner(System.in);
    private final ProfessorController controller = new ProfessorController();

    public void menuProfessorView() {
        String opcao;
        String menu = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar professor(a)                          |
                |       2 - Atualizar professor(a)                          |
                |       3 - Remover professor(a)                            |
                |       4 - Listar professor@s                              |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                """;
        do {
            System.out.print(menu);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrarProfessor();
                case "2" -> atualizarProfessor();
                case "3" -> removerProfessor();
                case "4" -> listarProfessores();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarProfessor() {
        String nome = scannerPromptString("\tNome do(a) professor(a): ");
        String login = scannerPromptString("\tLogin: ");
        String senha = scannerPromptString("\tSenha: ");
        String disciplina = scannerPromptString("\tDisciplina: ");

        String resultado = controller.cadastrarProfessor(nome, login, senha, disciplina);
        System.out.println(resultado);
    }

    private void atualizarProfessor() {
        int id = scannerPromptInt("\tID do(a) professor(a): ", "\nPor favor, insira um ID válido.");
        String nome = scannerPromptString("\tNovo nome: ");
        String login = scannerPromptString("\tNovo login: ");
        String senha = scannerPromptString("\tNova senha: ");
        String disciplina = scannerPromptString("\tNova disciplina: ");

        String resultado = controller.atualizarProfessor(id, nome, login, senha, disciplina);
        System.out.println(resultado);
    }

    private void removerProfessor() {
        int id = scannerPromptInt("\tID do(a) professor(a): ", "\nPor favor, insira um ID válido.");
        System.out.print("\nTem certeza que deseja remover o(a) professor(a) com ID '" + id + "'? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S")) {
            String resultado = controller.deletarProfessor(id);
            System.out.println(resultado);
        } else {
            System.out.println("\nRemoção cancelada!");
        }
    }

    private void listarProfessores() {
        List<Professor> professores = controller.listarProfessores();
        if (professores.isEmpty()) {
            System.out.println("\nNenhum professor(a) encontrad@!");
        } else {
            System.out.println("\n======= LISTA DE PROFESSORES =======");
            for (Professor p : professores) {
                System.out.printf("ID: %d | Nome: %s | Disciplina: %s%n", p.getId(), p.getNome(), p.getDisciplina());
            }
        }
    }

    private String scannerPromptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private int scannerPromptInt(String msg, String erroMsg) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(msg);
                numero = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println(erroMsg);
            }
        }
        return numero;
    }
}