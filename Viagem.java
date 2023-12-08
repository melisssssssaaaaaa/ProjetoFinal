package da_melissa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Viagem {
    private int id_viagem;
    private String origem;
    private String destino;
    private double preco;
    private int assentosDisponiveis;
    private int totalAssentos;
    private boolean[] assentoMarcado;
    private List<Venda> vendas;
    private int id_administrador;
    private String horario; 

    public Viagem() {
        this.assentoMarcado = new boolean[totalAssentos];
    }

    public Viagem(String origem, String destino, String horario, double preco, int totalAssentos) {
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.preco = preco;
        this.totalAssentos = totalAssentos;
        this.assentoMarcado = new boolean[totalAssentos];
    }

    
    public Viagem(String origem, String destino, String horario, double preco, int assentosDisponiveis, int id_administrador) {
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.preco = preco;
        this.assentosDisponiveis = assentosDisponiveis;
        this.id_administrador = id_administrador;
        this.assentoMarcado = new boolean[totalAssentos];
    }

    // Método para adicionar uma venda à lista de vendas
    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
        System.out.println("Venda registrada para a viagem.");
    }

    public static Viagem criarViagem(String origem, String destino, String horario, double preco, int totalAssentos) {
        Viagem viagem = new Viagem(origem, destino, horario, preco, totalAssentos);

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:meubanco.db")) {
            String sql = "INSERT INTO viagem (origem, destino, horario, preco, assentosDisponiveis) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, origem);
                pstmt.setString(2, destino);
                pstmt.setString(3, horario+":00");
                pstmt.setDouble(4, preco);
                pstmt.setInt(5, totalAssentos);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viagem;
    }
    // Método para marcar o próximo assento disponível e retornar o número do assento
    public int marcarProximoAssentoDisponivel() {
        for (int i = 0; i < totalAssentos; i++) {
            if (!assentoMarcado[i]) {
                assentoMarcado[i] = true;
                assentosDisponiveis--;
                return i + 1; // Retorna o número do assento (começando de 1)
            }
        }
        return -1; // Retorna -1 se não houver assentos disponíveis
    }

    public boolean haAssentosDisponiveis() {
        return assentosDisponiveis > 0;
    }
    
    public void marcarAssentoComoVendido(int numeroAssento) {
        if (numeroAssento > 0 && numeroAssento <= totalAssentos && !assentoMarcado[numeroAssento - 1]) {
            assentoMarcado[numeroAssento - 1] = true;
            assentosDisponiveis--;
            System.out.println("Assento " + numeroAssento + " marcado como vendido.");
        } else {
            System.out.println("Assento inválido ou já vendido.");
        }
    }

    public double calcularValorPassagem(String tipoAssento) {
        switch (tipoAssento.toLowerCase()) {
            case "convencional":
                return 120.0;
            case "semi-leito":
                return 200.0;
            case "leito":
                return 320.0;
            default:
                System.out.println("Tipo de assento inválido.");
                return 0.0;
        }
    }

    public void realizarVenda(String nomeCliente, String metodoPagamento, String tipoAssento) {
        
        if (assentosDisponiveis > 0) {
            double valor = calcularValorPassagem(tipoAssento);

            // Marcar o próximo assento disponível
            int numeroAssento = marcarProximoAssentoDisponivel();

            if (numeroAssento != -1) {
                // Criar a venda sem a classe TransacaoVenda
                Venda venda = new Venda();
                vendas.add(venda);

                // Se o pagamento foi confirmado, emitir o bilhete
                if (pagamentoConfirmado(metodoPagamento)) {
                    Bilhete bilhete = new Bilhete(this, nomeCliente);
                }

                System.out.println("Venda realizada com sucesso!");
            } else {
                System.out.println("Não há assentos disponíveis para esta viagem.");
            }
        } else {
            System.out.println("Não há assentos disponíveis para esta viagem.");
        }
    }

    // Método para verificar se o pagamento foi confirmado
    private boolean pagamentoConfirmado(String metodoPagamento) {
        // Adicione a lógica de confirmação de pagamento aqui
        // Se o pagamento for confirmado, retorne true; caso contrário, retorne false
        return true; // Altere conforme a lógica real
    }

    
    // setters e getters
    
	public int getId_viagem() {
		return id_viagem;
	}

	public void setId_viagem(int id_viagem) {
		this.id_viagem = id_viagem;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getAssentosDisponiveis() {
		return assentosDisponiveis;
	}

	public void setAssentosDisponiveis(int assentosDisponiveis) {
		this.assentosDisponiveis = assentosDisponiveis;
	}

	public int getTotalAssentos() {
		return totalAssentos;
	}

	public void setTotalAssentos(int totalAssentos) {
		this.totalAssentos = totalAssentos;
	}

	public boolean[] getAssentoMarcado() {
		return assentoMarcado;
	}

	public void setAssentoMarcado(boolean[] assentoMarcado) {
		this.assentoMarcado = assentoMarcado;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public int getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(int id_administrador) {
		this.id_administrador = id_administrador;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Viagem [id_viagem=");
		builder.append(id_viagem);
		builder.append(", origem=");
		builder.append(origem);
		builder.append(", destino=");
		builder.append(destino);
		builder.append(", preco=");
		builder.append(preco);
		builder.append(", assentosDisponiveis=");
		builder.append(assentosDisponiveis);
		builder.append(", totalAssentos=");
		builder.append(totalAssentos);
		builder.append(", assentoMarcado=");
		builder.append(Arrays.toString(assentoMarcado));
		builder.append(", vendas=");
		builder.append(vendas);
		builder.append(", id_administrador=");
		builder.append(id_administrador);
		builder.append(", horario=");
		builder.append(horario);
		builder.append("]");
		return builder.toString();
	}

    

    
	
}
