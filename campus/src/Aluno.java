import java.time.LocalDate;
import java.util.ArrayList;

class Aluno extends Pessoa {

    public static final ArrayList<Aluno> ALUNOSTOTAL = new ArrayList<>();
    public static int quantidadeVotos = 0;
    private String curso;
    private boolean votou;

    Aluno(String cpf, int matricula, String nome, LocalDate dataNasc, String curso) {
        super(cpf, matricula, nome, dataNasc);
        this.setCurso(curso);
        votou = false;

        ALUNOSTOTAL.add(this);
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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