package com.senai.view;

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
            System.out.println("5 - Registrar atraso de aluno");
            System.out.println("6 - Listar atrasos registrados");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Departamento: ");
                        String dept = scanner.nextLine();

                        Coordenador c = new Coordenador(0, nome, cpf, dept);
                        coordenadorController.adicionar(c);
                        System.out.println("Coordenador cadastrado com sucesso!");
                    }
                    case 2 -> {
                        List<Coordenador> lista = coordenadorController.listar();
                        if (lista.isEmpty()) {
                            System.out.println(" Nenhum coordenador cadastrado.");
                        } else {
                            System.out.println("\n--- Lista de Coordenadores ---");
                            for (Coordenador c : lista) {
                                System.out.printf("%d - %s | CPF: %s | Dept: %s%n",
                                        c.getId(), c.getNome(), c.getCpf(), c.getDepartamento());
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("ID do coordenador a atualizar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Coordenador c = coordenadorController.buscarPorId(id);
                        if (c != null) {
                            System.out.print("Novo nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Novo CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Novo departamento: ");
                            String dept = scanner.nextLine();

                            Coordenador novo = new Coordenador(id, nome, cpf, dept);
                            coordenadorController.atualizar(id, novo);
                            System.out.println(" Coordenador atualizado.");
                        } else {
                            System.out.println(" Coordenador não encontrado.");
                        }
                    }
                    case 4 -> {
                        System.out.print("ID do coordenador a remover: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Coordenador c = coordenadorController.buscarPorId(id);
                        if (c != null) {
                            List<AQV> atrasos = aqvController.listarPorCoordenador(id);
                            if (!atrasos.isEmpty()) {
                                System.out.println(" Este coordenador possui atrasos registrados. Remova os atrasos primeiro.");
                            } else {
                                coordenadorController.remover(id);
                                System.out.println(" Coordenador removido com sucesso.");
                            }
                        } else {
                            System.out.println("️ Coordenador não encontrado.");
                        }
                    }
                    case 5 -> {
                        System.out.print("ID do coordenador que está registrando o atraso: ");
                        int idCoord = scanner.nextInt();
                        scanner.nextLine();

                        Coordenador coord = coordenadorController.buscarPorId(idCoord);
                        if (coord == null) {
                            System.out.println("️ Coordenador não encontrado. Cadastre primeiro.");
                            break;
                        }

                        System.out.print("Nome do aluno: ");
                        String nomeAluno = scanner.nextLine();

                        System.out.print("Matrícula do aluno: ");
                        String matriculaAluno = scanner.nextLine();

                        System.out.print("Motivo do atraso: ");
                        String motivo = scanner.nextLine();

                        System.out.print("Data do atraso (yyyy-mm-dd): ");
                        String data = scanner.nextLine();

                        AQV atraso = new AQV(0, nomeAluno, matriculaAluno, motivo, data, idCoord);
                        aqvController.adicionar(atraso);

                        System.out.println(" Atraso registrado com sucesso!");
                    }
                    case 6 -> {
                        List<AQV> atrasos = aqvController.listar();
                        if (atrasos.isEmpty()) {
                            System.out.println(" Nenhum atraso registrado.");
                        } else {
                            System.out.println("\n--- Lista de Atrasos ---");
                            for (AQV a : atrasos) {
                                Coordenador c = coordenadorController.buscarPorId(a.getIdCoordenador());
                                String nomeCoord = (c != null) ? c.getNome() : "Coordenador não encontrado";
                                System.out.printf("%d - Aluno: %s | Matrícula: %s | Motivo: %s | Data: %s | Registrado por: %s (ID: %d)%n",
                                        a.getId(), a.getNomeAluno(), a.getMatriculaAluno(),
                                        a.getMotivoAtraso(), a.getDataRegistro(), nomeCoord, a.getIdCoordenador());
                            }
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