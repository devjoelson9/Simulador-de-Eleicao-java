
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

public class Candidato {

    public static final ArrayList<Candidato> CANDIDATOSTOTAL = new ArrayList<>();
    private ServidorEfetivo candidato;
    private String cor;
    private int numeroIdentificador;
    private int votosRecebidos;
    private int VotosRAlunos = 0;
    private int VotosRDocentes = 0;
    private int VotosRTecnicos = 0;
    private static int n_candidatos = 0;

    private static HashSet<Integer> numerosUsados = new HashSet<>();
    private static HashSet<String> coresUsadas = new HashSet<>();
    private static String[] cores = { "azul claro", "amarelo", "vermelho", "verde", "roxo", "rosa", "laranja", "preto",
            "branco", "cinza", "bege", "dourado", "prata", "violeta", "azul escuro" };

    Candidato(ServidorEfetivo candidato) {
        if (candidato.podeSeCandidatar()) {
            this.candidato = candidato;
            this.numeroIdentificador = gerarNumeroIdentificador();
            this.cor = CorAleatoria();
            votosRecebidos = 0;
            CANDIDATOSTOTAL.add(this);
            n_candidatos++;
        } else {
            System.out.println("O Servidor não está apto para se candidatar");
        }
    }

    public ServidorEfetivo getCandidato() {
        return this.candidato;
    }

    public void setCandidato(ServidorEfetivo candidato) {
        this.candidato = candidato;
    }

    private int gerarNumeroIdentificador() {
        Random rand = new Random();
        int numero;
        do {
            numero = rand.nextInt(100) + 1;
        } while (numerosUsados.contains(numero));
        numerosUsados.add(numero);
        return numero;
    }

    private String CorAleatoria() {
        Random rand = new Random();
        String cor;
        do {
            cor = cores[rand.nextInt(cores.length)];
        } while (coresUsadas.contains(cor));
        coresUsadas.add(cor);
        return cor;
    }

    public String getCor() {
        return this.cor;
    }

    public int getNumeroIdentificador() {
        return this.numeroIdentificador;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setNumeroIdentificador(int numeroIdentificador) {
        this.numeroIdentificador = numeroIdentificador;
    }

    public void exibir() {
        System.out.println("------------Candidato " + n_candidatos + " -------------- ");
        System.out.println("Nome:  " + candidato.getNome());
        System.out.println("Numero Identificador: " + getNumeroIdentificador());
        System.out.println("Cor do Candidato: " + CorAleatoria());
        System.out.println("\n");
    }

    public int getVotosRecebidos() {
        return votosRecebidos;
    }

    public int AdicionaVoto() {
        votosRecebidos++;
        return votosRecebidos;
    }

    public int getVotosRAlunos() {
        return VotosRAlunos;
    }

    public int AddVotosRAlunos() {

        VotosRAlunos++;
        return VotosRAlunos;
    }

    public int getVotosRDocentes() {
        return VotosRDocentes;
    }

    public int AddVotosRDocentes() {

        VotosRDocentes++;
        return VotosRDocentes;
    }

    public int getVotosRTecnicos() {
        return VotosRTecnicos;
    }

    public int AddVotosRTecnicos() {

        VotosRTecnicos++;
        return VotosRTecnicos;
    }

}