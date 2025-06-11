package com.senai.view.curso;

import com.senai.control.curso.AmbienteController;
import com.senai.model.curso.Ambiente;
import com.senai.view.usuario.aluno.JustificativaView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AmbienteView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AmbienteController ambienteController = new AmbienteController();

    public void menuAmbiente() {
        String opcao;
        String menuAmbiente = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar Ambiente                              |
                |       2 - Atualizar Ambiente                              |
                |       3 - Deletar Ambiente                                |
                |       4 - Listar Ambientes                                |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                
                """;
        do {
            System.out.print(menuAmbiente);
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrar();
                case "2" -> atualizar();
                case "3" -> deletar();
                case "4" -> listar();
                case "0" -> System.out.println("Voltando...\n");
                default -> System.out.println("Opção inválida!\n");
            }
        } while (!opcao.equals("0"));
    }

    private static void cadastrar() {
        String nome = scannerPrompt("Nome do Ambiente: ");
        String tipo = scannerPrompt("\tTipo do Ambiente (Sala/Laboratório): ");
        System.out.println(ambienteController.cadastrarAmbiente(nome, tipo));
    }

    private static void atualizar() {
        listar();
        int idAmbiente = scannerPromptInt("\nID do Ambiente: ");
        Optional<Ambiente> ambienteOpt = ambienteController.buscarPorId(idAmbiente);
        if (ambienteOpt.isPresent()) {
            String nome = scannerPrompt("\tNovo nome do Ambiente: ");
            String tipo = scannerPrompt("\tNovo tipo do Ambiente (Sala/Laboratório): ");
            System.out.println(ambienteController.atualizarAmbiente(idAmbiente, nome, tipo));
        } else {
            System.out.println("Ambiente com ID " + idAmbiente + " não encontrado!");
        }
    }

    private static void deletar() {
        listar();
        int id = scannerPromptInt("ID do Ambiente: ");
        System.out.println(ambienteController.deletarAmbiente(id));
    }

    public static void listar() {
        List<Ambiente> ambientes = ambienteController.listarAmbientes();
        if (ambientes.isEmpty()) {
            System.out.println("\nNenhum ambiente cadastrado!");
        } else {
            System.out.println("\nLista de Ambientes:");
            for (Ambiente a : ambientes) {
                System.out.println(a);
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