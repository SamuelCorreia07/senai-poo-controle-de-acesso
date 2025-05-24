package com.senai.view;

import com.senai.control.usuario.aluno.OcorrenciaController;
import com.senai.model.usuario.aluno.Ocorrencia;

import java.util.Scanner;

public class OcorrenciaView {
    private final Scanner scanner = new Scanner(System.in);
    private final OcorrenciaController controller = new OcorrenciaController();

    public static void main(String[] args) {

    }

    private void menu() {
        String opcao;
        String menuOcorrencia = """
                --- MENU DE OCORRÊNCIAS ---
                
                    1. Cadastrar ocorrência
                    2. Atualizar ocorrência
                    3. Remover ocorrência
                    4. Listar ocorrências
                    0. Sair
                """;
        do {
            System.out.println(menuOcorrencia);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        String tipo = scannerPrompt("""
                Tipo da ocorrência:
                    1. Entrada
                    2. Saída
                """);
        String descricao = scannerPrompt("Descrição: ");
        System.out.println(controller.cadastrarOcorrencia(tipo, descricao));
    }

    private void atualizar() {
        int id = scannerPromptInt("ID: ");
        String tipo = scannerPrompt("Tipo: ");
        String descricao = scannerPrompt("Descrição: ");
        System.out.println(controller.atualizarOcorrencia(id, tipo, descricao));
    }

    private void remover() {
        int id = scannerPromptInt("ID: ");
        System.out.println(controller.removerOcorrencia(id));
    }

    private void listar() {
        System.out.println("--- Ocorrências ---");
        for (Ocorrencia o : controller.listarOcorrencias()) {
            System.out.printf("""
                ID: %d
                Tipo: %s
                Descrição: %s
                Status: %s
                Data/Hora: %s
                
                """, o.getId(), o.getTipo(), o.getDescricao(), o.getStatus(), o.getDataHora());
        }
    }

    private String scannerPrompt(String msg) {
        System.out.println(msg);
        return scanner.nextLine();
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

}
