package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin";

    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
            return conexao;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC não encontrado!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao se conectar com o banco: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
