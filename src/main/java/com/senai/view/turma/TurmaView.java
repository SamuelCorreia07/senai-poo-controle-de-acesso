package com.senai.view.turma;

import com.senai.control.turma.TurmaController;
import com.senai.model.turma.Turma;

import java.util.Scanner;

//Feito por Victor
public class TurmaView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TurmaController turmaController = new TurmaController();

    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        String opcao;
        String menuTurma = """
                
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
            System.out.print(menuTurma);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("\nVoltando...");
                default -> System.out.println("\nOpção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private static void cadastrar() {
        System.out.println("\nPreencha as informações a seguir:");
        String nome = scannerPrompt("\tNome da turma: ");
        String curso = scannerPrompt("\tCurso: ");
        String dataInicio = scannerPrompt("\tData de inicio: ");
        int qtdSemestre = scannerPromptInt("\tNumero de semestres: ");
        String horaEntrada = scannerPrompt("\tHora de entrada: ");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(turmaController.cadastrarTurma(nome, curso, dataInicio, qtdSemestre, horaEntrada));
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    private static void atualizar() {
        listar();
        System.out.println("\nPreencha as informações a seguir:");
        int idTurma = scannerPromptInt("\tidTurma: ");
        String nome = scannerPrompt("\tNome da turma: ");
        String curso = scannerPrompt("\tCurso: ");
        String dataInicio = scannerPrompt("\tData de inicio: ");
        int qtdSemestre = scannerPromptInt("\tNumero de semestres: ");
        String horaEntrada = scannerPrompt("\tHora de entrada: ");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
        turmaController.atualizarTurma(idTurma, nome, curso, dataInicio, qtdSemestre, horaEntrada);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    private static void remover() {
        listar();
        System.out.println("\nPreencha as informações a seguir:");
        int id = scannerPromptInt("\tID da turma: ");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(turmaController.removerTurma(id));
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
    }

    public static void listar() {
        for (Turma t : turmaController.listarTurmas()){
            System.out.println(" --- LISTA DE TURMAS ---- ");
            System.out.printf("ID: %d | Nome: %s | Curso: %s | Data Inicio: %s | Semestres: %d | Hora Entrada: %s\n",
                    t.getIdTurma(),t.getNome(), t.getCurso(),  t.getDataInicio(), t.getQtdSemestre(), t.getHorarioEntrada());
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------\n");
        }
    }

    private static String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }
}
