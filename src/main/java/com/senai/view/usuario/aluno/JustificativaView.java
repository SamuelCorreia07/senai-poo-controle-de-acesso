package com.senai.view.usuario.aluno;

import com.senai.control.usuario.aluno.JustificativaController;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.aluno.Justificativa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class JustificativaView {
    private final Scanner scanner = new Scanner(System.in);
    private final JustificativaController controller = new JustificativaController();

    public JustificativaView(Aluno aluno) {
    }

    public static void main(String[] args) {
        JustificativaView view = new JustificativaView();
        view.menu();
    }

    private void menu() {
        String opcao;
        String menu = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar justificativa                         |
                |       2 - Atualizar justificativa                         |
                |       3 - Remover justificativa                           |
                |       4 - Listar justificativas                           |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                """;

        do {
            System.out.print(menu);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> remover();
                case "4" -> listar();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrar() {
        String tipo = scannerPromptTipo();
        String descricao = scannerPrompt("\tDescrição: ");
        LocalDate data = lerData("\tData justificada (dd/MM/yyyy): ");
        int qtdDias = scannerPromptInt("\tQuantidade de dias: ", "Digite um número válido.");

        String resultado = controller.cadastrarJustificativa(tipo, descricao, data, qtdDias);
        System.out.println(resultado);
    }

    private void atualizar() {
        listar();
        int id = scannerPromptInt("\n\tID da justificativa: ", "Digite um ID válido.");
        String tipo = scannerPromptTipo();
        String descricao = scannerPrompt("\tNova descrição: ");
        LocalDate data = lerData("\tNova data justificada (dd/MM/yyyy): ");
        int qtdDias = scannerPromptInt("\tNova quantidade de dias: ", "Digite um número válido.");

        String resultado = controller.atualizarJustificativa(id, tipo, descricao, data, qtdDias);
        System.out.println(resultado);
    }

    private void remover() {
        listar();
        int id = scannerPromptInt("\n\tID da justificativa a remover: ", "Digite um ID válido.");
        System.out.print("Tem certeza que deseja remover essa justificativa? (S/N): ");
        String confirma = scanner.nextLine().trim().toUpperCase();
        if (confirma.equals("S")) {
            String resultado = controller.removerJustificativa(id);
            System.out.println(resultado);
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    public void listar() {
        System.out.println("\n--- JUSTIFICATIVAS ---");
        for (Justificativa j : controller.listarJustificativas()) {
            System.out.printf("""
                ID: %d
                Tipo: %s
                Descrição: %s
                Status: %s
                Data justificada: %s
                Data/Hora da Solicitação: %s
                Quantidade de dias: %d
                Prazo de aceite: %d dia(s)
                
                """, j.getId(), j.getTipo(), j.getDescricao(), j.getStatus(),
                    j.getDataJustificadaFormatada(), j.getDataHoraFormatada(),
                    j.getQtdDias(), j.getPrazoAceite());
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

    private LocalDate lerData(String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }

    private String scannerPromptTipo() {
        while (true) {
            System.out.print("""
                Tipo da justificativa:
                    1. Falta
                    2. Ocorrência
                Escolha: """);
            String opcao = scanner.nextLine().trim();
            return switch (opcao) {
                case "1" -> "Falta";
                case "2" -> "Ocorrência";
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    yield null;
                }
            };
        }
    }
}