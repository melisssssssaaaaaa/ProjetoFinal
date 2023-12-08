package da_melissa;

import java.util.Date;


public class Bilhete {
	private int id_viagem; 
	private int id_bilhete;
	private Viagem viagem;
	private String nomeCliente;
	private Date dataHoraEmbarque;
    private String nomeVendedor;
    private int numeroAssento;
    private String tipoAssento;
    private double valorPassagem;

	 // Construtores, getters e setters
	public Bilhete() {

	}
	
    public Bilhete(String nomeCliente, String nomeVendedor, Viagem viagem, int numeroAssento, String tipoAssento, double valorPassagem) {
        this.nomeCliente = nomeCliente;
        this.nomeVendedor = nomeVendedor;
        this.viagem = viagem;
        this.numeroAssento = numeroAssento;
        this.tipoAssento = tipoAssento;
        this.valorPassagem = valorPassagem;
    }
    
    public Bilhete(Viagem viagem, String nomeCliente) {
        this.viagem = viagem;
        this.nomeCliente = nomeCliente;
        this.dataHoraEmbarque = new Date(); // Definindo a data e hora do embarque como o momento atual
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }
    
	//getters e setters
	public int getId_bilhete() {
		return id_bilhete;
	}

	public void setId_bilhete(int id_bilhete) {
		this.id_bilhete = id_bilhete;
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

	public Date getDataHoraEmbarque() {
		return dataHoraEmbarque;
	}

	public void setDataHoraEmbarque(Date dataHoraEmbarque) {
		this.dataHoraEmbarque = dataHoraEmbarque;
	}

	public String getNome_vendedor() {
		return nomeVendedor;
	}

	public void setNome_vendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public int getNumeroAssento() {
		return numeroAssento;
	}

	public void setNumeroAssento(int numeroAssento) {
		this.numeroAssento = numeroAssento;
	}

	public String getTipoAssento() {
		return tipoAssento;
	}

	public void setTipoAssento(String tipoAssento) {
		this.tipoAssento = tipoAssento;
	}

	public double getValorPassagem() {
		return valorPassagem;
	}

	public void setValorPassagem(double valorPassagem) {
		this.valorPassagem = valorPassagem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bilhete [id_bilhete=");
		builder.append(id_bilhete);
		builder.append(", viagem=");
		builder.append(viagem);
		builder.append(", nomeCliente=");
		builder.append(nomeCliente);
		builder.append(", dataHoraEmbarque=");
		builder.append(dataHoraEmbarque);
		builder.append(", nomeVendedor=");
		builder.append(nomeVendedor);
		builder.append(", numeroAssento=");
		builder.append(numeroAssento);
		builder.append(", tipoAssento=");
		builder.append(tipoAssento);
		builder.append(", valorPassagem=");
		builder.append(valorPassagem);
		builder.append("]");
		return builder.toString();
	}

	

	    
}