package com.ecochallenge;

import com.ecochallenge.managers.GerenciadorDados;
import com.ecochallenge.models.Desafio;
import com.ecochallenge.models.RegistroDesafio;
import com.ecochallenge.models.Usuario;
import java.util.List;
import java.util.Scanner;

public class EcoChallengeTerminal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorDados gerenciador = GerenciadorDados.getInstance();
    private static Usuario usuarioAtual;

    public static void main(String[] args) {
        System.out.println("=== EcoChallenge Terminal v1.0 ===");
        System.out.println("Sistema de Desafios Sustentáveis");
        System.out.println("==================================");

        carregarUsuario();
        executarMenuPrincipal();
    }

    private static void carregarUsuario() {
        usuarioAtual = gerenciador.carregarUsuario();
        if (usuarioAtual == null) {
            System.out.println("\nNenhum usuário encontrado. Vamos criar seu perfil!");
            criarNovoUsuario();
        } else {
            System.out.println("\nBem-vindo de volta, " + usuarioAtual.obterNomeCompleto() + "!");
            exibirEstatisticasUsuario();
        }
    }

    private static void criarNovoUsuario() {
        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Digite sua idade: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();

        usuarioAtual = new Usuario(nome, idade, email);
        gerenciador.salvarUsuario(usuarioAtual);

        System.out.println("\nUsuário criado com sucesso!");
        exibirEstatisticasUsuario();
    }

    private static void exibirEstatisticasUsuario() {
        System.out.println("\n--- Suas Estatísticas ---");
        System.out.println(usuarioAtual.toString());
        System.out.println("Desafios Completos: " + gerenciador.obterTotalDesafiosCompletos(usuarioAtual));
        System.out.println("------------------------");
    }

    private static void executarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Ver Desafios Disponíveis");
            System.out.println("2. Completar Desafio");
            System.out.println("3. Ver Histórico");
            System.out.println("4. Ver Perfil");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    exibirDesafiosDisponiveis();
                    break;
                case "2":
                    completarDesafio();
                    break;
                case "3":
                    exibirHistorico();
                    break;
                case "4":
                    exibirPerfil();
                    break;
                case "5":
                    System.out.println("Obrigado por usar o EcoChallenge! Até logo!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirDesafiosDisponiveis() {
        System.out.println("\n=== DESAFIOS SUSTENTÁVEIS ===");
        List<Desafio> desafios = gerenciador.obterDesafiosDisponiveis();

        for (int i = 0; i < desafios.size(); i++) {
            System.out.println("\n" + (i + 1) + ". " + desafios.get(i));
            System.out.println("   " + obterIconeCategoria(desafios.get(i).obterCategoria()));
        }
        System.out.println("\nPressione ENTER para voltar...");
        scanner.nextLine();
    }

    private static void completarDesafio() {
        System.out.println("\n=== COMPLETAR DESAFIO ===");
        List<Desafio> desafios = gerenciador.obterDesafiosDisponiveis();

        for (int i = 0; i < desafios.size(); i++) {
            System.out.println((i + 1) + ". " + desafios.get(i).obterTitulo() +
                             " (" + desafios.get(i).obterPontosRecompensa() + " pontos)");
        }

        System.out.print("\nEscolha o número do desafio que você completou (0 para voltar): ");
        String input = scanner.nextLine();

        try {
            int escolha = Integer.parseInt(input);
            if (escolha == 0) return;

            if (escolha >= 1 && escolha <= desafios.size()) {
                Desafio desafioEscolhido = desafios.get(escolha - 1);

                System.out.println("\nVocê selecionou: " + desafioEscolhido.obterTitulo());
                System.out.println("Descrição: " + desafioEscolhido.obterDescricao());
                System.out.print("Confirma que completou este desafio? (s/n): ");

                String confirmacao = scanner.nextLine().toLowerCase();
                if (confirmacao.equals("s") || confirmacao.equals("sim")) {
                    gerenciador.completarDesafio(desafioEscolhido, usuarioAtual);
                    usuarioAtual = gerenciador.carregarUsuario();

                    System.out.println("\n🎉 Parabéns! Desafio completado!");
                    System.out.println("Você ganhou " + desafioEscolhido.obterPontosRecompensa() + " pontos!");
                    System.out.println("Dica ecológica: " + desafioEscolhido.obterDicaEcologica());
                    exibirEstatisticasUsuario();
                }
            } else {
                System.out.println("Número inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
        }
    }

    private static void exibirHistorico() {
        System.out.println("\n=== HISTÓRICO DE DESAFIOS ===");
        List<RegistroDesafio> historico = gerenciador.carregarHistorico(usuarioAtual);

        if (historico.isEmpty()) {
            System.out.println("Nenhum desafio completado ainda.");
            System.out.println("Que tal completar seu primeiro desafio?");
        } else {
            System.out.println("Total de desafios completados: " + historico.size());
            System.out.println("\nDetalhes:");
            for (RegistroDesafio registro : historico) {
                System.out.println("• " + registro);
            }
        }

        System.out.println("\nPressione ENTER para voltar...");
        scanner.nextLine();
    }

    private static void exibirPerfil() {
        System.out.println("\n=== SEU PERFIL ===");
        exibirEstatisticasUsuario();

        System.out.println("\nOpções:");
        System.out.println("1. Atualizar dados");
        System.out.println("2. Voltar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();
        if (opcao.equals("1")) {
            atualizarPerfil();
        }
    }

    private static void atualizarPerfil() {
        System.out.println("\n=== ATUALIZAR PERFIL ===");
        System.out.println("Dados atuais: " + usuarioAtual.toString());

        System.out.print("Novo nome (ou ENTER para manter atual): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            usuarioAtual.definirNomeCompleto(novoNome);
        }

        System.out.print("Nova idade (ou ENTER para manter atual): ");
        String novaIdade = scanner.nextLine();
        if (!novaIdade.trim().isEmpty()) {
            try {
                usuarioAtual.definirIdade(Integer.parseInt(novaIdade));
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida, mantendo valor atual.");
            }
        }

        System.out.print("Novo e-mail (ou ENTER para manter atual): ");
        String novoEmail = scanner.nextLine();
        if (!novoEmail.trim().isEmpty()) {
            usuarioAtual.definirEmail(novoEmail);
        }

        gerenciador.salvarUsuario(usuarioAtual);
        System.out.println("Perfil atualizado com sucesso!");
    }

    private static String obterIconeCategoria(String categoria) {
        switch (categoria.toLowerCase()) {
            case "água": return "💧 Categoria: Conservação de Água";
            case "transporte": return "🚲 Categoria: Transporte Sustentável";
            case "energia": return "⚡ Categoria: Energia Limpa";
            case "reciclagem": return "♻️ Categoria: Reciclagem";
            case "consumo": return "🛒 Categoria: Consumo Consciente";
            case "resíduos": return "🗑️ Categoria: Gestão de Resíduos";
            default: return "🌱 Categoria: Sustentabilidade Geral";
        }
    }
}
