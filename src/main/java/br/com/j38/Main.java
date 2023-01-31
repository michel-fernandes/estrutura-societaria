package br.com.j38;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Empresa> quadrosSocietarios = new ArrayList<>(){{
            add(new Empresa(new Pessoa("12.123.123/1234-01", 10000.00),
                    new ArrayList<>(){{
                        add(new Pessoa("123.123.123-01", 1000.00));
                        add(new Pessoa("123.123.123-02", 2000.00));
                        add(new Pessoa("12.123.123/1234-04", 40000.00));
                    }}
            ));
            add(new Empresa(new Pessoa("12.123.123/1234-02", 20000.00),
                    new ArrayList<>(){{
                        add(new Pessoa("123.123.123-03", 3000.00));
                        add(new Pessoa("12.123.123/1234-01", 10000.00));
                    }}
            ));
            add(new Empresa(new Pessoa("12.123.123/1234-03", 30000.00),
                    new ArrayList<>(){{
                        add(new Pessoa("12.123.123/1234-01", 10000.00));
                        add(new Pessoa("12.123.123/1234-02", 20000.00));
                        add(new Pessoa("12.123.123/1234-03", 30000.00));
                    }}
            ));
            add(new Empresa(new Pessoa("12.123.123/1234-04", 40000.00),
                    new ArrayList<>(){{
                        add(new Pessoa("123.123.123-04", 4000.00));
                        add(new Pessoa("123.123.123-05", 5000.00));
                        add(new Pessoa("12.123.123/1234-03", 30000.00));
                    }}
            ));
        }};

        calcularComprometimentoFinanceiro(quadrosSocietarios.get(0), quadrosSocietarios);
        calcularComprometimentoFinanceiro(quadrosSocietarios.get(1), quadrosSocietarios);
        calcularComprometimentoFinanceiro(quadrosSocietarios.get(2), quadrosSocietarios);
        calcularComprometimentoFinanceiro(quadrosSocietarios.get(3), quadrosSocietarios);
    }

    static public void calcularComprometimentoFinanceiro(Empresa empresa, List<Empresa> bdEmMemoria){
        CalculadoraComprometimento calculadoraComprometimento = new CalculadoraComprometimento(
                empresa, bdEmMemoria);
        empresa.setComprometimentoFinanceiro( calculadoraComprometimento.getComprometimentoFinanceiro());
        System.out.println("Comprometimento financeiro do CNPJ -> " + empresa.getEmpresa().getId() +
                " é de: R$ " + empresa.getComprometimentoFinanceiro());
    }
}