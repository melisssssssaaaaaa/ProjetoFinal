package da_melissa;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Administrador {

    private int id_administrador;
    private String nome_administrador;
    private List<Venda> vendas;
    private List<Viagem> viagens;

    public Administrador() {
        this.vendas = new ArrayList<>();
        this.viagens = new ArrayList<>();
    }

    // Outros métodos e atributos da classe

    public void criarViagem(String origem, String destino, String horario, double preco, int totalAssentos) {
        Viagem viagem = Viagem.criarViagem(origem, destino, horario, preco, totalAssentos);
        viagens.add(viagem);
    }

    public void realizarVenda(Viagem viagem, String nomeCliente, String metodoPagamento, String tipoAssento) {
        if (viagem.haAssentosDisponiveis()) {
            double valor = viagem.calcularValorPassagem(tipoAssento);
            viagem.realizarVenda(nomeCliente, metodoPagamento, tipoAssento);
            vendas.add(new Venda());
            System.out.println("Venda realizada para " + nomeCliente + " - Assento: " + viagem.getAssentosDisponiveis());
        } else {
            System.out.println("Não há assentos disponíveis para esta viagem.");
        }
    }

    public void exibirInformacoesViagem(Viagem viagem) {
        System.out.println("Informações da Viagem:");
        System.out.println("Origem: " + viagem.getOrigem());
        System.out.println("Destino: " + viagem.getDestino());
        System.out.println("Horário: " + viagem.getHorario());
        System.out.println("Preço: " + viagem.getPreco());
        System.out.println("Assentos Disponíveis: " + viagem.getAssentosDisponiveis());
    }

    public void gerarRelatorioVendas(Viagem viagem) {
        List<Venda> vendasDaViagem = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.getViagem().equals(viagem)) {
                vendasDaViagem.add(venda);
            }
        }

        System.out.println("\nRelatório de Vendas da Viagem:");
        for (Venda venda : vendasDaViagem) {
            System.out.println("Cliente: " + venda.getNomeCliente() +
                    ", Método de Pagamento: " + venda.getMetodoPagamento() +
                    ", Tipo de Assento: " + venda.getTipoAssento() +
                    ", Valor: " + venda.getValor());
        }
    }

    
 // Outros métodos e getters/setters
	public int getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(int id_administrador) {
		this.id_administrador = id_administrador;
	}

	public String getNome_administrador() {
		return nome_administrador;
	}

	public void setNome_administrador(String nome_administrador) {
		this.nome_administrador = nome_administrador;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Administrador [id_administrador=");
		builder.append(id_administrador);
		builder.append(", nome_administrador=");
		builder.append(nome_administrador);
		builder.append(", vendas=");
		builder.append(vendas);
		builder.append(", viagens=");
		builder.append(viagens);
		builder.append("]");
		return builder.toString();
	}

    
    
}
