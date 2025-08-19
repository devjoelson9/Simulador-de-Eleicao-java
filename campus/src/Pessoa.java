import java.time.LocalDate;
import java.time.Period;


 public abstract class Pessoa {
    private String cpf;
    private long matricula;
    private String nome;
    private LocalDate dataNasc;
    private boolean jaVotou = false;
    
    public Pessoa(String cpf, long matricula, String nome, LocalDate dataNasc) {
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setNome(nome);
        this.setDataNasc(dataNasc);
    }
    
    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getMatricula() {
        return this.matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public abstract boolean PodeVotar();
    
    
    public int CalcularIdade() {
        return Period.between(getDataNasc(), LocalDate.now()).getYears();
    }
    
    public boolean votou() {
        return jaVotou;
    }

    public void Votar() {
        this.jaVotou = true;
    }
    
}
