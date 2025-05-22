package com.senai.view;

import com.senai.control.usuario.aluno.OcorrenciaController;

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
        tipo();
        String descricao = scannerPrompt("Descrição: ");

    }

    private String tipo(){
        String opcaoTipo;
        String menuTipo = """
                Tipo da ocorrência:
                    1. Entrada
                    2. Saída
                """;

        System.out.println(menuTipo);
        opcaoTipo = scanner.nextLine();

        if (opcaoTipo.equals("1")) {
            return "Entrada";
        } else if (opcaoTipo.equals("2")) {
            return "Saida";
        } else {
            return "";
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
