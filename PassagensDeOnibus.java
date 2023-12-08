package da_melissa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import da_melissaDAO.ViagemDAO;

public class PassagensDeOnibus {

    public static void main(String[] args) throws IOException {

        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Scanner entrada = new Scanner(System.in);
        List<Viagem> viagens = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        MapaAssentos mapaAssentos = new MapaAssentos(new byte[24], new byte[24]);
        Administrador administrador = new Administrador(); // Supondo que 'transacoes' seja a lista de transações
        Vendedor vendedor = new Vendedor();
        
        do {
            exibirMenuLogin();
            int opcaoLogin = entrada.nextInt();
            entrada.nextLine(); // Limpar o buffer

            switch (opcaoLogin) {

                case 1:
                    // Login como Atendente
                    menuAtendente(entrada, vendas, viagens, mapaAssentos, vendedor);
                    break;
                case 2:
                    // Login como Administrador
                    menuAdministrador(entrada, vendas, viagens, mapaAssentos, administrador);
                    break;
                case 3:
                    System.out.println("Saindo do sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private static void menuAtendente(Scanner entrada, List<Venda> vendas, List<Viagem> viagens, MapaAssentos mapaAssentos, Vendedor vendedor) {
        do {
            exibirMenuAtendente();
            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                	consultarVendas(vendas, entrada);
                    break;
                    
                case 2:
                	consultarViagens(viagens);
                    break;
                    
                case 3:
                	cadastrarVenda(vendas, entrada);
                    break;
                case 4:
                	exibirMapaAssentos(mapaAssentos);
                    break;
                case 5:
                    System.out.println("\nSaindo do sistema.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }
    private static void exibirMenuAtendente() {
        System.out.println("\n======= Menu Atendente =======");
        System.out.println("[1] - Consultar Vendas");
        System.out.println("[2] - Consultar Viagens");
        System.out.println("[3] - Criar Vendas");
        System.out.println("[4] - Mapa dos assentos");
        System.out.println("[5] - Sair do Sistema");
        System.out.print("O que deseja fazer? : ");
    }
    
    private static void menuAdministrador(Scanner entrada, List<Venda> vendas, List<Viagem> viagens, MapaAssentos mapaAssentos, Administrador administrador) {
        do {
            exibirMenuAdministrador();
            int opcao = entrada.nextInt();
            entrada.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarViagem(viagens, entrada, administrador);
                    break;
                case 2:
                    exibirMapaAssentos(mapaAssentos);
                    break;
                case 3:
                	consultarVendas(vendas, entrada);
                    break;
                case 4:
                	consultarViagens(viagens);
                    break;
                case 5:
                	System.out.println("\nVoltando ao menu principal.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private static void consultarVendas(List<Venda> vendas, Scanner entrada) {
        // Exibir a lista de viagens disponíveis para o atendente escolher
        System.out.println("\nVendas:");
        for (int i = 0; i < vendas.size(); i++) {
            Venda vendinha = vendas.get(i);
            System.out.println("[" + (i + 1) + "] " + vendas.toString());
        }

        // Solicitar ao atendente que escolha uma viagem
        System.out.print("\nEscolha o número da viagem para consultar as vendas: ");
        int escolhaVendas = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer

        // Verificar se a escolha é válida
        if (escolhaVendas >= 1 && escolhaVendas <= vendas.size()) {
            // A viagem escolhida pelo atendente
        	Venda vendasEscolhida = vendas.get(escolhaVendas - 1);

            // Exibir as vendas para a viagem escolhida
            System.out.println("\nVendas para a Viagem " + vendasEscolhida.getIdVenda() + ":");
            List<Venda> vendas1 = vendasEscolhida.getVendas();
            for (Venda venda : vendas1) {
                System.out.println(venda.toString());
            }
        } else {
            System.out.println("\nEscolha inválida. Tente novamente.");
        }
    }

    private static void consultarVendasOuViagens(Scanner entrada, List<Viagem> viagens, MapaAssentos mapaAssentos) {
        System.out.println("\n======= Consulta de Vendas ou Viagens =======");
        System.out.println("[1] - Consultar Vendas");
        System.out.println("[2] - Consultar Viagens");
        System.out.println("[3] - Consultar Vendas");
        System.out.println("[4] - Consultar Viagens");
        System.out.println("[5] - Sair do Sistema");
        System.out.print("O que deseja consultar? : ");

        int opcaoConsulta = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer

        switch (opcaoConsulta) {
            case 1:
                // Lógica para consultar vendas
                // (Você pode chamar métodos ou fazer consultas aqui)
                System.out.println("Consulta de Vendas");
                break;
            case 2:
                // Lógica para consultar viagens
                // (Você pode chamar métodos ou fazer consultas aqui)
                System.out.println("Consulta de Viagens");
                break;
            case 3:
                // Voltar ao Menu Atendente
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void consultarViagens(List<Viagem> viagens) {
        System.out.println("\n======= Consulta de Viagens =======");
        
        if (viagens.isEmpty()) {
            System.out.println("Não há viagens cadastradas.");
        } else {
            for (Viagem viagem : viagens) {
                System.out.println("ID: " + viagem.getId_viagem());
                System.out.println("Origem: " + viagem.getOrigem());
                System.out.println("Destino: " + viagem.getDestino());
                System.out.println("Horário: " + viagem.getHorario());
                System.out.println("Preço: " + viagem.getPreco());
                System.out.println("Assentos Disponíveis: " + viagem.getAssentosDisponiveis());
                System.out.println("Total de Assentos: " + viagem.getTotalAssentos());
                System.out.println("------------------------------");
            }
        }
    }



   

    private static void exibirMapaAssentos(MapaAssentos mapaAssentos) {
        mapaAssentos.exibirMapaAssentos();
    }

    private static void cadastrarViagem(List<Viagem> viagens, Scanner entrada, Administrador administrador) {
        // Verificar se o usuário é um administrador
        if (administrador != null) {
            System.out.print("\nQual a origem da viagem? : ");
            String origem = entrada.nextLine();
            System.out.print("\nQual o destino da viagem? : ");
            String destino = entrada.nextLine();
            System.out.print("\nQual o horário da viagem? : ");
            String horario = entrada.nextLine();  // Agora, horário é uma String
            System.out.print("\nQual o preço da viagem? : ");
            double preco = entrada.nextDouble();
            System.out.print("\nQuantos assentos disponíveis? : ");
            int assentosDisponiveis = entrada.nextInt();

            // Agora, crie a instância de Viagem
            Viagem viagem = new Viagem(origem, destino, horario, preco, assentosDisponiveis, administrador.getId_administrador());
            viagens.add(viagem);

            // Agora, você pode chamar o método inserirViagem no DAO
            ViagemDAO viagemDAO = new ViagemDAO();
            viagemDAO.inserirViagem(viagem);

            System.out.println("\nViagem cadastrada com sucesso!");
        } else {
            System.out.println("\nApenas administradores podem cadastrar viagens.");
        }
    }
    
    private static void cadastrarVenda(List<Venda> vendas, Scanner entrada) {
        // Exibir a lista de viagens disponíveis para o atendente escolher
        System.out.println("\nViagens Disponíveis:");
        for (int i = 0; i < vendas.size(); i++) {
            Venda viagem = vendas.get(i);
            System.out.println("[" + (i + 1) + "] " + viagem.toString());
        }

        // Solicitar ao atendente que escolha uma viagem
        System.out.print("\nEscolha o número da viagem para cadastrar a venda: ");
        int escolhaViagem = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer

        // Verificar se a escolha é válida
        if (escolhaViagem >= 1 && escolhaViagem <= vendas.size()) {
            // A viagem escolhida pelo atendente
            Venda vendaEscolhida = vendas.get(escolhaViagem - 1); // Corrigir o nome da variável

            // Solicitar informações do cliente
            System.out.print("\nNome do Cliente: ");
            String nomeCliente = entrada.nextLine();
            System.out.print("Tipo de Assento (convencional, semi-leito, leito): ");
            int tipoAssento = entrada.nextInt();
            System.out.print("Valor: ");
            double valor = entrada.nextDouble(); // Alterado para double

            // Criar a venda
            vendaEscolhida.realizarVenda(nomeCliente, valor, tipoAssento); // Atualizado para passar um double
        } else {
            System.out.println("\nEscolha inválida. Tente novamente.");
        }
    }






    private static void exibirMenuAdministrador() {
        System.out.println("\n======= Menu Administrador =======");
        System.out.println("[1] - Cadastrar Viagem");
        System.out.println("[2] - Exibir Mapa de Assentos");
        System.out.println("[3] - Outras Opções de Administração");
        System.out.println("[4] - Voltar ao Menu Principal");
        System.out.println("[5] - Sair do Sistema");
        System.out.print("O que deseja fazer, Administrador? : ");
    }

    private static void exibirMenuLogin() {
        System.out.println("\n======= Sistema de Passagens de Ônibus =======");
        System.out.println("[1] - Login como Atendente");
        System.out.println("[2] - Login como Administrador");
        System.out.println("[3] - Sair");
        System.out.print("Escolha uma opção: ");
    }


}