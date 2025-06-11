package com.senai.view.curso;

import com.senai.control.curso.CursoController;
import com.senai.model.curso.Curso;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class CursoView {
    private final Scanner scanner = new Scanner(System.in);
    private final CursoController controller = new CursoController();

    public void menuCursoView() {
        String opcao;
        String menu = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar curso                                 |
                |       2 - Atualizar curso                                 |
                |       3 - Remover curso                                   |
                |       4 - Listar cursos                                   |
                |       5 - Inserir UC em um curso                          |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                
                """;

        do {
            System.out.print(menu);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrarCurso();
                case "2" -> atualizarCurso();
                case "3" -> removerCurso();
                case "4" -> listarCursos();
                case "5" -> inserirUC();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarCurso() {
        String titulo = scannerPromptString("\tTítulo do curso: ");
        int cargaHoraria = scannerPromptInt("\tCarga horária do curso: ", "Por favor, insira um número válido.");
        String tipo = scannerPromptTipo();

        int tolerancia = scannerPromptInt("\tTolerância (minutos): ", "Por favor, insira um número válido.");

        String resultado = controller.cadastrarCurso(titulo, cargaHoraria, tipo, tolerancia);
        System.out.println(resultado);
    }

    private void atualizarCurso() {
        listarCursos();
        int id = scannerPromptInt("\n\tID do curso a atualizar: ", "Por favor, insira um ID válido.");
        String titulo = scannerPromptString("\tNovo título do curso: ");
        int cargaHoraria = scannerPromptInt("\tNova carga horária: ", "Por favor, insira um número válido.");
        String tipo = scannerPromptTipo();

        int tolerancia = scannerPromptInt("\tNova tolerância (minutos): ", "Por favor, insira um número válido.");

        String resultado = controller.atualizarCurso(id, titulo, cargaHoraria, tipo, tolerancia);
        System.out.println(resultado);
    }

    private void removerCurso() {
        listarCursos();
        int id = scannerPromptInt("\n\tID do curso a remover: ", "Por favor, insira um ID válido.");
        System.out.print("\nTem certeza que deseja remover o curso com ID '" + id + "'? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S")) {
            String resultado = controller.removerCurso(id);
            System.out.println(resultado);
        } else {
            System.out.println("\nRemoção cancelada!");
        }
    }

    private void listarCursos() {
        List<Curso> cursos = controller.listarCursos();
        if (cursos.isEmpty()) {
            System.out.println("\nNenhum curso encontrad@!");
        } else {
            System.out.println("\n--- LISTA DE CURSOS ---");
            for (Curso c : cursos) {
                System.out.printf("ID: %d | Título: %s | Carga Horária: %d Horas | Tipo: %s | Tolerância: %s min\n",
                        c.getIdCurso(), c.getTitulo(), c.getCargaHoraria(), c.getTipo(), c.getTolerancia());
            }
        }
    }

    private void inserirUC() {
        listarCursos();
        int idCurso = scannerPromptInt("\n\tID do curso a atualizar: ", "Por favor, insira um ID válido.");
        String nome = scannerPromptString("\tNome da UC: ");
        int cargaHoraria = scannerPromptInt("\tCarga horária da UC: ", "Por favor, insira um número válido.");
        int qtdSemestres = scannerPromptInt("\tQuantidade de semestres da UC: ", "Por favor, insira um número válido.");

        String resultado = controller.inserirUC(idCurso, nome, cargaHoraria, qtdSemestres);
        System.out.println(resultado);
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

    private String scannerPromptTipo() {
        String tipo = "";
        boolean valido = false;
        while (!valido) {
            System.out.print("\tTipo do curso (1=CAI, 2=TEC): ");
            tipo = scanner.nextLine().trim();
            switch (tipo) {
                case "1" -> {
                    tipo = "CAI";
                    valido = true;
                }
                case "2" -> {
                    tipo = "TEC";
                    valido = true;
                }
                default -> System.out.println("Opção inválida! Digite '1' para CAI ou '2' para TEC.");
            }
        }
        return tipo;
    }
}