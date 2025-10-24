package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.Conexao;
import sistema.modelo.Livro;

public class LivroDao {
    private Connection conexao;

    public LivroDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void inserirLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (titulo, autor, editora, genero, ano, isbn, codigo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getEditora());
        stmt.setString(4, livro.getGenero());
        stmt.setInt(5, livro.getAno());
        stmt.setString(6, livro.getIsbn());
        stmt.setString(7, livro.getCodigo());
        stmt.setString(8, livro.getEstado());

        stmt.execute();
        stmt.close();
    }

    public List<Livro> getListaLivro() throws SQLException {
        String sql = "SELECT * FROM livro";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Livro> lista = new ArrayList<Livro>();

        while (rs.next()) {
            Livro temp = new Livro();
            temp.setId(rs.getLong("id"));
            temp.setTitulo(rs.getString("titulo"));
            temp.setAutor(rs.getString("autor"));
            temp.setEditora(rs.getString("editora"));
            temp.setGenero(rs.getString("genero"));
            temp.setAno(rs.getInt("ano"));
            temp.setIsbn(rs.getString("isbn"));
            temp.setCodigo(rs.getString("codigo"));
            temp.setEstado(rs.getString("estado"));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void modificarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo=?, autor=?, editora=?, genero=?, ano=?, isbn=?, codigo=?, estado=? WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getEditora());
        stmt.setString(4, livro.getGenero());
        stmt.setInt(5, livro.getAno());
        stmt.setString(6, livro.getIsbn());
        stmt.setString(7, livro.getCodigo());
        stmt.setString(8, livro.getEstado());
        stmt.setLong(9, livro.getId());

        stmt.execute();
        stmt.close();
    }

    public void removerLivro(Livro livro) throws SQLException {
        String sql = "DELETE FROM livro WHERE id=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, livro.getId());

        stmt.execute();
        stmt.close();
    }

    public Livro getLivro(long id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        Livro livro = new Livro();
        livro.setId(rs.getLong("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setEditora(rs.getString("editora"));
        livro.setGenero(rs.getString("genero"));
        livro.setAno(rs.getInt("ano"));
        livro.setIsbn(rs.getString("isbn"));
        livro.setCodigo(rs.getString("codigo"));
        livro.setEstado(rs.getString("estado"));

        rs.close();
        stmt.close();
        return livro;
    }
}
