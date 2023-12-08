package da_melissa;

import java.util.Arrays;

public class MapaAssentos {

    // Constantes para representar o estado dos assentos
    private static final int LIVRE = 0;
    private static final int OCUPADO = 1;

    private byte[] assentosDisponiveis;
    private byte[] J;
    private byte[] C;

    // Construtores
    public MapaAssentos(byte[] J, byte[] C) {
        if (J.length != C.length) {
            throw new IllegalArgumentException("Os arrays J e C devem ter o mesmo comprimento.");
        }
        this.J = J;
        this.C = C;
    }

    public MapaAssentos(byte[] assentosDisponiveis) {
        if (assentosDisponiveis == null) {
            throw new IllegalArgumentException("O array de assentos disponíveis não pode ser nulo.");
        }
        this.assentosDisponiveis = assentosDisponiveis;
    }

    // Métodos getters e setters para acessar ou manipular os dados encapsulados
    public byte[] getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(byte[] assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public void exibirMapaAssentos() {
        System.out.println("\n================================");
        System.out.println("M A P A   D E   O C U P A Ç Ã O:");
        System.out.println("================================");
        System.out.println("JANELA\t\tCORREDOR");
        for (byte iFileira = 1; iFileira < 25; iFileira++) {
            System.out.print(iFileira + " - ");
            exibirEstadoAssento(J[iFileira - 1]);
            System.out.print("\t" + iFileira + " - ");
            exibirEstadoAssento(C[iFileira - 1]);
            System.out.println();
        }
    }

    // Método privado para exibir o estado do assento
    private void exibirEstadoAssento(byte estado) {
        if (estado == LIVRE) {
            System.out.print("[LIVRE] ");
        } else {
            System.out.print("[OCUPADO]");
        }
    }

    public boolean reservarAssento(int numeroAssento) {
        if (!validarNumeroAssento(numeroAssento)) {
            System.out.println("Número de assento inválido.");
            return false;
        }

        int indiceAssento = numeroAssento - 1;

        if (assentosDisponiveis[indiceAssento] == LIVRE) {
            assentosDisponiveis[indiceAssento] = OCUPADO;
            System.out.println("Assento #" + numeroAssento + " reservado com sucesso.");
            return true;
        } else {
            System.out.println("Assento #" + numeroAssento + " já está reservado.");
            return false;
        }
    }

    public boolean isAssentoDisponivel(int numeroAssento) {
        if (!validarNumeroAssento(numeroAssento)) {
            return false;
        }
        return assentosDisponiveis[numeroAssento - 1] == LIVRE;
    }

    // Método privado para validar o número do assento
    private boolean validarNumeroAssento(int numeroAssento) {
        return numeroAssento > 0 && numeroAssento <= assentosDisponiveis.length;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapaAssentos [assentosDisponiveis=");
		builder.append(Arrays.toString(assentosDisponiveis));
		builder.append(", J=");
		builder.append(Arrays.toString(J));
		builder.append(", C=");
		builder.append(Arrays.toString(C));
		builder.append("]");
		return builder.toString();
	}
    
    
}
