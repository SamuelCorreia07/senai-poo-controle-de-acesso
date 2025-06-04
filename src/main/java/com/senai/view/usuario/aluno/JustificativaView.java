package com.senai.view.usuario.aluno;

import com.senai.control.usuario.aluno.JustificativaController;
import com.senai.model.usuario.aluno.Justificativa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class JustificativaView {
    private final Scanner scanner = new Scanner(System.in);
    private final JustificativaController controller = new JustificativaController();

    public static void main(String[] args) {
        JustificativaView view = new JustificativaView();
        view.menu();
    }

    private void menu() {
        String opcao;
        String menuJustificativa = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar justificativa                         |
                |       2 - Atualizar justificativa                         |
                |       3 - Deletar justificativa                           |
                |       4 - Listar justificativas                           |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                """;
        do {
            System.out.println(menuJustificativa);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        String tipo = scannerPrompt("""
                Tipo da justificativa:
                    1. Falta
                    2. Ocorrência
                """);
        String descricao = scannerPrompt("Descrição: ");
        LocalDate dataJustificada = lerDataJustificada(scanner);
        int qtdDias = scannerPromptInt("Quantidade de Dias:");
        System.out.println(controller.cadastrarJustificativa(tipo, descricao, dataJustificada, qtdDias));
    }

    private void atualizar() {
        int id = scannerPromptInt("ID: ");
        String tipo = scannerPrompt("""
                Tipo da justificativa:
                    1. Falta
                    2. Ocorrência
                """);
        String descricao = scannerPrompt("Descrição: ");
        LocalDate dataJustificada = lerDataJustificada(scanner);
        int qtdDias = scannerPromptInt("Quantidade de Dias:");
        System.out.println(controller.atualizarJustificativa(id, tipo, descricao, dataJustificada, qtdDias));
    }

    private void remover() {
        int id = scannerPromptInt("ID: ");
        System.out.println(controller.removerJustificativa(id));
    }

    private void listar() {
        System.out.println("--- JUSTIFICATIVAS ---");
        for (Justificativa j : controller.listarJustificativas()) {
            System.out.printf("""
                ID: %d
                Tipo: %s
                Descrição: %s
                Status: %s
                Data justificada: %s
                Data/Hora: %s
                Quantidade de dias: %d
                Prazo aceite: %d dias
                
                """, j.getId(), j.getTipo(), j.getDescricao(), j.getStatus(), j.getDataJustificadaFormatada(), j.getDataHoraFormatada(), j.getQtdDias(), j.getPrazoAceite());
        }
    }

    private String scannerPrompt(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int scannerPromptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private LocalDate lerDataJustificada(Scanner scanner) {
        System.out.print("Informe a data justificada (dd/MM/yyyy): ");
        String dataJustificada = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataJustificada, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Tente novamente no formato dd/MM/yyyy.");
            return lerDataJustificada(scanner);
        }
    }
}
