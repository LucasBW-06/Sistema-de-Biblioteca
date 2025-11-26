package sistema.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.FuncionarioDao;

public class Funcionario {
    protected long id;
    protected String nome;
    protected String login;
    protected String cargo;
    protected String senha;
    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void validar() throws IllegalArgumentException, SQLException {
        if (this.getNome() == null || this.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }

        if (this.getLogin() == null || this.getLogin().isEmpty()) {
            throw new IllegalArgumentException("Login inválido!");
        }

        if (this.getCargo() == null || this.getCargo().isEmpty()) {
            throw new IllegalArgumentException("Cargo inválido!");
        }

        if (this.getSenha() == null || this.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha inválida!");
        }
        
        FuncionarioDao daoF = new FuncionarioDao();
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        funcionarios = daoF.getListaFuncionario();
        Boolean validade = true;
        for (Funcionario funcionario : funcionarios) {
            if (this.getLogin().equals(funcionario.getLogin())) {
                validade = false;
            }
        }
        
        if (!validade) {
            throw new IllegalArgumentException("Login já em uso!");
        }
    }
}
