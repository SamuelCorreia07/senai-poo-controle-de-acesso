package com.senai.view.turma;

import com.senai.control.turma.TurmaController;
import com.senai.model.turma.Turma;
import com.senai.model.curso.Curso;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class TurmaView {
    private final Scanner scanner = new Scanner(System.in);
    private final TurmaController controller = new TurmaController();

    // Exibição do menu de opções
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

    // Cadastro de uma nova turma
    private void cadastrarTurma() {
        String nome = promptString("\tNome da turma: ");
        int idCurso = promptInt("\tID do curso: ", "Por favor, insira um ID válido.");
        Curso curso = controller.buscarCursoPorId(idCurso);  // Buscar o curso pelo ID
        String dataInicio = promptString("\tData de início (dd/mm/aaaa): ");
        int qtdSemestre = promptInt("\tNúmero de semestres: ", "\nPor favor, insira um número válido.");

        // Entrada de horário
        String horaEntradaStr = promptString("\tHorário de entrada (HH:mm): ");
        LocalTime horarioEntrada = LocalTime.parse(horaEntradaStr);  // Convertendo para LocalTime

        String resultado = controller.cadastrarTurma(nome, curso, dataInicio, qtdSemestre, horarioEntrada);
        System.out.println("\n" + resultado + "\n");
    }

    // Atualização de uma turma existente
    private void atualizarTurma() {
        listarTurmas();
        int id = promptInt("\tID da turma a atualizar: ", "\nPor favor, insira um ID válido.");
        String nome = promptString("\tNovo nome da turma: ");
        int idCurso = promptInt("\tNovo ID do curso: ", "Por favor, insira um ID válido.");
        Curso curso = controller.buscarCursoPorId(idCurso);  // Buscar o curso pelo ID
        String dataInicio = promptString("\tNova data de início (dd/mm/aaaa): ");
        int qtdSemestre = promptInt("\tNovo número de semestres: ", "\nPor favor, insira um número válido.");

        // Entrada de horário
        String horaEntradaStr = promptString("\tNovo horário de entrada (HH:mm): ");
        LocalTime horarioEntrada = LocalTime.parse(horaEntradaStr);

        String resultado = controller.atualizarTurma(id, nome, curso, dataInicio, qtdSemestre, horarioEntrada);
        System.out.println("\n" + resultado + "\n");
    }

    // Remover uma turma
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

    // Listar todas as turmas cadastradas
    private void listarTurmas() {
        List<Turma> turmas = controller.listarTurmas();
        if (turmas.isEmpty()) {
            System.out.println("\nNenhuma turma cadastrada.");
        } else {
            System.out.println("\n--- LISTA DE TURMAS ---");
            for (Turma t : turmas) {
                // Ajustar a exibição para mostrar o nome do curso em vez de seu hashcode
                String nomeCurso = t.getCurso() != null ? t.getCurso().getTitulo() : "Curso não encontrado";

                System.out.printf("ID: %d | Nome: %s | Curso: %s | Início: %s | Semestres: %d | Entrada: %s\n",
                        t.getIdTurma(), t.getNome(), nomeCurso, t.getDataInicio(), t.getQtdSemestre(), t.getHorarioEntrada());
            }
            System.out.println();
        }
    }

    // Função para solicitar uma String ao usuário
    private String promptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    // Função para solicitar um número inteiro ao usuário
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