
import factory.Seeder;
import java.util.ArrayList;
import java.util.Scanner;
import model.Funcionario;
import model.FuncionarioOperacao;
import model.FuncionarioVendedor;

public class App {

    static ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
    static int sair = -1;
    static Scanner input = new Scanner(System.in);

    // caso true instancia clases para testes
    static boolean SEEDER_FUNCIONARIO = false;

    public static void main(String[] args) throws Exception {

        if (SEEDER_FUNCIONARIO){
            System.out.println("\nInserido dados teste...");
            Seeder.carregarDadosDemonstracao(listaFuncionario);
        }

        do { 
            Tela.MostrarOpcoes();
            sair = validaOpcaoTela1(input.nextLine());

            switch (sair) {
                case 0:
                    sair=0;
                    Tela.MostrarAlerta("Finalizando...");
                    break;
                case 1:
                case 2:
                case 3:
                    String nome;
                    String matricula;
                    
                    
                    nome = validaInputNome();
                    if (nome == null){
                        Tela.MostrarAlerta("Cancelando Cadastro");
                        continue;
                    }
                    
                    matricula = validaInputMatricula();
                    if (matricula==null){
                        Tela.MostrarAlerta("Cancelando Cadastro");
                        continue;
                    }

                    switch (sair) {
                        case 1:
                            Funcionario FuncionarioBase = new Funcionario(matricula, nome);
                            listaFuncionario.add(FuncionarioBase);
                            Tela.MostrarAlerta("Funcionario Cadastrado com Sucesso");
                            break;

                        case 2:
                            double valorVendas = validaInputMoeda("Digite o valor total das Vendas: ");
                            if (Double.isNaN(valorVendas)){
                                Tela.MostrarAlerta("Cancelando Cadastro");
                                continue;
                            }
                            
                            float comissao = validaInputPorcentagem();
                            if (Float.isNaN(comissao)){
                                Tela.MostrarAlerta("Cancelando Cadastro");
                                continue;    
                            }                           
                            FuncionarioVendedor FuncionarioVendas = new FuncionarioVendedor(matricula, nome, valorVendas, comissao);
                            listaFuncionario.add(FuncionarioVendas);
                            Tela.MostrarAlerta("Funcionario Cadastrado com Sucesso");
                            break;
                        
                        case 3:
                            double valorUnitario = validaInputMoeda("Digite o valor unitário da peça:");
                            if (Double.isNaN(valorUnitario)){
                                Tela.MostrarAlerta("Cancelando Cadastro");
                                continue;
                            }

                            int quantidadePecaProduzida = validaInputInt();
                            if (quantidadePecaProduzida==-1){
                                Tela.MostrarAlerta("Cancelando Cadastro");
                                continue;
                            }
                            
                            FuncionarioOperacao FuncionarioOperacao = new FuncionarioOperacao(matricula, nome, quantidadePecaProduzida, valorUnitario);
                            listaFuncionario.add(FuncionarioOperacao);

                            break;
                            
                    }
                    break;
                case 4:
                    if (listaFuncionario.isEmpty()){
                        Tela.GeraFolhaZeroFuncionario();
                    }else{
                        Tela.GeraFolhaHeader(listaFuncionario.size());

                        double totalFuncionarioPadrao=0;
                        double totalComissao=0;
                        double totalFuncionarioVendedor=0;
                        double totalProdutividade=0;
                        double totalFuncionarioOperador=0;
                        double totalFolhaPgto;

                        for (Funcionario funcionario : listaFuncionario){

                            Tela.GeraFolhaBody(
                                funcionario.getNomeCompleto(),
                                funcionario.getMatricula(),
                                funcionario.getTipo(),
                                funcionario.getSalarioFixo(),
                                funcionario.getExtras(),
                                funcionario.getSalarioFinal());
                
                            switch (funcionario.getTipo()) {
                                case "PADR":
                                    totalFuncionarioPadrao +=funcionario.getSalarioFixo();
                                    break;
                                case "VEND":
                                    totalComissao += funcionario.getExtras();
                                    totalFuncionarioVendedor += funcionario.getSalarioFinal();                        
                                    break;
                                    
                                case "PROD":
                                    totalProdutividade += funcionario.getExtras();
                                    totalFuncionarioOperador += funcionario.getSalarioFinal();                        
                                    break;
                            }   
                        }
                        totalFolhaPgto = totalFuncionarioPadrao + totalFuncionarioVendedor + totalFuncionarioOperador;
                        Tela.GeraFolhaFooter(totalComissao, totalProdutividade, totalFuncionarioPadrao, totalFuncionarioVendedor, totalFuncionarioOperador, totalFolhaPgto);
                    }
                    break;
                case -1:
                default:                    
            }
        
        } while (sair!=0);
        input.close();
    }
    
