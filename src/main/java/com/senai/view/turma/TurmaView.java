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
                
                --- MENU DE TURMAS ---
                
                    1. Cadastrar turma
                    2. Atualizar turma
                    3. Remover turma
                    4. Listar turmas
                    0. Voltar
                    
                """;
        do {
            System.out.print(menuTurma);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private static void cadastrar() {
        String nome = scannerPrompt("Nome da turma: ");
        String curso = scannerPrompt("Curso: ");
        String dataInicio = scannerPrompt("Data de inicio: ");
        int qtdSemestre = scannerPromptInt("Numero de semestres: ");
        String horaEntrada = scannerPrompt("Hora de entrada: ");
        System.out.println(turmaController.cadastrarTurma(nome, curso, dataInicio, qtdSemestre, horaEntrada));
    }

    private static void atualizar() {
        listar();
        int idTurma = scannerPromptInt("idTurma: ");
        String nome = scannerPrompt("Nome da turma: ");
        String curso = scannerPrompt("Curso: ");
        String dataInicio = scannerPrompt("Data de inicio: ");
        int qtdSemestre = scannerPromptInt("Numero de semestres: ");
        String horaEntrada = scannerPrompt("Hora de entrada: ");
        turmaController.atualizarTurma(idTurma, nome, curso, dataInicio, qtdSemestre, horaEntrada);
    }

    private static void remover() {
        listar();
        int id = scannerPromptInt("ID da turma: ");
        System.out.println(turmaController.removerTurma(id));
    }

    public static void listar() {
        for (Turma t : turmaController.listarTurmas()){
            System.out.printf("ID: %d | Nome: %s | Curso: %s | Data Inicio: %s | Semestres: %d | Hora Entrada: %s\n",
                    t.getIdTurma(),t.getNome(), t.getCurso(),  t.getDataInicio(), t.getQtdSemestre(), t.getHorarioEntrada());
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
