package da_melissaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.util.Date;

import da_melissa.Bilhete;


public class BilheteDAO {
	public boolean inserirBilhete(Bilhete bilhete) {
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Bilhete (id_viagem, nomeCliente, dataHoraEmbarque) VALUES (?, ?, ?);")) {

            ps.setInt(1, bilhete.getViagem().getId_viagem());
            ps.setString(2, bilhete.getNomeCliente());

            // Convertendo Date para LocalDateTime e, em seguida, para Timestamp
            Instant instant = bilhete.getDataHoraEmbarque().toInstant();
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            Timestamp timestamp = Timestamp.valueOf(dateTime);

            ps.setTimestamp(3, timestamp);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean alterarBilhete(Bilhete bilhete) {
	    try (Connection conn = Conexao.conect();
	         PreparedStatement ps = conn.prepareStatement("UPDATE Bilhete SET nomeCliente = ?, dataHoraEmbarque = ? WHERE id = ?;")) {

	        ps.setString(1, bilhete.getNomeCliente());

	        // Convertendo de java.util.Date para java.sql.Timestamp
	        java.sql.Timestamp dataHoraEmbarque = new java.sql.Timestamp(bilhete.getDataHoraEmbarque().getTime());

	        ps.setTimestamp(2, dataHoraEmbarque);
	        ps.setInt(3, bilhete.getId_bilhete());

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


    public boolean excluirBilhetePorId(int idBilhete) {
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Bilhete WHERE id = ?;")) {

            ps.setInt(1, idBilhete);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Bilhete consultarBilhetePorId(int idBilhete) {
        Bilhete bilhete = null;
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Bilhete WHERE id = ?;")) {

            ps.setInt(1, idBilhete);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bilhete = new Bilhete();
                    bilhete.setId_bilhete(rs.getInt("id"));
                    bilhete.setId_viagem(rs.getInt("id_viagem"));
                    bilhete.setNomeCliente(rs.getString("nomeCliente"));

                    // Convertendo de java.sql.Timestamp para java.util.Date
                    java.sql.Timestamp timestamp = rs.getTimestamp("dataHoraEmbarque");
                    bilhete.setDataHoraEmbarque(new Date(timestamp.getTime()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilhete;
    }


    // Implementar outros métodos conforme necessário
}

