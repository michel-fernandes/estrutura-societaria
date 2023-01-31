package br.com.j38;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Empresa {
    private Pessoa empresa;
    private List<Pessoa> sociedade;
    private Double comprometimentoFinanceiro;

    public Empresa(Pessoa empresa, List<Pessoa> sociedade) {
        this.empresa = empresa;
        this.sociedade = sociedade;
    }
}
