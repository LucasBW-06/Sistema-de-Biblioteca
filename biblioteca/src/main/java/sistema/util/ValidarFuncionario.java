package sistema.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.dao.FuncionarioDao;
import sistema.model.Funcionario;

public class ValidarFuncionario {
    public static void validar(Funcionario f) throws IllegalArgumentException, SQLException {
        if (f.getNome() == null || f.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }

        if (f.getCargo() == null || f.getCargo().isEmpty()) {
            throw new IllegalArgumentException("Cargo inválido!");
        }

        if (f.getSenha() == null || f.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha inválida!");
        }
        
        FuncionarioDao daoF = new FuncionarioDao();
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        funcionarios = daoF.getListaFuncionario();
        Boolean validade = true;
        for (Funcionario funcionario : funcionarios) {
            if (f.getLogin().equals(funcionario.getLogin())) {
                validade = false;
            }
        }
        
        if (!validade) {
            throw new IllegalArgumentException("Login já em uso!");
        }
    }
}
