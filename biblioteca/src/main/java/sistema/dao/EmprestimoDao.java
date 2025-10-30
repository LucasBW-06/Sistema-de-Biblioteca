package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import sistema.Conexao;
import sistema.modelo.Emprestimo;
import sistema.modelo.Livro;
import sistema.modelo.Usuario;

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
                temp.setDataDevolucao(rs.getDate("data_devolvido").toLocalDate());
            }
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
}
