package br.com.j38;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraComprometimento {
    private List<String> pessoaFisicas = new ArrayList<>();
    private List<String> pessoaJuridicas = new ArrayList<>();
    private String pessoaJuridicaRaiz;
    private Map<Integer, String> nodes = new HashMap<>();
    private Integer hashNode;
    private Double comprometimentoFinanceiro;
    private List<Empresa> bdEmpresas = new ArrayList<>();

    public CalculadoraComprometimento(Empresa empresa, List<Empresa> bdEmpresas) {
        this.pessoaJuridicas.add(empresa.getEmpresa().getId());
        this.pessoaJuridicaRaiz = empresa.getEmpresa().getId();
        this.hashNode = 0;
        this.nodes = new HashMap<>();
        this.nodes.put(hashNode, empresa.getEmpresa().getId());
        this.comprometimentoFinanceiro = empresa.getEmpresa().getBensImovel();
        this.bdEmpresas = bdEmpresas;
        this.comprometimentoFinanceiro = calcularComprometimentoFinanceiro(empresa.getSociedade());
    }
    private Double calcularComprometimentoFinanceiro(List<Pessoa> socios){
        List<Pessoa> pessoasFisica = socios.stream()
                .filter(pessoa -> pessoa.getId().length() == 14).toList();
        pessoasFisica.forEach(pf -> incluirPFPilha(pf));
        List<Pessoa> pessoasJuridica = socios.stream()
                .filter(pessoa -> pessoa.getId().length() == 18).toList();
        pessoasJuridica.forEach(pj -> incluirPJNaPilha(pj));

        return comprometimentoFinanceiro;
    }

    private void incluirPFPilha(Pessoa pessoa){
        if(pessoaFisicas.isEmpty()){
            pessoaFisicas.add(pessoa.getId());
            comprometimentoFinanceiro += pessoa.getBensImovel();
        } else {
            if(!pessoaFisicas.contains(pessoa.getId())){
                pessoaFisicas.add(pessoa.getId());
                comprometimentoFinanceiro += pessoa.getBensImovel();
            }
        }
    }
    private void incluirPJNaPilha(Pessoa pessoa) {
        if(!pessoaJuridicas.contains(pessoa.getId())){
            hashNode += 1;
            nodes.put(hashNode, pessoa.getId());
            pessoaJuridicas.add(pessoa.getId());
            comprometimentoFinanceiro += pessoa.getBensImovel();
            List<Empresa> pessoaJuridicaNova = bdEmpresas.stream().filter(p->p.getEmpresa().getId().equals(pessoa.getId()))
                            .toList();
            if(!pessoaJuridicaNova.isEmpty())
                calcularComprometimentoFinanceiro(pessoaJuridicaNova.get(0).getSociedade());
        }

    }

}
