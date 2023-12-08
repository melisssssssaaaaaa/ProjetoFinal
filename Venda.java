package da_melissa;

import java.util.List;

public class Venda {
	private int idVenda;
    private int idTransacaoVenda;
    private Viagem viagem;
    private String nomeCliente;
    private double valor;
    private String metodoPagamento;
    private boolean pagamentoConfirmado;
    private String dataHora;
	private String tipoAssento;
	private int numeroAssento;
	private String dataHoraVenda;

    
	 // Construtores, getters e setters
	public Venda() {

	}
	
    public Venda(Viagem viagem, String nomeCliente, int numeroAssento2, double valor) {
        this.viagem = viagem;
        this.nomeCliente = nomeCliente;
        this.numeroAssento = numeroAssento2;
        this.valor = valor;
    }
    
    public Venda(String nomeCliente, String metodoPagamento, String tipoAssento, double valor, String dataHora, Viagem viagem) {
        this.nomeCliente = nomeCliente;
        this.metodoPagamento = metodoPagamento;
        this.tipoAssento = tipoAssento;
        this.valor = valor;
        this.dataHora = dataHora;
        this.viagem = viagem;
    }

    // Construtor para criar uma nova venda
    public Venda(int idTransacaoVenda, String nomeCliente, double valor, String metodoPagamento, boolean pagamentoConfirmado, String dataHora) {
        this.idTransacaoVenda = idTransacaoVenda;
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.pagamentoConfirmado = pagamentoConfirmado;
        this.dataHora = dataHora;
    }

    // Construtor para criar uma venda com ID (útil ao recuperar vendas do banco de dados)
    public Venda(int idVenda, int idTransacaoVenda, String nomeCliente, double valor, String metodoPagamento, boolean pagamentoConfirmado, String dataHora) {
        this.idVenda = idVenda;
        this.idTransacaoVenda = idTransacaoVenda;
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.pagamentoConfirmado = pagamentoConfirmado;
        this.dataHora = dataHora;
    }
    
    public Venda(String nomeCliente, int numeroAssento, double valor, String dataHoraVenda, Viagem viagem) {
        this.nomeCliente = nomeCliente;
        this.numeroAssento = numeroAssento;
        this.valor = valor;
        this.dataHoraVenda = dataHoraVenda;
        this.viagem = viagem;
    }
    

    public void realizarVenda(String nomeCliente, double valor, int tipoAssento) {
        // Atualiza o estado do assento na viagem
        viagem.marcarAssentoComoVendido(numeroAssento);

        // Adiciona a venda ao histórico da viagem
        viagem.adicionarVenda(this);

        // Exibe informações da venda (isso pode variar conforme sua implementação)
        System.out.println("Venda realizada com sucesso!");
        System.out.println("Viagem: " + viagem);
        System.out.println("Assento: " + numeroAssento);
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Valor: " + valor);
        System.out.println("Data e Hora da Venda: " + dataHora);
    }


    
  //getters e setters
    
	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdTransacaoVenda() {
		return idTransacaoVenda;
	}

	public void setIdTransacaoVenda(int idTransacaoVenda) {
		this.idTransacaoVenda = idTransacaoVenda;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public boolean isPagamentoConfirmado() {
		return pagamentoConfirmado;
	}

	public void setPagamentoConfirmado(boolean pagamentoConfirmado) {
		this.pagamentoConfirmado = pagamentoConfirmado;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getTipoAssento() {
		return tipoAssento;
	}

	public void setTipoAssento(String tipoAssento) {
		this.tipoAssento = tipoAssento;
	}

	public int getNumeroAssento() {
		return numeroAssento;
	}

	public void setNumeroAssento(int numeroAssento) {
		this.numeroAssento = numeroAssento;
	}

	public Venda get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Venda> getVendas() {
		// TODO Auto-generated method stub
		return null;
	}
    
  
	

}

