package com.senai.view;


import java.util.Scanner;

public class CoordenadorView {
    private Scanner scanner = new Scanner(System.in);
    private CoordenadorView coordenadorView = new CoordenadorView();
    private AqvView aqvView = new AqvView();

    public static void main(String[] args) {
        new CoordenadorView().menuPrincipal();
    }

    public void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE GESTÃO ===");
            System.out.println("1. Menu Coordenador");
            System.out.println("2. Menu AQV");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> coordenadorView.menuPrincipal();
                case 2 -> aqvView.menu();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }
}



