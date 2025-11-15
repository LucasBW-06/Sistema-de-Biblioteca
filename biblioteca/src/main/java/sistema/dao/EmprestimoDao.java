package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sistema.Conexao;
import sistema.model.Emprestimo;
import sistema.model.Livro;
import sistema.model.Usuario;

public class EmprestimoDao {
   
    private Connection conexao;

    public EmprestimoDao() throws SQLException {
        this.conexao = Conexao.getConexao();
    }

    public void inserirEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao, data_devolvido) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setLong(1, emprestimo.getLivro().getId());
        stmt.setLong(2, emprestimo.getUsuario().getId());

        if (emprestimo.getDataEmprestimo() != null) {
            stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
        } else {
            stmt.setNull(3, Types.DATE);
        }

        if (emprestimo.getDataDevolucao() != null) {
            stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));
        } else {
            stmt.setNull(4, Types.DATE);
        }

        if (emprestimo.getDataDevolvido() != null) {
            stmt.setDate(5, java.sql.Date.valueOf(emprestimo.getDataDevolvido()));
        } else {
            stmt.setNull(5, Types.DATE);
        }
        
        stmt.execute();
        stmt.close();
    }

    public List<Emprestimo> getListaEmprestimo() throws SQLException {
        String sql = "SELECT * FROM emprestimo";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Emprestimo> lista = new ArrayList<Emprestimo>();

        LivroDao livroDao = new LivroDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        while (rs.next()) {
            Emprestimo temp = new Emprestimo();
            temp.setId(rs.getLong("id"));
            Livro livroTemp = livroDao.getLivro(rs.getLong("livro_id"));
            temp.setLivro(livroTemp);
            Usuario usuarioTemp = usuarioDao.getUsuario(rs.getLong("usuario_id"));
            temp.setUsuario(usuarioTemp);

            if (rs.getDate("data_emprestimo") != null) {
                temp.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            }

            if (rs.getDate("data_devolucao") != null) {
                temp.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }

            if (rs.getDate("data_devolvido") != null) {
                temp.setDataDevolvido(rs.getDate("data_devolvido").toLocalDate());
            }
            temp.setEstado((rs.getString("estado")));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Emprestimo> getListaEmprestimoPendente() throws SQLException {
        String sql = "SELECT * FROM v_emprestimos_pendentes";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Emprestimo> lista = new ArrayList<Emprestimo>();

        LivroDao livroDao = new LivroDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        while (rs.next()) {
            Emprestimo temp = new Emprestimo();
            temp.setId(rs.getLong("id"));
            Livro livroTemp = livroDao.getLivro(rs.getLong("livro_id"));
            temp.setLivro(livroTemp);
            Usuario usuarioTemp = usuarioDao.getUsuario(rs.getLong("usuario_id"));
            temp.setUsuario(usuarioTemp);

            if (rs.getDate("data_emprestimo") != null) {
                temp.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            }

            if (rs.getDate("data_devolucao") != null) {
                temp.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }

            if (rs.getDate("data_devolvido") != null) {
                temp.setDataDevolvido(rs.getDate("data_devolvido").toLocalDate());
            }
            temp.setEstado((rs.getString("estado")));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Emprestimo> getListaEmprestimoPendente(String titulo, String codigo, String nome, String cpf) throws SQLException {
        String sql = "SELECT e.* FROM v_emprestimos_pendentes e JOIN livro l ON e.livro_id = l.id JOIN usuario u ON e.usuario_id = u.id WHERE l.titulo LIKE ? AND l.codigo LIKE ? AND u.nome LIKE ? AND u.cpf LIKE ?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setString(1, "%" + titulo + "%");
        stmt.setString(2, "%" + codigo + "%");
        stmt.setString(3, "%" + nome + "%");
        stmt.setString(4, "%" + cpf + "%");

        ResultSet rs = stmt.executeQuery();

        List<Emprestimo> lista = new ArrayList<Emprestimo>();

        LivroDao livroDao = new LivroDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        while (rs.next()) {
            Emprestimo temp = new Emprestimo();
            temp.setId(rs.getLong("id"));
            Livro livroTemp = livroDao.getLivro(rs.getLong("livro_id"));
            temp.setLivro(livroTemp);
            Usuario usuarioTemp = usuarioDao.getUsuario(rs.getLong("usuario_id"));
            temp.setUsuario(usuarioTemp);

            if (rs.getDate("data_emprestimo") != null) {
                temp.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            }

            if (rs.getDate("data_devolucao") != null) {
                temp.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }

            if (rs.getDate("data_devolvido") != null) {
                temp.setDataDevolvido(rs.getDate("data_devolvido").toLocalDate());
            }
            temp.setEstado((rs.getString("estado")));
            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public List<Emprestimo> getHistoricoUsuario(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE usuario_id=? ORDER BY data_emprestimo";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setLong(1, usuario.getId());

        ResultSet rs = stmt.executeQuery();

        List<Emprestimo> lista = new ArrayList<Emprestimo>();

        LivroDao livroDao = new LivroDao();

        while (rs.next()) {
            Emprestimo temp = new Emprestimo();
            temp.setId(rs.getLong("id"));
            Livro livroTemp = livroDao.getLivro(rs.getLong("livro_id"));
            temp.setLivro(livroTemp);
            temp.setUsuario(usuario);

            if (rs.getDate("data_emprestimo") != null) {
                temp.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            }

            if (rs.getDate("data_devolucao") != null) {
                temp.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }

            if (rs.getDate("data_devolvido") != null) {
                temp.setDataDevolvido(rs.getDate("data_devolvido").toLocalDate());
            }

            temp.setEstado(rs.getString("estado"));

            lista.add(temp);
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void modificarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET livro_id=?, usuario_id=?, data_emprestimo=?, data_devolucao=?, data_devolvido=? WHERE id=?";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, emprestimo.getLivro().getId());
        stmt.setLong(2, emprestimo.getUsuario().getId());

        if (emprestimo.getDataEmprestimo() != null) {
            stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
        } else {
            stmt.setNull(3, Types.DATE);
        }

        if (emprestimo.getDataDevolvido() != null) {
            stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));
        } else {
            stmt.setNull(4, Types.DATE);
        }

        if (emprestimo.getDataDevolvido() != null) {
            stmt.setDate(5, java.sql.Date.valueOf(emprestimo.getDataDevolvido()));
        } else {
            stmt.setNull(5, Types.DATE);
        }

        stmt.setLong(6, emprestimo.getId());

        stmt.execute();
        stmt.close();
    }

    public void removerEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE id=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, emprestimo.getId());

        stmt.execute();
        stmt.close();
    }

    public void softdeleteEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "CALL p_softdelete_emprestimo(?)";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, emprestimo.getId());

        stmt.execute();
        stmt.close();
    }

    public void registrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "CALL p_registrar_emprestimo(?, ?, ?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setLong(1, emprestimo.getLivro().getId());
        stmt.setLong(2, emprestimo.getUsuario().getId());
        if (emprestimo.getDataEmprestimo() != null) {
            stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
        } else {
            stmt.setNull(3, Types.DATE);
        }

        if (emprestimo.getDataDevolucao() != null) {
            stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));
        } else {
            stmt.setNull(4, Types.DATE);
        }

        stmt.execute();
        stmt.close();
    }

    public void registrarDevolucao(Emprestimo emprestimo, LocalDate dataDevolvido)throws SQLException {
        String sql = "CALL p_registrar_devolucao(?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setLong(1, emprestimo.getId());
        if (dataDevolvido != null) {
            stmt.setDate(2, java.sql.Date.valueOf(dataDevolvido));
        } else {
            stmt.setNull(2, Types.DATE);
        }

        stmt.execute();
        stmt.close();
    }

    public Emprestimo getEmprestimo(Long id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE id=?";
        
        PreparedStatement stmt = this.conexao.prepareStatement(sql);

        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();

        Emprestimo emprestimo = new Emprestimo();

        LivroDao livroDao = new LivroDao();
        UsuarioDao usuarioDao = new UsuarioDao();

        if (rs.next()) {
            emprestimo.setId(rs.getLong("id"));
            Livro livroTemp = livroDao.getLivro(rs.getLong("livro_id"));
            emprestimo.setLivro(livroTemp);
            Usuario usuarioTemp = usuarioDao.getUsuario(rs.getLong("usuario_id"));
            emprestimo.setUsuario(usuarioTemp);

            if (rs.getDate("data_emprestimo") != null) {
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            }

            if (rs.getDate("data_devolucao") != null) {
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao").toLocalDate());
            }

            if (rs.getDate("data_devolvido") != null) {
                emprestimo.setDataDevolucao(rs.getDate("data_devolvido").toLocalDate());
            }
            emprestimo.setEstado((rs.getString("estado")));
        }

        rs.close();
        stmt.close();
        return emprestimo;
    }
}
