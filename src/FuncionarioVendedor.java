public class FuncionarioVendedor extends Funcionario{

        private double valorVenda;
        private float comissao;

    public FuncionarioVendedor(String matricula, String nome, double valorVenda, float  comissao) {
        super(matricula, nome);
        this.valorVenda = valorVenda;
        this.comissao = comissao;
        this.tipo = "VEND";
    }

    public double getExtras(){
        return this.comissao*this.valorVenda;
    }
    public double getSalarioFinal(){
        return this.getExtras()+this.getSalarioFixo();
    }     
}