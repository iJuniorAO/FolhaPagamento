public class Funcionario {

    private String matricula;
    private String nomeCompleto;


    public Funcionario(String matricula, String nomeCompleto) {
        this.matricula = matricula;
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula(){
        return this.matricula;
    }

    public String getNomeCompleto(){
        return this.nomeCompleto;
    }   
}