    /**
     * 1. Valida se é inteiro (somente números)
     * 2. Valida se está entre 0~4
     * @param textoInput
     * @return numero válido
     */
    private static int validaOpcaoTela1(String textoInput){
        try {
            int numeroValido = Integer.parseInt(textoInput);
            if ((numeroValido<0) || (numeroValido>=5)){
                Tela.MostrarErro("Opção inválida. Digite somente números de 0 a 4");
                return -1;
            }
            return numeroValido;
        }catch(java.lang.NumberFormatException e) {
            Tela.MostrarErro("Digite somente números");
            return -1;

        } catch (Exception e) {
            Tela.MostrarErro("Erro Inesperado. Tente novamente: ");
            return -1;
        }

    }

    /**
     * 1. Se vazio retorna null (cancela operação)
     * 2. valida se é nome completo (possui espaço)
     * 3. Valida se possui digitos
     * @return nome (lowercase) para compação em banco de dados ArrayList
     */
    private static String validaInputNome(){
        while (true) {
            Tela.MostrarHeader("Digite o nome completo | 'Enter' para cancelar");
            String nome = input.nextLine().trim();

            if (nome.isEmpty()){
                return null;
            }
            if (!nome.contains(" ")){
                Tela.MostrarErro("Necessário preencher nome completo");
                continue;
            }
            boolean temNumero = false;
            for (char c : nome.toCharArray()){
                if (Character.isDigit(c)){
                    temNumero = true;
                    break;
                }
            }
            if (temNumero){
                Tela.MostrarErro("Não pode haver números");
                continue;
            }

            return nome.toLowerCase();
        }
    }

    /**
     * 1. Se vazio retorna null (cancela operação)
     * 2. Valida se a matrícula já foi cadastrada
     * @return matricula
     */
    private static String validaInputMatricula(){
        while (true) {

            Tela.MostrarSubheader("Digite o número da matrícula | 'Enter' para cancelar");
            String matricula = input.nextLine().trim(); 

            if (matricula.isEmpty()){
                return null;
            }
            boolean repetido = false;
            for (Funcionario funcionario : listaFuncionario){
                if (funcionario.getMatricula().equals(matricula)){
                    repetido = true;
                    break;
                }
            }
            if (repetido){
                Tela.MostrarErro("Matrícula já cadastrada");
                continue;
            }
            return matricula;  
        }
    }

    /** 
     * 1. Se vazio retorna null (cancela operação)
     * 2. Valida se pode ser convertido para double (possui letras/caracteres especiais)
     * 3. Valida se é negativo
     * @param mensagem será passado no print Inicial
     * @return moeda
     */
    private static double validaInputMoeda(String mensagem){
        while (true) {
            Tela.MostrarSubheader(mensagem + " | 'Enter' para cancelar");
            String moeda = input.nextLine().trim();

            if (moeda.isEmpty()){
                return Double.NaN;
            }
            
            moeda = moeda.replace(",", ".");

            try {
                double numeroValido = Double.parseDouble(moeda);
                if (numeroValido<0){
                    Tela.MostrarErro("Valor Negativo");
                    continue;
                }
                return numeroValido;
            }
            catch(java.lang.NumberFormatException e) {
                Tela.MostrarErro("Digite somente números");
            }
            catch (Exception e) {
                Tela.MostrarErro("Erro inesperado. Tente novamente");
            }
            
        }
    }

    /**
     * 1. Se vazio retorna null (cancela operação)
     * 2. Valida se pode ser convertido para float (possui letras/caracteres especiais)
     * 3. Valida se é negativo
     * @return numeroValido/100
     */
    private static float validaInputPorcentagem(){
        while (true) {
            Tela.MostrarSubheader("Digite a porcentagem de comissão | 'Enter' para cancelar");
            String porcentagemValida = input.nextLine().trim();

            if (porcentagemValida.isEmpty()){
                return Float.NaN;
            }
            porcentagemValida = porcentagemValida.replace(",", ".");

            try {
                float numeroValido = Float.parseFloat(porcentagemValida);

                if (numeroValido<0){
                    Tela.MostrarErro("Não pode existir comissão negativa");
                    continue;
                }
                return numeroValido/100;
            }
            catch(java.lang.NumberFormatException e) {
                Tela.MostrarErro("Digite somente números");
            }
            catch (Exception e) {
                Tela.MostrarErro("Erro inesperado. Tente novamente");
            }
            
        }
    }
    /**
     * 1. Vazio retorna -1
     * 2. Valida se pode ser convertido para inteiro
     * 3. Valida se é negativo
     * @return qtProduzida
     */
    private static int validaInputInt(){
        while (true) {
            Tela.MostrarSubheader("Digite quantidade de peças produzidas | 'Enter' para cancelar");
            String qtProduzida = input.nextLine().trim();
    
            if (qtProduzida.isEmpty()){
                return -1;
            }
            try {
                int numeroValido = Integer.parseInt(qtProduzida);
    
                if (numeroValido<0){
                    Tela.MostrarErro("Não pode existir quantidade produzida negativa");
                    continue;
                }
                return numeroValido;
            }
            catch(java.lang.NumberFormatException e) {
                Tela.MostrarErro("Erro. Digite somente números inteiros");
            }
            catch (Exception e) {
                Tela.MostrarErro("Erro Inesperado. Tente novamente: ");
            }
            
        }

    }


}