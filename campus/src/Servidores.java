
import java.time.LocalDate;

public abstract class Servidores extends Pessoa{

    private Titulacao titulacao;
    private CampusEnum campus;

    Servidores(String cpf, long matricula, String nome, LocalDate dataNasc, Titulacao titulacao, CampusEnum campus){
        super(cpf, matricula, nome, dataNasc);
        this.setTitulacao(titulacao);
        this.setCampus(campus);
    }

    public Titulacao getTitulacao() {
        return this.titulacao;
    }

    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    public CampusEnum getCampus() {
        return this.campus;
    }

    public void setCampus(CampusEnum campus) {
        this.campus = campus;
    }
}
