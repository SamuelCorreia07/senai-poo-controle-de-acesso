package com.senai.view.curso;

import com.senai.control.curso.CursoController;
import com.senai.model.curso.Curso;

import java.util.Scanner;

//Feito por Victor
public class CursoView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CursoController cursoController = new CursoController();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        String opcao;
        String menuCurso = """
                
                --- MENU DE CURSOS ---
                
                    1. Cadastrar curso
                    2. Atualizar curso
                    3. Remover curso
                    4. Listar cursos
                    0. Voltar
                    
                """;
        do {
            System.out.print(menuCurso);
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
        String titulo = scannerPrompt("Titulo do Curso: ");
        int cargaHoraria = scannerPromptInt("Carga horária do curso: ");
        String tipo = scannerPrompt("Tipo do curso (1=CAI, 2=TEC): ");
        switch (tipo){
            case "1" -> tipo = "CAI";
            case "2" -> tipo = "TEC";
        }
        int tolerancia = scannerPromptInt("Tolerância (minutos): ");
        System.out.println(cursoController.cadastrarCurso(titulo, cargaHoraria, tipo, tolerancia));
    }

    private static void atualizar() {
        listar();
        int idCurso = scannerPromptInt("idCurso: ");
        String titulo = scannerPrompt("Titulo do Curso: ");
        int cargaHoraria = scannerPromptInt("Carga horária do curso: ");
        String tipo = scannerPrompt("Tipo do curso (1=CAI, 2=TEC): ");
        switch (tipo){
            case "1" -> tipo = "CAI";
            case "2" -> tipo = "TEC";
        }
        int tolerancia = scannerPromptInt("Tolerância: ");
        cursoController.atualizarCurso(idCurso, titulo, cargaHoraria, tipo, tolerancia);
    }

    private static void remover() {
        listar();
        int id = scannerPromptInt("ID do Curso: ");
        System.out.println(cursoController.removerCurso(id));
    }

    public static void listar() {
        for (Curso c : cursoController.listarCursos()){
            System.out.printf("ID: %d | Título: %s | Carga Horária: %d Horas| Tipo(CAI, TEC): %s | Tolerância: %d Minutos|\n",
                    c.getIdCurso(), c.getTitulo(), c.getCargaHoraria(), c.getTipo(), c.getTolerancia());
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
