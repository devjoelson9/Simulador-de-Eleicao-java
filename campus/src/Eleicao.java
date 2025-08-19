import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Eleicao {

    public static void salvarVotantesEmArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("------------------LISTA DOS ALUNOS---------------------------");
            writer.newLine();
            writer.newLine();
            for (Aluno aluno : Aluno.ALUNOSTOTAL) {
                writer.write("Nome: " + aluno.getNome() + ", Matricula: " + aluno.getMatricula());
                writer.newLine();
            }
            writer.write(" Total de Alunos: " + Aluno.ALUNOSTOTAL.size());
            writer.newLine();
            writer.write("------------------LISTA DE DOCENTES---------------------------");
            writer.newLine();
            writer.newLine();
            for (Docentes docente : Docentes.DOCENTESTOTAL) {
                writer.write("Nome: " + docente.getNome() + ", matricula: " + docente.getMatricula());
                writer.newLine();
            }
            writer.write(" Total de Docentes: " + Docentes.DOCENTESTOTAL.size());
            writer.newLine();
            writer.write("-------------------LISTA DE TECNICOS_ADMINISTRATIVOS-----------------------------------");
            writer.newLine();
            writer.newLine();
            for (Tecnico tecnico : Tecnico.TECNICOSTOTAL) {
                writer.write(" " + tecnico.getNome() + ", Matricula: " + tecnico.getMatricula());
                writer.newLine();
            }
            writer.write(" Total de Tecnicos: " + Tecnico.TECNICOSTOTAL.size());

            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Candidato> candidatos = new ArrayList<>();

        // adicionando os alunos
        new Aluno("234.580.746-45", 2222, "Maria Helena", LocalDate.of(2005, 12, 27), "Tads");
        new Aluno("123.456.789-01", 3333, "José Vitor", LocalDate.of(2002, 05, 7), "Engenharia");
        new Aluno("922.857.745-76", 4444, "Joao Rafael", LocalDate.of(2003, 4, 21), "Medicina");
        new Aluno("234.580.746-45", 5555, "Ana Patricia", LocalDate.of(2002, 6, 7), "Tads");
        new Aluno("123.456.789-01", 6666, "Leticia Lima", LocalDate.of(2004, 5, 10), "Enfermagem");
        new Aluno("922.857.745-76", 7777, "Bruna Ferreira", LocalDate.of(2003, 10, 15), "Tads");

        // adicionando os tecnicos administrativos
        new Tecnico("123.123.123-12", 1231, "Klaus Mikaelson", LocalDate.of(1976, 2, 1), Titulacao.DOUTORADO,
                CampusEnum.NOVA_CRUZ, 8, 4, TipoTecnico.PRESENCIAL);
        new Tecnico("123.123.123-12", 1111, "Helena Beatriz", LocalDate.of(1990, 9, 15), Titulacao.DOUTORADO,
                CampusEnum.NOVA_CRUZ, 6, 4, TipoTecnico.REMOTO);
        new Tecnico("123.123.123-12", 1212, "Roberto Carlos", LocalDate.of(1987, 10, 9), Titulacao.MESTRADO,
                CampusEnum.NOVA_CRUZ, 6, 4, TipoTecnico.PRESENCIAL);

        // adicionando os docentes
        new Docentes("123.123.123-12", 3131, "Miguel Diaz", LocalDate.of(1975, 2, 1), Titulacao.DOUTORADO,
                CampusEnum.NOVA_CRUZ, 6, 6);
        new Docentes("123.123.123-12", 1010, "Yuri Alberto", LocalDate.of(1997, 2, 1), Titulacao.DOUTORADO,
                CampusEnum.NOVA_CRUZ, 5, 3);
        new Docentes("123.123.123-12", 2020, "Teresa Perez", LocalDate.of(1980, 2, 1), Titulacao.DOUTORADO,
                CampusEnum.NOVA_CRUZ, 9, 4);

        Candidato candidato1 = new Candidato(Tecnico.TECNICOSTOTAL.get(0));
        Candidato candidato2 = new Candidato(Docentes.DOCENTESTOTAL.get(0));

        candidatos.add(candidato1);
        candidatos.add(candidato2);

        salvarVotantesEmArquivo("Lista-Dos-Votantes.txt");

        System.out.println("=======================ELEIÇÕES IFRN-NOVA CRUZ =====================================");
        System.out.println("\n");
        System.out.println("-----------------------CANDIDATOS--------------------------------------");
        Urna urna = new Urna();
        System.out.println("------------------------------------------------------------------------");

        urna.cadastrarServidorResponsavel();

        urna.iniciarEleicao();

        System.out.println("--------ELEIÇÃO FINALIZADA--------");
        List<Aluno> alunosNaoVotaram = new ArrayList<>();
        List<Docentes> docentesNaoVotaram = new ArrayList<>();
        List<Tecnico> tecnicosNaoVotaram = new ArrayList<>();

        for (Aluno aluno : Aluno.ALUNOSTOTAL) {
            if (!aluno.votou()) {
                alunosNaoVotaram.add(aluno);
            }
        }
        for (Docentes docente : Docentes.DOCENTESTOTAL) {
            if (!docente.votou()) {
                docentesNaoVotaram.add(docente);
            }
        }

        for (Tecnico tecnico : Tecnico.TECNICOSTOTAL) {
            if (!tecnico.votou()) {
                tecnicosNaoVotaram.add(tecnico);
            }
        }
        salvarOsQNaoVotaram("Lista Dos Eleitores Ausentes.txt", alunosNaoVotaram, docentesNaoVotaram,
                tecnicosNaoVotaram);
        ResultadoEleicao("Resultado-Eleicao.txt", urna);
    }

    public static void salvarOsQNaoVotaram(String nomeArquivos, List<Aluno> alunos, List<Docentes> docentes,
            List<Tecnico> tecnicos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivos))) {
            writer.write("------------------LISTA DOS QUE NÃO VOTARAM---------------------------");
            writer.newLine();
            writer.newLine();
            writer.newLine();

            writer.write("----------------ALUNOS---------------");
            writer.newLine();
            for (Aluno aluno : alunos) {
                writer.write("Nome: " + aluno.getNome() + ", Matrícula: " + aluno.getMatricula());
                writer.newLine();
            }
            writer.newLine();

            writer.write("-------------DOCENTES-------------");
            writer.newLine();
            for (Docentes docente : docentes) {
                writer.write("Nome: " + docente.getNome() + ", Matrícula: " + docente.getMatricula());
                writer.newLine();
            }
            writer.newLine();

            writer.write("-------------TÉCNICOS------------");
            writer.newLine();
            for (Tecnico tecnico : tecnicos) {
                writer.write("Nome: " + tecnico.getNome() + ", Matrícula: " + tecnico.getMatricula());
                writer.newLine();
            }
            writer.newLine();
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static void ResultadoEleicao(String nomeArquivo, Urna urna) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("------------------Resultados da Eleição--------------------");
            writer.newLine();
            writer.newLine();

            for (Candidato candidato : Candidato.CANDIDATOSTOTAL) {
                double percentual = urna.calcularPercentualVotos(candidato);
                writer.write("Candidato: " + candidato.getCandidato().getNome() +
                        " | Percentual de Votos: " + String.format("%.2f", percentual) + "%");
                writer.newLine();
            }

            writer.newLine();
            writer.write("Votos em Branco: " + urna.getVotosBrancos());
            writer.newLine();
            writer.write("Votos Nulos: " + urna.getVotosNulos());
            writer.newLine();

            writer.write("-----------------------------------------------------------");
            writer.newLine();

            System.out.println("Arquivo de resultados salvo com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo de resultados: " + e.getMessage());
        }
    }
}