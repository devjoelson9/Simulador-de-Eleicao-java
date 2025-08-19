import java.time.LocalDate;
import java.util.ArrayList;

public class Docentes extends ServidorEfetivo {

    public static final ArrayList<Docentes> DOCENTESTOTAL = new ArrayList<>();
    public static int quantidadeVotos = 0;
    private boolean votou;

    Docentes(String cpf, int matricula, String nome, LocalDate dataNasc, Titulacao titulacao, CampusEnum campus,
            int tempoEfetivoServico, int tempoGestao) {
        super(cpf, matricula, nome, dataNasc, titulacao, campus, tempoEfetivoServico, tempoGestao);
        votou = false;
        DOCENTESTOTAL.add(this);

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

}
