package da_melissaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import da_melissa.Vendedor;

public class VendedorDAO {

    public boolean inserirVendedor(Vendedor vendedor) {
        try {
            Connection conn = Conexao.conect();

            String sql = "INSERT INTO Vendedor (nome_vendedor) VALUES (?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vendedor.getNome_vendedor());

            int linhasAfetadas = ps.executeUpdate();

            ps.close();
            conn.close();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean alterarVendedor(Vendedor vendedor) {
        try {
            Connection conn = Conexao.conect();

            String sql = "UPDATE vendedor SET nome_vendedor = ? WHERE id_vendedor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vendedor.getNome_vendedor());
            ps.setInt(2, vendedor.getId_vendedor());

            int linhasAfetadas = ps.executeUpdate();

            ps.close();
            conn.close();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean excluirVendedor(int id) {
        try {
            Connection conn = Conexao.conect();

            String sql = "DELETE FROM vendedor WHERE id_vendedor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();

            ps.close();
            conn.close();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vendedor consultarVendedor(int id) {
        try {
            Connection conn = Conexao.conect();

            String sql = "SELECT * FROM vendedor WHERE id_vendedor > ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            Vendedor vendedor = null;

            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setId_vendedor(rs.getInt("id"));
                vendedor.setNome_vendedor(rs.getString("nome"));
            }

            rs.close();
            ps.close();
            conn.close();

            return vendedor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
