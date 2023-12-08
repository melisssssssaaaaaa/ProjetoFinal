package da_melissa;

public class Vendedor {
    private int id_vendedor;
    private String nome_vendedor;

    public Vendedor() {}

    public Vendedor(int id_vendedor, String nome_vendedor) {
        this.id_vendedor = id_vendedor;
        this.nome_vendedor = nome_vendedor;
    }

    
 // Getters e setters
	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public String getNome_vendedor() {
		return nome_vendedor;
	}

	public void setNome_vendedor(String nome_vendedor) {
		this.nome_vendedor = nome_vendedor;
	}

    
    
}
