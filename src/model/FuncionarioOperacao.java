package model;


public class FuncionarioOperacao extends Funcionario{

        private int qtdePecas;
        private double valorPeca;

    public FuncionarioOperacao(String matricula, String nome, int qtdePecas, double valorPeca) {
        super(matricula, nome);
        this.qtdePecas = qtdePecas;
        this.valorPeca = valorPeca;
        this.tipo = "PROD";
    }

    public double getExtras(){
        return this.valorPeca*this.qtdePecas;
    }
    
    public double getSalarioFinal(){
        return this.getExtras()+this.getSalarioFixo();
    }
    
}