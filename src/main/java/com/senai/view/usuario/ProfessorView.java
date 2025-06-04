package com.senai.view.usuario;

import com.senai.control.usuario.ProfessorController;
import com.senai.model.usuario.Professor;

import java.util.List;
import java.util.Scanner;

public class ProfessorView {
        private static final Scanner scanner = new Scanner(System.in);
        private static final ProfessorController professorController = new ProfessorController();

        public static void main(String[] args) {
            ProfessorView view = new ProfessorView();
            view.menu();
        }

        public static void menu() {
            String opcao;
            String menuProfessor = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar professor                             |
                |       2 - Atualizar professor                             |
                |       3 - Deletar professor                               |
                |       4 - Listar professores                              |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                """;
            do {
                System.out.print(menuProfessor);
                opcao = scanner.nextLine();

                switch (opcao) {
                    case "1" -> cadastrar();
                    case "2" -> atualizar();
                    case "3" -> deletar();
                    case "4" -> listar();
                    case "0" -> System.out.println("Voltando...");
                    default -> System.out.println("Opção inválida.");
                }
            } while (!opcao.equals("0"));
        }

        private static void cadastrar() {
            String nome = scannerPrompt("Nome do Professor: ");
            String disciplina = scannerPrompt("Disciplina do Professor: ");
            System.out.println(professorController.cadastrarProfessor(nome, disciplina));
        }

        private static void atualizar() {
            listar();
            int idProfessor = scannerPromptInt("ID do Professor: ");
            String nome = scannerPrompt("Novo nome do Professor: ");
            String disciplina = scannerPrompt("Nova disciplina do Professor: ");
            System.out.println(professorController.atualizarProfessor(idProfessor, nome, disciplina));
        }

        private static void deletar() {
            listar();
            int id = scannerPromptInt("ID do Professor: ");
            System.out.println(professorController.deletarProfessor(id));
        }

        public static void listar() {
            List<Professor> professores = professorController.listarProfessores();
            if (professores.isEmpty()) {
                System.out.println("Nenhum professor cadastrado.");
            } else {
                System.out.println("\nLista de Professores:");
                for (Professor p : professores) {
                    System.out.printf("ID: %d | Nome: %s | Disciplina: %s\n", p.getId(), p.getNome(), p.getDisciplina());
                }
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

