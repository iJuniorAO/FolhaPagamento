public class Funcionario {

    private String matricula;
    private String nomeCompleto;
    private double SalarioBase = 2_000;
    String tipo = "base";


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
    public double getSalarioFixo(){
        return this.SalarioBase;
    }   
    public String getTipo(){
        return this.tipo;
    }   
}
