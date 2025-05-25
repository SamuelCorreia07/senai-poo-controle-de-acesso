package com.senai.view;

import com.senai.control.usuario.AQVController;
import com.senai.model.usuario.AQV;

import java.util.List;
import java.util.Scanner;

public class AqvView {
    public static void main(String[] args) {


            AQVController aqvController = new AQVController();
            Scanner scanner = new Scanner(System.in);

            int opcao;
            do {
                System.out.println("\n=== MENU AQV ===");
                System.out.println("1 - Registrar atraso");
                System.out.println("2 - Listar atrasos");
                System.out.println("3 - Atualizar atraso");
                System.out.println("4 - Remover atraso");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // limpa buffer

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome do aluno: ");
                        String nome = scanner.nextLine();

                        System.out.print("Matrícula do aluno: ");
                        String matricula = scanner.nextLine();

                        System.out.print("Motivo do atraso: ");
                        String motivo = scanner.nextLine();

                        System.out.print("Data do atraso (yyyy-mm-dd): ");
                        String data = scanner.nextLine();

                        AQV atraso = new AQV(0, nome, matricula, motivo, data, -1);
                        aqvController.adicionar(atraso);
                        System.out.println("Atraso registrado com sucesso.");
                    }
                    case 2 -> {
                        List<AQV> lista = aqvController.listar();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhum atraso registrado.");
                        } else {
                            for (AQV a : lista) {
                                System.out.printf("%d - Aluno: %s | Matrícula: %s | Motivo: %s | Data: %s%n",
                                        a.getId(), a.getNomeAluno(), a.getMatriculaAluno(), a.getMotivoAtraso(), a.getDataRegistro());
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do atraso a atualizar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        AQV existente = aqvController.buscarPorId(id);
                        if (existente == null) {
                            System.out.println("Atraso não encontrado.");
                            break;
                        }

                        System.out.print("Novo nome do aluno: ");
                        String nome = scanner.nextLine();

                        System.out.print("Nova matrícula do aluno: ");
                        String matricula = scanner.nextLine();

                        System.out.print("Novo motivo do atraso: ");
                        String motivo = scanner.nextLine();

                        System.out.print("Nova data do atraso (yyyy-mm-dd): ");
                        String data = scanner.nextLine();

                        AQV atualizado = new AQV(id,nome, matricula, motivo, data, -1);
                        aqvController.atualizar(id, atualizado);
                        System.out.println("Atraso atualizado com sucesso.");
                    }
                    case 4 -> {
                        System.out.print("ID do atraso a remover: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        aqvController.remover(id);
                        System.out.println("Atraso removido com sucesso.");
                    }
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }

            } while (opcao != 0);

            scanner.close();
        }
    }