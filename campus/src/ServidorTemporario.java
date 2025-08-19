import java.time.LocalDate;

public  class ServidorTemporario extends Servidores{
    ServidorTemporario(String cpf, int matricula, String nome, LocalDate dataNasc, Titulacao titulacao, CampusEnum campus){
        super(cpf, matricula, nome, dataNasc, titulacao, campus);
    }
    public boolean PodeVotar(){
        return false;
    }
}
