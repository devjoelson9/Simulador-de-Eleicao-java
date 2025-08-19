import java.time.LocalDate;

public abstract class ServidorEfetivo extends Servidores{
    private int tempoEfetivoServico;
    private int tempoGestao;
    public static int quantidadeVotos = 0;
    public boolean votou;

    ServidorEfetivo(String cpf, long matricula, String nome, LocalDate dataNasc, Titulacao titulacao, CampusEnum campus, int tempoEfetivoServico, int tempoGestao){
        super(cpf, matricula, nome, dataNasc, titulacao, campus);
        
        this.setTempoEfetivoServico(tempoEfetivoServico);
        this.setTempoGestao(tempoGestao);
    }

    public int getTempoGestao() {
        return this.tempoGestao;
    }

    public void setTempoGestao(int tempoGestao) {
        this.tempoGestao = tempoGestao;
    }


    public int getTempoEfetivoServico() {
        return this.tempoEfetivoServico;
    }

    public void setTempoEfetivoServico(int tempoEfetivoServico) {
        this.tempoEfetivoServico = tempoEfetivoServico;
    }
    public boolean PodeVotar(){
        return true;
    }
    public boolean podeSeCandidatar(){
        if(tempoEfetivoServico >= 5 && this.getCampus() == CampusEnum.NOVA_CRUZ && CalcularIdade() >= 35 && (getTitulacao() == Titulacao.DOUTORADO || getTempoGestao() >= 2)) {
            return true;
        }
        return false;
    }

    public boolean votou(){
        return votou;
    }
    
    public void Votar(){
        votou = true;
        quantidadeVotos++;
    }

}