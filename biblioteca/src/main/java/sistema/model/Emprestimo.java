package sistema.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.LivroDao;
import sistema.dao.UsuarioDao;

public class Emprestimo {
    protected long id;
    protected Livro livro;
    protected Usuario usuario;
    protected LocalDate dataEmprestimo;
    protected LocalDate dataDevolucao;
    protected LocalDate dataDevolvido;
    protected String estado;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolvido(LocalDate dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    public LocalDate getDataDevolvido() {
        return dataDevolvido;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void validar() throws IllegalArgumentException, SQLException {
        if (this.getLivro() == null) {
            throw new IllegalArgumentException("Livro inválido!");
        }
        List<Livro> livros = new ArrayList<Livro>();
        livros = new LivroDao().getListaLivroDisponiveis();
        Boolean validade = false;
        for (Livro l : livros) {
            if (l.getId() == this.getLivro().getId()) {
                validade = true;
            }
        }
        if (!validade) {
            throw new IllegalArgumentException("Livro inválido!");
        }

        if (this.getUsuario() == null) {
            throw new IllegalArgumentException("Usuário inválido!");
        }
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = new UsuarioDao().getListaUsuarioVencido();
        for (Usuario u : usuarios) {
            if (u.getId() == this.getUsuario().getId()) {
                throw new IllegalArgumentException("Usuário inválido!");
            }
        }

        if (this.getDataEmprestimo().isAfter(this.getDataDevolucao())) {
            throw new IllegalArgumentException("Data de devolução após data de emprestimo!");
        }
        if (this.getDataEmprestimo().isEqual(this.getDataDevolucao())) {
            throw new IllegalArgumentException("Datas de emprestimo e devolução iguais!");
        }
    }
}
