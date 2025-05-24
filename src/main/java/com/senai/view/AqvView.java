package com.senai.view;

import com.senai.control.usuario.AQVController;
import com.senai.model.usuario.AQV;

import java.util.List;
import java.util.Scanner;

public class AqvView {
    public static void main(String[] args) {
        private Scanner scanner = new Scanner(System.in);
        private AQVController controller = new AQVController();

        public void menu() {
            int opcao;
            do {
                System.out.println("\n--- MENU AQV ---");
                System.out.println("1. Registrar Acesso");
                System.out.println("2. Listar Acessos");
                System.out.println("3. Atualizar Acesso");
                System.out.println("4. Remover Acesso");
                System.out.println("0. Voltar");
                System.out.print("Opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> atualizar();
                    case 4 -> remover();
                }
            } while (opcao != 0);
        }

        private void registrar() {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome da Pessoa: ");
            String nome = scanner.nextLine();
            System.out.print("Setor: ");
            String setor = scanner.nextLine();
            System.out.print("Data e Hora (ex: 2025-05-21 08:30): ");
            String dataHora = scanner.nextLine();

            AQV novo = new AQV(id, nome, setor, dataHora);
            controller.adicionarAQV(novo);
        }

        private void listar() {
            List<AQV> lista = controller.listarTodos();
            for (AQV a : lista) {
                System.out.printf("ID: %d | Nome: %s | Setor: %s | Data/Hora: %s\n",
                        a.getId(), a.getNomePessoa(), a.getSetor(), a.getDataHoraAcesso());
            }
        }

        private void atualizar() {
            System.out.print("ID do acesso a atualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Setor: ");
            String setor = scanner.nextLine();
            System.out.print("Nova Data e Hora: ");
            String dataHora = scanner.nextLine();

            AQV atualizado = new AQV(id, nome, setor, dataHora);
            controller.atualizarAQV(atualizado);
        }

        private void remover() {
            System.out.print("ID do acesso a remover: ");
            int id = scanner.nextInt();
            controller.removerAQV(id);
        }
    }
