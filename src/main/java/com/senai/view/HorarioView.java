package com.senai.view;

import com.senai.control.HorarioController;
import com.senai.model.turma.horario.Horario;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class HorarioView {
    private final Scanner scanner = new Scanner(System.in);
    private final HorarioController controller = new HorarioController();

    public static void main(String[] args) {
        HorarioView view = new HorarioView();
        view.menuHorarioView();
    }

    public void menuHorarioView() {
        String opcao;
        String menu = """
                
                _____________________________________________________________
                |   Escolha uma opção:                                      |
                |                                                           |
                |       1 - Cadastrar horário                               |
                |       2 - Atualizar horário                               |
                |       3 - Remover horário                                 |
                |       4 - Listar horários                                 |
                |       0 - Voltar                                          |
                |___________________________________________________________|
                
                """;
        do {
            System.out.print(menu);
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> cadastrarHorario();
                case "2" -> atualizarHorario();
                case "3" -> removerHorario();
                case "4" -> listarHorarios();
                case "0" -> System.out.println("\nVoltando ao menu principal...");
                default -> System.out.println("\nOpção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void cadastrarHorario() {
        int idAluno = scannerPromptInt("\tID do aluno: ", "\nPor favor, insira um ID válido.");
        int idProfessor = scannerPromptInt("\tID do professor: ", "\nPor favor, insira um ID válido.");
        LocalTime hora = scannerPromptHora("\tHora de início (HH:mm): ");

        String resultado = controller.cadastrarHorario(idAluno, idProfessor, hora);
        System.out.println(resultado);  // Exibe o resultado
    }

    private void atualizarHorario() {
        int id = scannerPromptInt("\tID do horário: ", "\nPor favor, insira um ID válido.");
        int idAluno = scannerPromptInt("\tNovo ID do aluno: ", "\nPor favor, insira um ID válido.");
        int idProfessor = scannerPromptInt("\tNovo ID do professor: ", "\nPor favor, insira um ID válido.");
        LocalTime hora = scannerPromptHora("\tNova hora de início (HH:mm): ");

        String resultado = controller.atualizarHorario(id, idAluno, idProfessor, hora);
        System.out.println(resultado);
    }

    private void removerHorario() {
        int id = scannerPromptInt("\tID do horário: ", "\nPor favor, insira um ID válido.");
        System.out.print("\nTem certeza que deseja remover o horário com ID '" + id + "'? (S/N): ");

        String confirmacao = scanner.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            String resultado = controller.removerHorario(id);
            System.out.println(resultado);
        } else {
            System.out.println("\nRemoção cancelada!");
        }
    }

    private void listarHorarios() {
        List<Horario> horarios = controller.listarHorarios();
        if (horarios.isEmpty()) {
            System.out.println("\nNenhum horário cadastrad@!");
        } else {
            for (Horario h : horarios) {
                System.out.printf("ID: %d | Aluno ID: %d | Professor ID: %d | Início: %s%n",
                        h.getId(), h.getIdAluno(), h.getIdProfessor(), h.getHoraInicio());
            }
        }
    }

    public void listar() {
        System.out.println("\n--- HORÁRIOS ---");
        for (Horario h : controller.listarHorarios()) {
            System.out.printf("""
            ID do Aluno: %d
            ID do Professor: %d
            Hora de Início: %s
            
            """, h.getIdAluno(), h.getIdProfessor(), h.getHoraInicioFormatada());
        }
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

    private LocalTime scannerPromptHora(String msg) {
        LocalTime hora = null;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(msg);
                hora = LocalTime.parse(scanner.nextLine().trim());
                valido = true;
            } catch (DateTimeParseException e) {
                System.out.println("\nFormato inválido. Use o padrão HH:mm (ex: 14:30).");
            }
        }
        return hora;
    }
}