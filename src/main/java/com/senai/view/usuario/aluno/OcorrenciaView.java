package com.senai.view.usuario.aluno;

import com.senai.control.usuario.aluno.OcorrenciaController;
import com.senai.model.usuario.aluno.Ocorrencia;

import java.util.Scanner;

public class OcorrenciaView {
    private final Scanner scanner = new Scanner(System.in);
    private final OcorrenciaController controller = new OcorrenciaController();

    public static void main(String[] args) {
        new OcorrenciaView().menu();
    }

    private void menu() {
        String opcao;
        String menuOcorrencia = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar ocorrência                            |
                |       2 - Atualizar ocorrência                            |
                |       3 - Remover ocorrência                              |
                |       4 - Listar ocorrências                              |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                """;

        do {
            System.out.print(menuOcorrencia);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Voltando ao menu...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        String tipo = scannerPromptTipo();
        String descricao = scannerPrompt("\tDescrição: ");
        String resultado = controller.cadastrarOcorrencia(tipo, descricao);
        System.out.println(resultado);
    }

    private void atualizar() {
        listar();
        int id = scannerPromptInt("\n\tID da ocorrência: ", "Digite um ID válido.");
        String tipo = scannerPromptTipo();
        String descricao = scannerPrompt("\tNova descrição: ");
        String resultado = controller.atualizarOcorrencia(id, tipo, descricao);
        System.out.println(resultado);
    }

    private void remover() {
        listar();
        int id = scannerPromptInt("\n\tID da ocorrência a remover: ", "Digite um ID válido.");
        System.out.print("Tem certeza que deseja remover essa ocorrência? (S/N): ");
        String confirma = scanner.nextLine().trim().toUpperCase();
        if (confirma.equals("S")) {
            String resultado = controller.removerOcorrencia(id);
            System.out.println(resultado);
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void listar() {
        System.out.println("\n--- OCORRÊNCIAS ---");
        for (Ocorrencia o : controller.listarOcorrencias()) {
            System.out.printf("""
                ID: %d
                Tipo: %s
                Descrição: %s
                Status: %s
                Data/Hora: %s
                
                """, o.getId(), o.getTipo(), o.getDescricao(), o.getStatus(), o.getDataHoraFormatada());
        }
    }

    private String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private int scannerPromptInt(String msg, String erro) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(msg);
                valor = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println(erro);
            }
        }
        return valor;
    }

    private String scannerPromptTipo() {
        while (true) {
            System.out.print("""
                Tipo da ocorrência:
                    1. Entrada
                    2. Saída
                Escolha: """);
            String opcao = scanner.nextLine().trim();
            return switch (opcao) {
                case "1" -> "Entrada";
                case "2" -> "Saída";
                default -> {
                    System.out.println("\nOpção inválida. Escolha 1 ou 2.");
                    yield null;
                }
            };
        }
    }
}