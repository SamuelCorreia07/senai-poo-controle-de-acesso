package com.senai.view.usuario;

import com.senai.control.usuario.AQVController;
import com.senai.model.usuario.AQV;

import java.util.List;
import java.util.Scanner;

public class AqvView {
    private final AQVController controller = new AQVController();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AqvView view = new AqvView();
        view.menu();
    }

    private void menu() {
        int opcao;
        do {
            System.out.print("""
                
                ===================== MENU AQV =====================
                1 - Cadastrar AQV
                2 - Listar AQVs
                3 - Atualizar AQV
                4 - Remover AQV
                0 - Sair
                ====================================================
                Escolha uma opção: """);

            opcao = lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Motivo do atraso: ");
        String motivo = scanner.nextLine();

        System.out.print("Data do atraso (yyyy-mm-dd): ");
        String data = scanner.nextLine();

        AQV novo = new AQV(0, nome, matricula, motivo, data, -1);
        controller.inserir(novo);

        System.out.println("AQV cadastrado com sucesso.");
    }

    private void listar() {
        List<AQV> lista = controller.listarAQVs();
        if (lista.isEmpty()) {
            System.out.println("Nenhum AQV registrado.");
            return;
        }

        System.out.println("\n======= LISTA DE AQVs =======");
        for (AQV a : lista) {
            System.out.printf("""
                ID: %d
                Aluno: %s
                Matrícula: %s
                Motivo: %s
                Data: %s
                ---------------------------------------
                """, a.getId(), a.getNomeAluno(), a.getMatriculaAluno(), a.getMotivoAtraso(), a.getDataRegistro());
        }
    }

    private void atualizar() {
        listar();
        System.out.print("ID do AQV a atualizar: ");
        int id = lerInt();

        AQV existente = AQVController.buscarPorId(id);
        if (existente == null) {
            System.out.println("AQV não encontrado.");
            return;
        }

        System.out.print("Novo nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("Nova matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Novo motivo do atraso: ");
        String motivo = scanner.nextLine();

        System.out.print("Nova data do atraso (yyyy-mm-dd): ");
        String data = scanner.nextLine();

        AQV atualizado = new AQV(id, nome, matricula, motivo, data, -1);
        controller.atualizar(id, atualizado);

        System.out.println("AQV atualizado com sucesso.");
    }

    private void remover() {
        listar();
        System.out.print("ID do AQV a remover: ");
        int id = lerInt();

        System.out.print("Tem certeza que deseja remover este AQV? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            controller.remover(id);
            System.out.println("AQV removido com sucesso.");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Digite novamente: ");
            }
        }
    }
}