import java.time.LocalDate;
import java.util.ArrayList;

public class Tecnico extends ServidorEfetivo {
    public static final ArrayList<Tecnico> TECNICOSTOTAL = new ArrayList<>();
    private TipoTecnico tipoTecnico;
    public static int quantidadeVotos = 0;
    public boolean votou;

    Tecnico(String cpf, int matricula, String nome, LocalDate dataNasc, Titulacao titulacao, CampusEnum campus,
            int tempoEfetivoServico, int tempoGestao, TipoTecnico tipoTecnico) {
        super(cpf, matricula, nome, dataNasc, titulacao, campus, tempoEfetivoServico, tempoGestao);
        votou = false;
        TECNICOSTOTAL.add(this);
    }

    public boolean PodeVotar() {
        return true;
    }

    public boolean votou() {
        return votou;
    }

    public void Votar() {
        if (!votou) {
            votou = true;
            quantidadeVotos++;
        }
    }

    public TipoTecnico getTipoTecnico() {
        return this.tipoTecnico;
    }

    public void setTipoTecnico(TipoTecnico tipoTecnico) {
        this.tipoTecnico = tipoTecnico;
    }

}
