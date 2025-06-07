package com.senai.view.turma;

import com.senai.control.turma.TurmaController;
import com.senai.model.turma.Turma;

import java.util.List;
import java.util.Scanner;

public class TurmaView {
    private final Scanner scanner = new Scanner(System.in);
    private final TurmaController controller = new TurmaController();

    public static void main(String[] args) {
        TurmaView view = new TurmaView();
        view.menuTurmaView();
    }

    public void menuTurmaView() {
        String opcao;
        String menu = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar turma                                 |
                |       2 - Atualizar turma                                 |
                |       3 - Remover turma                                   |
                |       4 - Listar turmas                                   |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                
                """;
        do {
            System.out.print(menu);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrarTurma();
                case "2" -> atualizarTurma();
                case "3" -> removerTurma();
                case "4" -> listarTurmas();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarTurma() {
        String nome = promptString("\tNome da turma: ");
        String curso = promptString("\tCurso: ");
        String dataInicio = promptString("\tData de início (dd/mm/aaaa): ");
        int qtdSemestre = promptInt("\tNúmero de semestres: ", "\nPor favor, insira um número válido.");
        String horaEntrada = promptString("\tHorário de entrada (HH:mm): ");
        String resultado = controller.cadastrarTurma(nome, curso, dataInicio, qtdSemestre, horaEntrada);
        System.out.println("\n" + resultado + "\n");
    }

    private void atualizarTurma() {
        listarTurmas();
        int id = promptInt("\tID da turma a atualizar: ", "\nPor favor, insira um ID válido.");
        String nome = promptString("\tNovo nome da turma: ");
        String curso = promptString("\tNovo curso: ");
        String dataInicio = promptString("\tNova data de início (dd/mm/aaaa): ");
        int qtdSemestre = promptInt("\tNovo número de semestres: ", "\nPor favor, insira um número válido.");
        String horaEntrada = promptString("\tNovo horário de entrada (HH:mm): ");
        String resultado = controller.atualizarTurma(id, nome, curso, dataInicio, qtdSemestre, horaEntrada);
        System.out.println("\n" + resultado + "\n");
    }

    private void removerTurma() {
        listarTurmas();
        int id = promptInt("\tID da turma a remover: ", "\nPor favor, insira um ID válido.");
        System.out.print("\nTem certeza que deseja remover a turma com ID '" + id + "'? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S")) {
            String resultado = controller.removerTurma(id);
            System.out.println("\n" + resultado + "\n");
        } else {
            System.out.println("\nRemoção cancelada!");
        }
    }

    private void listarTurmas() {
        List<Turma> turmas = controller.listarTurmas();
        if (turmas.isEmpty()) {
            System.out.println("\nNenhuma turma cadastrada.");
        } else {
            System.out.println("\n--- LISTA DE TURMAS ---");
            for (Turma t : turmas) {
                System.out.printf("ID: %d | Nome: %s | Curso: %s | Início: %s | Semestres: %d | Entrada: %s\n",
                        t.getIdTurma(), t.getNome(), t.getCurso(), t.getDataInicio(), t.getQtdSemestre(), t.getHorarioEntrada());
            }
            System.out.println();
        }
    }

    private String promptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private int promptInt(String msg, String erroMsg) {
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