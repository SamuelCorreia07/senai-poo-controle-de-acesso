package com.senai.view;
import com.senai.control.usuario.aluno.AlunoController;
import com.senai.model.usuario.aluno.Aluno;
import java.util.List;
import java.util.Scanner;

public class AlunoView {
    private final Scanner scanner = new Scanner(System.in);
    private final AlunoController controller = new AlunoController();

    public void menu() {
        String opcao;
        String menuPrincipal = """
                --- MENU PRINCIPAL ---
                
                    1 - Cadastrar aluno
                    2 - Atualizar aluno
                    3 - Remover aluno
                    4 - Listar alunos
                    0 - Voltar
                    
                """;
        do {
            System.out.print(menuPrincipal);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrarAluno();
                case "2" -> atualizarAluno();
                case "3" -> removerAluno();
                case "4" -> listarAlunos();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarAluno() {
        String nome = scannerPromptString("\tNome do aluno: ");
        int idade = scannerPromptInt("\tIdade do aluno: ", "\nPor favor, insira uma idade válida.");
        String resultado = controller.cadastrarAluno(nome, idade);
        System.out.println(resultado);
    }

    private void atualizarAluno() {
        int id = scannerPromptInt("\tID do aluno: ", "\nPor favor, insira um ID válido.");
        String nome = scannerPromptString("\tNovo nome do aluno: ");
        int idade = scannerPromptInt("\tNova idade do aluno: ", "\nPor favor, insira uma idade válida.");
        String resultado = controller.atualizarAluno(id, nome, idade);
        System.out.println(resultado);
    }

    private void removerAluno() {
        int id = scannerPromptInt("\tID do aluno: ", "\nPor favor, insira um ID válido.");
        System.out.print("\nTem certeza que deseja remover o aluno com ID " + id + "? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S")) {
            String resultado = controller.removerAluno(id);
            System.out.println(resultado);
        } else {
            System.out.println("\nRemoção cancelada!");
        }
    }

    private void listarAlunos() {
        List<Aluno> alunos = controller.listarAlunos();
        if (alunos.isEmpty()) {
            System.out.println("\nNenhum alun@ encontrad@!");
        } else {
            for (Aluno a : alunos) {
                System.out.println(a);
            }
        }
    }

    // Métodos auxiliares
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