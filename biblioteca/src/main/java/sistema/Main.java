package sistema;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import sistema.dao.EmprestimoDao;
import sistema.dao.LivroDao;
import sistema.dao.UsuarioDao;
import sistema.model.Emprestimo;
import sistema.model.Livro;
import sistema.model.Usuario;

public class Main {
    public static void main(String[] args) {
            try {
            UsuarioDao daoU = new UsuarioDao();
            LivroDao daoL = new LivroDao();
            EmprestimoDao daoE = new EmprestimoDao();

            Usuario u = new Usuario();
            u.setNome("Lucas");
            u.setEmail("lucas@email.com");
            u.setCpf("12345678900");
            u.setTelefone("66999999999");
            daoU.inserirUsuario(u);

            Livro l = new Livro();
            l.setTitulo("Teste");
            l.setAutor("autor");
            l.setCodigo("1234567891");
            daoL.inserirLivro(l);

            Emprestimo e = new Emprestimo();
            e.setLivro(daoL.getLivro(1));
            e.setUsuario(daoU.getUsuario(1));
            e.setDataDevolucao(LocalDate.of(2006, 10, 10));
            e.setDataEmprestimo(LocalDate.of(2006, 10, 1));
            daoE.inserirEmprestimo(e);

            List<Emprestimo> emprestimo = daoE.getListaEmprestimo();
            for (Emprestimo em : emprestimo) {
                System.out.println(em.getDataDevolvido());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}