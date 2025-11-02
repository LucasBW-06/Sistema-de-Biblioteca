package sistema;

import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<String>();
        lista.add("00000000000");
        lista.add("11111111111");
        lista.add("22222222222");
        lista.add("33333333333");
        lista.add("44444444444");
        lista.add("55555555555");
        lista.add("66666666666");
        lista.add("77777777777");
        lista.add("88888888888");
        lista.add("99999999999");

        for (String cpf : lista) {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += ((10 - i) * Character.getNumericValue(cpf.charAt(i)));
            }
            int digito1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += ((11 - i) * Character.getNumericValue(cpf.charAt(i)));
            }
            int digito2 = 11 - (soma % 11);

            if (Character.getNumericValue(cpf.charAt(9)) != digito1 || Character.getNumericValue(cpf.charAt(10)) != digito2) {
                System.out.println(cpf + ": invalido");
            } else {
                System.out.println(cpf + ": valido");
            }
        }
    }
}
