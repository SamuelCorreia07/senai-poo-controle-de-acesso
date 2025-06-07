package com.senai.view;

import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.Professor;
import com.senai.model.usuario.AQV;
import com.senai.model.usuario.Usuario;
import com.senai.model.usuario.dao.json.AQVDAO;
import com.senai.util.CriptografiaUtil;
import com.senai.view.LoginView;
import com.senai.view.UsuarioView;
import com.senai.view.HorarioView;
import com.senai.view.usuario.aluno.JustificativaView;
import com.senai.websocket.WebSocketClienteConsole;

import java.util.Optional;
import java.util.Scanner;

import static com.senai.mqtt.MqttSubscriber.iniciarMqtt;
import static com.senai.websocket.WebSocketSender.iniciarWebSocket;

public class MenuPrincipal {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        iniciarMqtt();
        iniciarWebSocket();
        criarAqv();
        logar();
    }

    public static void logar() {
        Optional<Usuario> usuarioLogado = new LoginView().menuLoginView();
        usuarioLogado.ifPresent(MenuPrincipal::redirecionarMenu);
    }

    public static void criarAqv() {
        AQV aqv = new AQV(1, "Rafael", "aqv", CriptografiaUtil.hash("1234"));
        AQVDAO aqvDAO = new AQVDAO();
        aqvDAO.inserir(aqv);
    }

    private static void redirecionarMenu(Usuario usuario) {
        switch (usuario.getTipo()) {
            case "AQV" -> menuAqv((AQV) usuario);
            case "Professor" -> menuProfessor((Professor) usuario);
            case "Aluno" -> menuAluno((Aluno) usuario);
            default -> System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private static void menuAqv(AQV aqv) {
        UsuarioView usuarioView = new UsuarioView();
        System.out.printf("Bem vind@ %s \n", aqv.getNome());
        executarMenu("""
                
                _____________________________________________________________
                |   MENU AQV - Escolha uma opção:                           |
                |                                                           |
                |       1 - Gerenciar Usuários (Aluno/Professor)           |
                |       2 - Deslogar                                       |
                |       0 - Sair                                           |
                |___________________________________________________________|
                
                """, opcao -> {
            switch (opcao) {
                case "1" -> usuarioView.menu();
                case "2" -> logar();
                case "0" -> {
                    System.out.println("\nSaindo...");
                    System.exit(0);
                }
                default -> System.out.println("\nOpção inválida.");
            }
        });
    }

    private static void menuProfessor(Professor professor) {
        System.out.printf("Bem vind@ %s \n", professor.getNome());
        HorarioView horarioView = new HorarioView();
        executarMenu("""
                
                _____________________________________________________________
                |   MENU PROFESSOR - Escolha uma opção:                     |
                |                                                           |
                |       1 - Gerenciar Horários                              |
                |       2 - Receber notificações de atraso                  |
                |       3 - Deslogar                                        |
                |       0 - Sair                                            |
                |___________________________________________________________|
                
                """, opcao -> {
            switch (opcao) {
                case "1" -> horarioView.menuHorarioView();
                case "2" -> WebSocketClienteConsole.conectar();
                case "3" -> {
                    WebSocketClienteConsole.desconectar();
                    logar();
                }
                case "0" -> {
                    System.out.println("Saindo...");
                    WebSocketClienteConsole.desconectar();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    private static void menuAluno(Aluno aluno) {
        System.out.printf("Bem vind@ %s \n", aluno.getNome());
        HorarioView horarioView = new HorarioView();
        JustificativaView justificativaView = new JustificativaView(aluno);
        executarMenu("""
                
                _____________________________________________________________
                |   MENU ALUNO - Escolha uma opção:                         |
                |                                                           |
                |       1 - Visualizar Horários                             |
                |       2 - Gerenciar Justificativas                        |
                |       3 - Deslogar                                        |
                |       0 - Sair                                            |
                |___________________________________________________________|
                
                """, opcao -> {
            switch (opcao) {
                case "1" -> {
                    horarioView.listar();
                }
                case "2" -> {
                    justificativaView.listar();
                }
                case "3" -> logar();
                case "0" -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        });
    }

    private static void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
        String opcao;
        do {
            System.out.print(titulo);
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }
}