package sistema.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.LivroDao;

public class Livro {
    protected long id;
    protected String titulo;
    protected String autor;
    protected String editora;
    protected String genero;
    protected int ano;
    protected String isbn;
    protected String codigo;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEditora() {
        return editora;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void validar() throws IllegalArgumentException, SQLException {
        if (this.getTitulo() == null || this.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Título inválido!");
        }

        if (this.getAutor() == null || this.getAutor().isEmpty()) {
            throw new IllegalArgumentException("Autor inválido!");
        }

        if (this.getEditora() == null || this.getEditora().isEmpty()) {
            throw new IllegalArgumentException("Editora inválido!");
        }

        if (this.getGenero() == null || this.getGenero().isEmpty()) {
            throw new IllegalArgumentException("Gênero inválido!");
        }

        if (this.getCodigo() == null || this.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("Código inválido!");
        }
        
        LivroDao daoL = new LivroDao();
        List<Livro> livros = new ArrayList<Livro>();
        livros = daoL.getListaLivro();
        System.out.println(livros);
        Boolean validade = true;
        for (Livro livro : livros) {
            if (this.getCodigo().equals(livro.getCodigo()) && this.getId() != livro.getId()) {
                validade = false;
            }
        }
        
        if (!validade) {
            throw new IllegalArgumentException("Código já registrado!");
        }
    }
}
