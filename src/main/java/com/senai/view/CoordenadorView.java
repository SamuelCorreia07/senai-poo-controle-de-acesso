package com.senai.view;


import com.senai.control.usuario.AQVController;
import com.senai.control.usuario.CoordenadorController;
import com.senai.model.usuario.AQV;
import com.senai.model.usuario.Coordenador;

import java.util.List;
import java.util.Scanner;

public class CoordenadorView {
    public static void main(String[] args) {

    }
    private final CoordenadorController coordenadorController;
    private final AQVController aqvController;
    private final Scanner scanner;

    public CoordenadorView(CoordenadorController coordenadorController, AQVController aqvController) {
        this.coordenadorController = coordenadorController;
        this.aqvController = aqvController;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- Menu Coordenador ---");
            System.out.println("1 - Cadastrar coordenador");
            System.out.println("2 - Listar coordenadores");
            System.out.println("3 - Atualizar coordenador");
            System.out.println("4 - Remover coordenador");
            System.out.println("5 - Registrar atraso de aluno");
            System.out.println("6 - Listar atrasos registrados");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> cadastrarCoordenador();
                case 2 -> listarCoordenadores();
                case 3 -> atualizarCoordenador();
                case 4 -> removerCoordenador();
                case 5 -> registrarAtraso();
                case 6 -> listarAtrasos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void cadastrarCoordenador() {
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

    private void listarCoordenadores() {
        List<Coordenador> lista = coordenadorController.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum coordenador cadastrado.");
        } else {
            for (Coordenador c : lista) {
                System.out.printf("%d - %s | CPF: %s | Dept: %s%n", c.getId(), c.getNome(), c.getCpf(), c.getDepartamento());
            }
        }
    }

    private void atualizarCoordenador() {
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
            System.out.println("Coordenador atualizado.");
        } else {
            System.out.println("Coordenador não encontrado.");
        }
    }

    private void removerCoordenador() {
        System.out.print("ID do coordenador a remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        coordenadorController.remover(id);
        System.out.println("Coordenador removido.");
    }

    private void registrarAtraso() {
        System.out.print("ID do coordenador que está registrando o atraso: ");
        int idCoord = scanner.nextInt();
        scanner.nextLine();

        Coordenador coord = coordenadorController.buscarPorId(idCoord);
        if (coord == null) {
            System.out.println("Coordenador não encontrado. Cadastre primeiro.");
            return;
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

        System.out.println("Atraso registrado com sucesso!");
    }

    private void listarAtrasos() {
        List<AQV> atrasos = aqvController.listar();
        if (atrasos.isEmpty()) {
            System.out.println("Nenhum atraso registrado.");
        } else {
            for (AQV a : atrasos) {
                Coordenador c = coordenadorController.buscarPorId(a.getIdCoordenador());
                String nomeCoord = (c != null) ? c.getNome() : "Desconhecido";
                System.out.printf("%d - Aluno: %s | Matrícula: %s | Motivo: %s | Data: %s | Registrado por: %s%n",
                        a.getId(), a.getNomeAluno(), a.getMatriculaAluno(), a.getMotivoAtraso(), a.getDataRegistro(), nomeCoord);
            }
        }
    }
}