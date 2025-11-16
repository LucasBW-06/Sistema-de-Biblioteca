package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.model.Funcionario;
import sistema.util.Conexao;

public class FuncionarioDao {
    
    private Connection conexao;

    public FuncionarioDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void inserirFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, login, cargo, senha) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getLogin());
        stmt.setString(3, funcionario.getCargo());
        stmt.setString(4, funcionario.getSenha());

        stmt.execute();
        stmt.close();
    }

    public List<Funcionario> getListaFuncionario() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Funcionario> lista = new ArrayList<Funcionario>();

        while (rs.next()) {
            Funcionario temp = new Funcionario();
            temp.setId(rs.getLong("id"));
            temp.setNome(rs.getString("nome"));
            temp.setCargo(rs.getString("cargo"));
            temp.setLogin(rs.getString("login"));
            temp.setSenha(rs.getString("senha"));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Funcionario> getListaFuncionarioAtivo() throws SQLException {
        String sql = "SELECT * FROM v_funcionario_ativo ORDER BY nome";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Funcionario> lista = new ArrayList<Funcionario>();

        while (rs.next()) {
            Funcionario temp = new Funcionario();
            temp.setId(rs.getLong("id"));
            temp.setNome(rs.getString("nome"));
            temp.setCargo(rs.getString("cargo"));
            temp.setLogin(rs.getString("login"));
            temp.setSenha(rs.getString("senha"));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void modificarFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome=?, login=?, cargo=?, senha=? WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getLogin());
        stmt.setString(3, funcionario.getCargo());
        stmt.setString(4, funcionario.getSenha());
        stmt.setLong(5, funcionario.getId());

        stmt.execute();
        stmt.close();
    }

    public void removerFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, funcionario.getId());

        stmt.execute();
        stmt.close();
    }

    public void softdeleteFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "CALL p_softdelete_funcionario(?)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, funcionario.getId());

        stmt.execute();
        stmt.close();
    }

    public Funcionario getFuncionario(long id) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Funcionario funcionario = new Funcionario();

        if (rs.next()) {
            funcionario.setId(rs.getLong("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));
        }

        rs.close();
        stmt.close();
        return funcionario;
    }
}
