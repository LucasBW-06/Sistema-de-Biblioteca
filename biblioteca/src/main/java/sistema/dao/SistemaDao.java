package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.Conexao;
import sistema.modelo.Usuario;

public class SistemaDao {
    
    private Connection conexao;

    public SistemaDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void registrarEmprestimo(Usuario usuario) throws SQLException {
        
    }
}
