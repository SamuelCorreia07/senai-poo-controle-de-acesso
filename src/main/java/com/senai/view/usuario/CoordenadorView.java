package com.senai.view.usuario;

import com.senai.control.usuario.AQVController;
import com.senai.control.usuario.CoordenadorController;
import com.senai.model.usuario.AQV;
import com.senai.model.usuario.Coordenador;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CoordenadorView {
    public static void main(String[] args) {
        CoordenadorController coordenadorController = new CoordenadorController();
        AQVController aqvController = new AQVController();
        Scanner scanner = new Scanner(System.in);

        int op = -1;
        while (op != 0) {
            System.out.println("\n--- Menu Coordenador ---");
            System.out.println("1 - Cadastrar coordenador");
            System.out.println("2 - Listar coordenadores");
            System.out.println("3 - Atualizar coordenador");
            System.out.println("4 - Remover coordenador");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Departamento: ");
                        String dept = scanner.nextLine();

                        Coordenador c = new Coordenador(0, nome, dept);
                        coordenadorController.inserirCoordenador(c);
                        System.out.println("Coordenador cadastrado com sucesso!");
                    }
                    case 2 -> {
                        List<Coordenador> lista = coordenadorController.listarCoordenadores();
                        if (lista.isEmpty()) {
                            System.out.println(" Nenhum coordenador cadastrado.");
                        } else {
                            System.out.println("\n--- Lista de Coordenadores ---");
                            for (Coordenador c : lista) {
                                System.out.printf("ID: %d - Nome: %s | Dept: %s%n",
                                        c.getId(), c.getNome(), c.getDepartamento());
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do coordenador a atualizarCoordenador: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Coordenador c = coordenadorController.buscarPorId(id);
                        if (c != null) {
                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo departamento: ");
                            String dept = scanner.nextLine();

                            Coordenador novo = new Coordenador(id, nome, dept);
                            coordenadorController.atualizarCoordenador(id, novo);
                            System.out.println(" Coordenador atualizado.");
                        } else {
                            System.out.println(" Coordenador não encontrado.");
                        }
                    }
                    case 4 -> {
                        System.out.print("ID do coordenador a removerCoordenador: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Coordenador c = coordenadorController.buscarPorId(id);
                        if (c != null) {
                            List<AQV> atrasos = aqvController.listarPorCoordenador(id);
                            if (!atrasos.isEmpty()) {
                                System.out.println(" Este coordenador possui atrasos registrados. Remova os atrasos primeiro.");
                            } else {
                                coordenadorController.removerCoordenador(id);
                                System.out.println(" Coordenador removido com sucesso.");
                            }
                        } else {
                            System.out.println("️ Coordenador não encontrado.");
                        }
                    }
                    case 0 -> System.out.println("Saindo do menu Coordenador...");
                    default -> System.out.println(" Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(" Erro inesperado: " + e.getMessage());
                e.printStackTrace(); // Adicionado para ajudar no diagnóstico
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}