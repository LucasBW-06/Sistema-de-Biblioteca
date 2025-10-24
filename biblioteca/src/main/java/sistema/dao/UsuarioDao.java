package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.Conexao;
import sistema.modelo.Usuario;

public class UsuarioDao {
    
    private Connection conexao;

    public UsuarioDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, cpf, telefone) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getCpf());
        stmt.setString(4, usuario.getTelefone());

        stmt.execute();
        stmt.close();
    }

    public List<Usuario> getListaUsuario() throws SQLException {
        String sql = "SELECT * FROM usuario";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Usuario> lista = new ArrayList<Usuario>();

        while (rs.next()) {
            Usuario temp = new Usuario();
            temp.setId(rs.getLong("id"));
            temp.setNome(rs.getString("nome"));
            temp.setEmail(rs.getString("email"));
            temp.setCpf(rs.getString("cpf"));
            temp.setTelefone(rs.getString("telefone"));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void modificarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome=?, email=?, cpf=?, telefone=? WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getCpf());
        stmt.setString(4, usuario.getTelefone());
        stmt.setLong(5, usuario.getId());

        stmt.execute();
        stmt.close();
    }

    public void removerUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, usuario.getId());

        stmt.execute();
        stmt.close();
    }

    public Usuario getUsuario(long id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setTelefone(rs.getString("telefone"));

        rs.close();
        stmt.close();
        return usuario;
    }
}
