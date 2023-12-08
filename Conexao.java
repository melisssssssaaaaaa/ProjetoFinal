package da_melissaDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    final static String NOME_DO_BANCO = "VendaPassagemOnibus";

    public static Connection conect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + NOME_DO_BANCO + "?useSSL=false";
            return DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
