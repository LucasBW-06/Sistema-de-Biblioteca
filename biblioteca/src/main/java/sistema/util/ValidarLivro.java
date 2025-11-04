package sistema.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.LivroDao;
import sistema.model.Livro;

public class ValidarLivro {
    public static void validar(Livro l) throws IllegalArgumentException, SQLException {
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
