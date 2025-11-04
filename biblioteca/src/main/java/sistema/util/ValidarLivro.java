package sistema.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.LivroDao;
import sistema.model.Livro;

public class ValidarLivro {
    public static void validar(Livro l) throws IllegalArgumentException, SQLException {
        if (l.getTitulo() == null || l.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Título inválido!");
        }

        if (l.getAutor() == null || l.getAutor().isEmpty()) {
            throw new IllegalArgumentException("Autor inválido!");
        }

        if (l.getEditora() == null || l.getEditora().isEmpty()) {
            throw new IllegalArgumentException("Editora inválido!");
        }

        if (l.getGenero() == null || l.getGenero().isEmpty()) {
            throw new IllegalArgumentException("Gênero inválido!");
        }

        if (l.getCodigo() == null || l.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("Código inválido!");
        }
        
        LivroDao daoL = new LivroDao();
        List<Livro> livros = new ArrayList<Livro>();
        livros = daoL.getListaLivro();
        System.out.println(l.getCodigo());
        Boolean validade = true;
        for (Livro livro : livros) {
            System.out.println(livro.getCodigo());
            if (l.getCodigo().equals(livro.getCodigo())) {
                validade = false;
            }
        }
        
        if (!validade) {
            throw new IllegalArgumentException("Código já registrado!");
        }
    }
}
