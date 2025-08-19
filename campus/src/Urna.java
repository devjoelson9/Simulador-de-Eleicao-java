import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Urna {

    private int votosTotais;
    private static int votosBrancos = 0;
    private static int votosNulos = 0;
    private Long servidorResponsavel = null;
    private Scanner scan = new Scanner(System.in);

    public Urna() {
        exibirZeresima();
    }

    public int getVotosBrancos() {
        return Urna.votosBrancos;
    }

    public int getVotosNulos() {
        return Urna.votosNulos;
    }

    private void exibirZeresima() {
        System.out.println("\n===== Zerésima - Estado Inicial da Urna =====");

        for (Candidato candidato : Candidato.CANDIDATOSTOTAL) {
            System.out.println("Candidato: " + candidato.getCandidato().getNome() +
                    " | Número: " + candidato.getNumeroIdentificador() +
                    " | Votos iniciais: 0");
        }

        System.out.println("\n===== Total de Eleitores Votantes por Categoria =====");
        System.out.println("Docentes: " + Docentes.DOCENTESTOTAL.size());
        System.out.println("Discentes (Alunos): " + Aluno.ALUNOSTOTAL.size());
        System.out.println("Técnicos Administrativos: " + Tecnico.TECNICOSTOTAL.size());
        int soma = Aluno.ALUNOSTOTAL.size() + Docentes.DOCENTESTOTAL.size() + Tecnico.TECNICOSTOTAL.size();
        System.out.println("Total de Eleirores votantes: " + soma);
    }

    public void cadastrarServidorResponsavel() throws InterruptedException {
        System.out.print("Servidor responsável, digite sua matrícula para iniciar a eleição:");
        long matricula = scan.nextLong();

        for (Tecnico tecnico : Tecnico.TECNICOSTOTAL) {
            if (tecnico.getMatricula() == matricula) {
                servidorResponsavel = matricula;
                System.out.println("Servidor responsável cadastrado com sucesso! Eleição pode ser iniciada.");
                menuGerandoEleicao();
                return;
            }
        }
        for (Docentes docentes : Docentes.DOCENTESTOTAL) {
            if (docentes.getMatricula() == matricula) {
                servidorResponsavel = matricula;
                System.out.println("Servidor responsável cadastrado com sucesso! Eleição pode ser iniciada.");
                menuGerandoEleicao();
                return;
            }
        }
    }

    public static void menuGerandoEleicao() throws InterruptedException {
        System.out.print("\n  INICIALIZANDO ELEIÇÃO ");

        for (int i = 0; i < 15; i++) {
            System.out.print("\r  " + ".".repeat(i) + " INICIALIZANDO ELEIÇÃO " + ".".repeat(i));
            Thread.sleep(300);
        }

        System.out.println();
    }

    private Pessoa validarEleitor() {
        System.out.print("\nDigite sua matrícula para votar: ");
        long matricula = scan.nextLong();

        for (Aluno aluno : Aluno.ALUNOSTOTAL) {
            if (aluno.getMatricula() == matricula)
                return aluno;
        }
        for (Docentes docente : Docentes.DOCENTESTOTAL) {
            if (docente.getMatricula() == matricula)
                return docente;
        }
        for (Tecnico tecnico : Tecnico.TECNICOSTOTAL) {
            if (tecnico.getMatricula() == matricula)
                return tecnico;
        }
        System.out.println("Erro: Matrícula não encontrada. Você não está na lista de eleitores.");
        return null;
    }

    private void registrarVoto(Candidato candidato, Pessoa eleitor) {
        if (!eleitor.votou()) {
            if (eleitor instanceof Docentes) {
                candidato.AddVotosRDocentes();
            } else if (eleitor instanceof Aluno) {
                candidato.AddVotosRAlunos();
            } else if (eleitor instanceof Tecnico) {
                candidato.AddVotosRTecnicos();
            }
            candidato.AdicionaVoto();
            eleitor.Votar();
            votosTotais++;
        } else {
            System.out.println("Erro: Eleitor já votou.");
        }
    }

    public void votoEmBranco(Pessoa eleitor) {
        if (!eleitor.votou()) {
            votosBrancos++;
            eleitor.Votar();
            votosTotais++;
        }
    }

    public void votoNulo(Pessoa eleitor) {
        if (!eleitor.votou()) {
            votosNulos++;
            eleitor.Votar();
            votosTotais++;
        }
    }

    public void votarCandidato(Pessoa eleitor) {
        System.out.println("Escolha um candidato pelo número:");
        for (Candidato candidato : Candidato.CANDIDATOSTOTAL) {
            System.out.println("Nome: " + candidato.getCandidato().getNome() +
                    " | Número: " + candidato.getNumeroIdentificador() +
                    " | Cor: " + candidato.getCor());

        }

        int numeroEscolhido = scan.nextInt();
        Candidato candidatoEscolhido = null;

        for (Candidato candidato : Candidato.CANDIDATOSTOTAL) {
            if (candidato.getNumeroIdentificador() == numeroEscolhido) {
                candidatoEscolhido = candidato;
                break;
            }
        }

        if (candidatoEscolhido != null) {
            registrarVoto(candidatoEscolhido, eleitor);
        } else {
            System.out.println("Número inválido. Voto nulo registrado.");
            votoNulo(eleitor);
        }
    }

    public void ExibirEleicao() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Votar em um candidato");
        System.out.println("2 - Votar em branco");
        System.out.println("3 - Votar nulo");
    }

    public void iniciarEleicao() throws InterruptedException {
        if (servidorResponsavel == null) {
            System.out.println("Erro: A eleição só pode começar após o cadastro de um servidor responsável.");
            return;
        }

        List<Pessoa> eleitores = new ArrayList<>();
        eleitores.addAll(Aluno.ALUNOSTOTAL);
        eleitores.addAll(Docentes.DOCENTESTOTAL);
        eleitores.addAll(Tecnico.TECNICOSTOTAL);

        int totalEleitores = eleitores.size();
        int eleitoresProcessados = 0;

        while (eleitoresProcessados < totalEleitores) {
            Pessoa eleitor = null;

            while (eleitor == null) {
                eleitor = validarEleitor();
                if (eleitor == null) {
                    System.out.println("Tente novamente. Matrícula inválida.");
                } else if (eleitor.votou()) {
                    System.out.println("\nVocê já votou!");
                    eleitor = null;
                }
            }

            ExibirEleicao();
            int opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    votarCandidato(eleitor);
                    break;
                case 2:
                    votoEmBranco(eleitor);
                    break;
                case 3:
                    votoNulo(eleitor);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (eleitor.votou()) {
                System.out.println("***Voto Registrado***");
                eleitoresProcessados++; // Apenas conta um eleitor válido que realmente votou
            }

            System.out.println("\nServidor Responsável, deseja finalizar a eleição?");
            System.out.println("Se quiser finalizar => Digite: 1");
            System.out.println("Se quiser continuar => Digite qualquer número");

            int opcao1 = scan.nextInt();
            if (opcao1 == 1) {
                encerrarEleicao();
                return;
            }
        }

        encerrarEleicao();
    }

    public void encerrarEleicao() throws InterruptedException {
        System.out.print("\nServidor responsável, digite sua matrícula para encerrar a eleição: ");
        long matriculaDigitada = scan.nextLong();
        if (matriculaDigitada == servidorResponsavel) {
            finalizarEleicao();
        } else {
            System.out.println("Erro: Apenas o servidor responsável pode encerrar a eleição.");
        }
    }

    public Candidato CandidatoVencedor() throws InterruptedException {
        Candidato vencedor = null;
        double maiorPercentual = 0;
        ArrayList<Candidato> empatados = new ArrayList<>();

        for (Candidato candidato : Candidato.CANDIDATOSTOTAL) {
            double percentual = calcularPercentualVotos(candidato);
            if (percentual > maiorPercentual) {
                maiorPercentual = percentual;
                vencedor = candidato;
                empatados.clear();
                empatados.add(candidato);
            } else if (percentual == maiorPercentual) {
                empatados.add(candidato);
            }
        }

        if (empatados.size() > 1) {
            vencedor = Desempate(empatados);
        }

        if (vencedor != null) {
            System.out.println("\n===== Candidato Vencedor =====");
            System.out.println("Nome: " + vencedor.getCandidato().getNome());
            System.out.println("Número: " + vencedor.getNumeroIdentificador());
            System.out.println("Percentual de Votos Ponderado: " + maiorPercentual + "%");
        } else {
            System.out.println("\nNenhum candidato venceu a eleição.");
        }

        return vencedor;
    }

    public double calcularPercentualVotos(Candidato candidato) {
        int votosDocentes = candidato.getVotosRDocentes();
        int votosDiscentes = candidato.getVotosRAlunos();
        int votosTecnicos = candidato.getVotosRTecnicos();

        double pesoDiscentes = (double) votosDiscentes / Aluno.ALUNOSTOTAL.size();
        double pesoDocentes = (double) votosDocentes / Docentes.DOCENTESTOTAL.size();
        double pesoTecnicos = (double) votosTecnicos / Tecnico.TECNICOSTOTAL.size();

        return (100.0 / 3) * (pesoDocentes + pesoDiscentes + pesoTecnicos);
    }

    public static void DesempateEleicao() throws InterruptedException {

        System.out.print("\n  DESEMPATE ATIVADO ");

        for (int i = 0; i < 15; i++) {
            System.out.print("\r  " + ".".repeat(i) + "DESEMPATE " + ".".repeat(i));
            Thread.sleep(300);
        }

        System.out.println();
    }

    public Candidato Desempate(ArrayList<Candidato> empatados) throws InterruptedException {
        DesempateEleicao();

        empatados.sort(Comparator.comparingInt(Candidato::getVotosRecebidos).reversed());
        if (empatados.size() == 1 || empatados.get(0).getVotosRecebidos() > empatados.get(1).getVotosRecebidos()) {
            System.out.println("Desempate resolvido pelo critério: Maior número de votos recebidos.");
            return empatados.get(0);
        }

        empatados
                .sort(Comparator.comparingInt(c -> ((Candidato) c).getCandidato().getTempoEfetivoServico()).reversed());
        if (empatados.size() == 1 || empatados.get(0).getCandidato().getTempoEfetivoServico() > empatados.get(1)
                .getCandidato().getTempoEfetivoServico()) {
            System.out.println("Desempate resolvido pelo critério:  Maior tempo efetivo de serviço.");
            return empatados.get(0);
        }

        empatados.sort(Comparator.comparingInt(c -> ((Candidato) c).getCandidato().CalcularIdade()).reversed());
        if (empatados.size() == 1
                || empatados.get(0).getCandidato().CalcularIdade() > empatados.get(1).getCandidato().CalcularIdade()) {
            System.out.println("Desempate resolvido pelo critério: Candidato mais velho.");
            return empatados.get(0);
        }

        Random random = new Random();
        Candidato vencedor = empatados.get(random.nextInt(empatados.size()));
        System.out.println("Desempate resolvido pelo critério: Sorteio aleatório.");
        return vencedor;
    }

    public static void FinalizarMensagem() throws InterruptedException {
        System.out.print("\n  FINALIZANDO ELEIÇÃO ");

        for (int i = 0; i < 15; i++) {
            System.out.print("\r  " + ".".repeat(i) + "FINALIZANDO ELEIÇÃO " + ".".repeat(i));
            Thread.sleep(300);
        }

        System.out.println();
    }

    public void finalizarEleicao() throws InterruptedException {

        int totalEleitores = Aluno.ALUNOSTOTAL.size() + Docentes.DOCENTESTOTAL.size() + Tecnico.TECNICOSTOTAL.size();
        int eleitoresAusentes = totalEleitores - votosTotais;

        System.out.println("\n===== Resultados da Eleição =====");
        System.out.println("\n");
        for (Candidato candidato : Candidato.CANDIDATOSTOTAL) {
            System.out.println("Candidato: " + candidato.getCandidato().getNome() +
                    " | Votos: " + candidato.getVotosRecebidos());
        }
        System.out.println("Votos em Branco: " + votosBrancos);
        System.out.println("Votos Nulos: " + votosNulos);
        System.out.println("Total de votos realizados: " + votosTotais);
        System.out.println("Total de Eleitores votantes: " + totalEleitores);
        System.out.println("Total de Eleitores ausentes: " + eleitoresAusentes);

        CandidatoVencedor();
        FinalizarMensagem();
    }
}
