package da_melissaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import da_melissa.Viagem;

public class ViagemDAO {
    public boolean inserirViagem(Viagem viagem) {
        try {
            Connection conn = Conexao.conect();

            String sql = "INSERT INTO viagem (origem, destino, horario, preco, assentos_disponiveis) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, viagem.getOrigem());
            ps.setString(2, viagem.getDestino());
            ps.setString(3, viagem.getHorario());
            ps.setDouble(4, viagem.getPreco());
            ps.setInt(5, viagem.getAssentosDisponiveis());

            int linhasAfetadas = ps.executeUpdate();

            ps.close();
            conn.close();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
