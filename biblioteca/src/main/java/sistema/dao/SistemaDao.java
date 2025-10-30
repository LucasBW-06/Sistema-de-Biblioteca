package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import sistema.Conexao;
import sistema.modelo.Emprestimo;

public class SistemaDao {
    
    private Connection conexao;

    public SistemaDao() throws SQLException {
        this.conexao = Conexao.getConexao();
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

    public void registrarDevolucao(long livroId, int ano, int mes, int dia)throws SQLException {
        String sql = "CALL p_registrar_devolucao(?, ?)";

        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.setLong(1, livroId);
        stmt.setDate(2, java.sql.Date.valueOf(LocalDate.of(ano, mes, dia)));

        stmt.execute();
        stmt.close();
    }
}
