package com.senai.view;

import com.senai.model.usuario.*;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.dao.json.AQVDAO;
import com.senai.util.CriptografiaUtil;
import com.senai.view.curso.AmbienteView;
import com.senai.view.curso.CursoView;
import com.senai.view.turma.TurmaView;
import com.senai.view.usuario.aluno.JustificativaView;
import com.senai.websocket.WebSocketClienteConsole;

import java.util.Optional;
import java.util.Scanner;

import static com.senai.mqtt.MqttSubscriber.iniciarMqtt;
import static com.senai.websocket.WebSocketSender.iniciarWebSocket;

public class MenuPrincipal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioView usuarioView = new UsuarioView();
    private static final HorarioView horarioView = new HorarioView();

    public static void main(String[] args) throws Exception {
        iniciarMqtt();
        iniciarWebSocket();
        logar();
    }

    public static void logar() {
        Optional<Usuario> usuarioLogado = new LoginView().menuLoginView();
        usuarioLogado.ifPresent(MenuPrincipal::redirecionarMenu);
    }

    private static void redirecionarMenu(Usuario usuario) {
        switch (usuario.getTipo()) {
            case "AQV" -> menuAQV((AQV) usuario);
            case "Professor" -> menuProfessor((Professor) usuario);
            case "Coordenador" -> menuCoordenador((Coordenador) usuario);
            case "Aluno" -> menuAluno((Aluno) usuario);
            case "Administrador" -> menuAdminstrador((Administrador) usuario);
            default -> System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private static void menuAdminstrador(Administrador administrador) {

        System.out.printf("Bem vind@ %s \n", administrador.getNome());
        executarMenu("""
                
                _____________________________________________________________
                |   MENU ADMINISTRADOR - Escolha uma opção:                 |
                |                                                           |
                |       1 - Gerenciar Usuários                              |
                |       2 - Deslogar                                        |
                |       0 - Sair                                            |
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
        executarMenu("""
                
                _____________________________________________________________
                |   MENU PROFESSOR - Escolha uma opção:                     |
                |                                                           |
                |       1 - Receber notificações de atraso                  |
                |       2 - Deslogar                                        |
                |       0 - Sair                                            |
                |___________________________________________________________|
                
                """, opcao -> {
            switch (opcao) {
                case "1" -> WebSocketClienteConsole.conectar();
                case "2" -> {
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

    private static void menuAQV(AQV aqv) {
        System.out.printf("Bem vind@ %s \n", aqv.getNome());

        executarMenu("""
                
                _____________________________________________________________
                |   MENU AQV - Escolha uma opção:                           |
                |                                                           |
                |       1 - Gerenciar Horários                              |
                |       2 - Receber notificações de atraso                  |
                |       3 - Autorizar ocorrência                            |
                |       4 - Deslogar                                        |
                |       0 - Sair                                            |
                |___________________________________________________________|
                
                """, opcao -> {
            switch (opcao) {
                case "1" -> horarioView.menuHorarioView();
                case "2" -> WebSocketClienteConsole.conectar();
//                case "3" ->
                case "4" -> {
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


    private static void menuCoordenador(Coordenador coordenador) {
        TurmaView turmaView = new TurmaView();
        CursoView cursoView = new CursoView();
        AmbienteView ambienteView = new AmbienteView();

        System.out.printf("Bem vind@ %s \n", coordenador.getNome());

        executarMenu("""
                
                _____________________________________________________________
                |   MENU COORDENADOR - Escolha uma opção:                   |
                |                                                           |
                |       1 - Gerenciar Horários                              |
                |       2 - Receber notificações de atraso                  |
                |       3 - Gerenciar Usuários                              |
                |       4 - Gerenciar Turmas                                |
                |       5 - Gerenciar Cursos                                |
                |       6 - Gerenciar Ambientes                             |
                |       7 - Deslogar                                        |
                |       0 - Sair                                            |
                |___________________________________________________________|
                
                """, opcao -> {
            switch (opcao) {
                case "1" -> horarioView.menuHorarioView();
                case "2" -> WebSocketClienteConsole.conectar();
                case "3" -> usuarioView.menu();
                case "4" -> turmaView.menuTurmaView();
                case "5" -> cursoView.menuCursoView();
                case "6" -> ambienteView.menuAmbiente();
                case "7" -> {
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

    private static void executarMenu(String titulo, java.util.function.Consumer<String> acoes) {
        String opcao;
        do {
            System.out.print(titulo);
            opcao = scanner.nextLine();
            acoes.accept(opcao);
        } while (!opcao.equals("0"));
    }
}