package sistema.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.LivroDao;
import sistema.dao.UsuarioDao;
import sistema.model.Emprestimo;
import sistema.model.Livro;
import sistema.model.Usuario;

public class ValidarEmprestimo {

    public static void validar(Emprestimo e) throws IllegalArgumentException, SQLException {
        if (e.getLivro() == null) {
            throw new IllegalArgumentException("Livro inválido!");
        }
        List<Livro> livros = new ArrayList<Livro>();
        livros = new LivroDao().getListaLivroDisponiveis();
        Boolean validade = false;
        for (Livro l : livros) {
            if (l.getId() == e.getLivro().getId()) {
                validade = true;
            }
        }
        if (!validade) {
            throw new IllegalArgumentException("Livro inválido!");
        }

        if (e.getUsuario() == null) {
            throw new IllegalArgumentException("Usuário inválido!");
        }
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = new UsuarioDao().getListaUsuarioVencido();
        for (Usuario u : usuarios) {
            if (u.getId() == e.getUsuario().getId()) {
                throw new IllegalArgumentException("Usuário inválido!");
            }
        }

        if (e.getDataEmprestimo().isAfter(e.getDataDevolucao())) {
            throw new IllegalArgumentException("Data de devolução após data de emprestimo!");
        }
        if (e.getDataEmprestimo().isEqual(e.getDataDevolucao())) {
            throw new IllegalArgumentException("Datas de emprestimo e devolução iguais!");
        }
    }
}
