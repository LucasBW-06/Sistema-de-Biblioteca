package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.Conexao;
import sistema.model.Livro;

public class LivroDao {
    private Connection conexao;

    public LivroDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void inserirLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (titulo, autor, editora, genero, ano, isbn, codigo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getEditora());
        stmt.setString(4, livro.getGenero());
        stmt.setInt(5, livro.getAno());
        stmt.setString(6, livro.getIsbn());
        stmt.setString(7, livro.getCodigo());

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
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Livro> getListaLivro(String titulo) throws SQLException {
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, "%" + titulo + "%");

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
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Livro> getListaLivro(String titulo, String codigo) throws SQLException {
        String sql = "SELECT * FROM livro WHERE titulo LIKE ? AND codigo LIKE ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, "%" + titulo + "%");
        stmt.setString(2, "%" + codigo + "%");

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
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Livro> getListaLivroAtivo() throws SQLException {
        String sql = "SELECT * FROM v_livro_ativo ORDER BY titulo";
        
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
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Livro> getListaLivroDisponiveis() throws SQLException {
        String sql = "SELECT * FROM v_livros_disponiveis ORDER BY titulo";
        
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
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Livro> getListaLivroDisponiveis(String titulo, String codigo) throws SQLException {
        String sql = "SELECT * FROM v_livros_disponiveis WHERE titulo LIKE ? AND codigo LIKE ? ORDER BY titulo";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, "%" + titulo + "%");
        stmt.setString(2, "%" + codigo + "%");

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
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void modificarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo=?, autor=?, editora=?, genero=?, ano=?, isbn=?, codigo=? WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setString(3, livro.getEditora());
        stmt.setString(4, livro.getGenero());
        stmt.setInt(5, livro.getAno());
        stmt.setString(6, livro.getIsbn());
        stmt.setString(7, livro.getCodigo());
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

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Livro livro = new Livro();
        
        if (rs.next()) {
            livro.setId(rs.getLong("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setEditora(rs.getString("editora"));
            livro.setGenero(rs.getString("genero"));
            livro.setAno(rs.getInt("ano"));
            livro.setIsbn(rs.getString("isbn"));
            livro.setCodigo(rs.getString("codigo"));
        }
        
        rs.close();
        stmt.close();
        return livro;
    }
}
