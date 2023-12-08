package da_melissaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import da_melissa.Administrador;

public class AdministradorDAO {

    private static final String INSERT_QUERY = "INSERT INTO administrador (nome) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE administrador SET nome = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM administrador WHERE id = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM administrador WHERE id = ?";

    public boolean inserirAdministrador(Administrador administrador) {
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {

            ps.setString(1, administrador.getNome_administrador());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterarAdministrador(Administrador administrador) {
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY)) {

            ps.setString(1, administrador.getNome_administrador());
            ps.setInt(2, administrador.getId_administrador());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirAdministrador(int id) {
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement(DELETE_QUERY)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Administrador consultarAdministrador(int id) {
        Administrador administrador = null;
        try (Connection conn = Conexao.conect();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador = new Administrador();
                    administrador.setId_administrador(rs.getInt("id"));
                    administrador.setNome_administrador(rs.getString("nome"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrador;
    }
}
