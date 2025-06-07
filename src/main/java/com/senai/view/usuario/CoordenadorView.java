package com.senai.view.usuario;

import com.senai.control.usuario.CoordenadorController;
import com.senai.model.usuario.Coordenador;

import java.util.List;
import java.util.Scanner;

public class CoordenadorView {

    private final Scanner scanner = new Scanner(System.in);
    private final CoordenadorController controller = new CoordenadorController();

    public static void main(String[] args) {
        CoordenadorView view = new CoordenadorView();
        view.menuCoordenadorView();
    }

    public void menuCoordenadorView() {
        String opcao;
        String menu = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar coordenador(a)                        |
                |       2 - Atualizar coordenador(a)                        |
                |       3 - Remover coordenador(a)                          |
                |       4 - Listar coordenador@s                            |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                
                """;
        do {
            System.out.print(menu);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrarCoordenador();
                case "2" -> atualizarCoordenador();
                case "3" -> removerCoordenador();
                case "4" -> listarCoordenadores();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarCoordenador() {
        String nome = scannerPromptString("\tNome do coordenador(a): ");
        String departamento = scannerPromptString("\tDepartamento: ");
        String resultado = controller.inserirCoordenador(new Coordenador(0, nome, departamento));
        System.out.println(resultado);
    }

    private void atualizarCoordenador() {
        int id = scannerPromptInt("\tID do coordenador(a): ", "\nPor favor, insira um ID válido.");
        String nome = scannerPromptString("\tNovo nome: ");
        String departamento = scannerPromptString("\tNovo departamento: ");
        Coordenador atualizado = new Coordenador(id, nome, departamento);
        String resultado = controller.atualizarCoordenador(id, atualizado);
        System.out.println(resultado);
    }

    private void removerCoordenador() {
        int id = scannerPromptInt("\tID do coordenador(a): ", "\nPor favor, insira um ID válido.");
        System.out.print("\nTem certeza que deseja remover o coordenador com ID '" + id + "'? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("S")) {
            String resultado = controller.removerCoordenador(id);
            System.out.println(resultado);
        } else {
            System.out.println("\nRemoção cancelada!");
        }
    }

    private void listarCoordenadores() {
        List<Coordenador> coordenadores = controller.listarCoordenadores();
        if (coordenadores.isEmpty()) {
            System.out.println("\nNenhum coordenador(a) encontrad@!");
        } else {
            for (Coordenador c : coordenadores) {
                System.out.println(c);
            }
        }
    }

    private String scannerPromptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private int scannerPromptInt(String msg, String erroMsg) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(msg);
                numero = Integer.parseInt(scanner.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println(erroMsg);
            }
        }
        return numero;
    }
}