
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
    static int sair = -1;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        do { 
            mostrarTelaLogin();
            sair = validaOpcaoTela1(input.nextLine());

            switch (sair) {
                case 0:
                    sair=0;
                    System.out.println("Finalizando...");
                    break;
                case 1:
                case 2:
                case 3:
                    String nome;
                    String matricula;
                    System.out.println("==================================");
                    System.out.println("Cadastro Funcionário");
                    
                    nome = validaInputNome();
                    if (nome == null){
                        System.out.println("Cancelando Cadastro...");
                        continue;
                    }
                    
                    matricula = validaInputMatricula();
                    if (matricula==null){
                        System.out.println("Cancelando Cadastro...");
                        continue;
                    }

                    switch (sair) {
                        case 1:
                            Funcionario FuncionarioBase = new Funcionario(matricula, nome);
                            listaFuncionario.add(FuncionarioBase);
                            System.out.println("Funcionário Cadastrado com Sucesso");
                            break;

                        case 2:
                            double valorVendas = validaInputMoeda("Digite o valor total das Vendas: ");
                            if (Double.isNaN(valorVendas)){
                                System.out.println("Cancelando Cadastro...");
                                continue;
                            }
                            
                            float comissao = validaInputPorcentagem();
                            if (Float.isNaN(comissao)){
                                System.out.println("Cancelando Cadastro...");
                                continue;    
                            }                           
                            FuncionarioVendedor FuncionarioVendas = new FuncionarioVendedor(matricula, nome, valorVendas, comissao);
                            listaFuncionario.add(FuncionarioVendas);
                            System.out.println("Funcionário Cadastrado com Sucesso");
                            break;
                        
                        case 3:
                            double valorUnitario = validaInputMoeda("Digite o valor unitário da peça:");
                            if (Double.isNaN(valorUnitario)){
                                System.out.println("Cancelando Cadastro...");
                                continue;
                            }

                            int quantidadePecaProduzida = validaInputInt();
                            if (quantidadePecaProduzida==-1){
                                System.out.println("Cancelando Cadastro");
                                continue;
                            }
                            
                            FuncionarioOperacao FuncionarioOperacao = new FuncionarioOperacao(matricula, nome, quantidadePecaProduzida, valorUnitario);
                            listaFuncionario.add(FuncionarioOperacao);

                            break;
                            
                    }
                    break;
                case 4:
                    System.out.println("Folha pgto...");
                    mostraFolhaPgto();
                    break;
                case -1:
                default:                    
            }
        
        } while (sair!=0);
        input.close();
    }
    
    private static int validaOpcaoTela1(String textoInput){
        // caso erro retorna -1
        // validado returna input
        try {
            int numeroValido = Integer.parseInt(textoInput);
            if ((numeroValido<0) || (numeroValido>=5)){
                System.out.println("Opção inválida. Digite somente números de 0 a 4");
                return -1;
            }
            return numeroValido;
        }catch(java.lang.NumberFormatException e) {
            System.out.println("Erro. Digite somente números");
            return -1;

        } catch (Exception e) {
            System.out.println("Erro Inesperado. Tente novamente: "+e);
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
            System.out.println("Digite o nome completo | 'Enter' para cancelar");
            String nome = input.nextLine().trim();

            if (nome.isEmpty()){
                return null;
            }
            if (!nome.contains(" ")){
                System.out.println("ERRO! Necessário preencher nome Completo");
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
                System.out.println("ERRO! Não pode haver números");
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
            System.out.println("Digite o número da Matrícula | 'Enter' para cancelar");
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
                System.out.println("ERRO. Matrícula já cadastrada");
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
            System.out.println(mensagem + " | 'Enter' para cancelar");
            String moeda = input.nextLine().trim();

            if (moeda.isEmpty()){
                return Double.NaN;
            }
            
            moeda = moeda.replace(",", ".");

            try {
                double numeroValido = Double.parseDouble(moeda);
                if (numeroValido<0){
                    System.out.println("ERRO. Valor negativo");
                    continue;
                }
                return numeroValido;
            }
            catch(java.lang.NumberFormatException e) {
                System.out.println("Erro. Digite somente números");
            }
            catch (Exception e) {
                System.out.println("Erro Inesperado. Tente novamente: "+e);
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
            System.out.println("Digite a porcentagem de comissão | 'Enter' para cancelar");
            String porcentagemValida = input.nextLine().trim();

            if (porcentagemValida.isEmpty()){
                return Float.NaN;
            }
            porcentagemValida = porcentagemValida.replace(",", ".");

            try {
                Float numeroValido = Float.parseFloat(porcentagemValida);

                if (numeroValido<0){
                    System.out.println("ERRO. Não pode existir comissão negativa");
                    continue;
                }
                return numeroValido/100;
            }
            catch(java.lang.NumberFormatException e) {
                System.out.println("Erro. Digite somente números");
            }
            catch (Exception e) {
                System.out.println("Erro Inesperado. Tente novamente: "+e);
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
            System.out.println("Digite quantidade de peças produzidas | 'Enter' para cancelar");
            String qtProduzida = input.nextLine().trim();
    
            if (qtProduzida.isEmpty()){
                return -1;
            }
            try {
                Integer numeroValido = Integer.parseInt(qtProduzida);
    
                if (numeroValido<0){
                    System.out.println("ERRO. Não pode existir quantidade produzida negativa");
                    continue;
                }
                return numeroValido;
            }
            catch(java.lang.NumberFormatException e) {
                System.out.println("Erro. Digite somente números inteiros");
            }
            catch (Exception e) {
                System.out.println("Erro Inesperado. Tente novamente: "+e);
            }
            
        }

    }



    private static void mostraFolhaPgto(){
        // se estiver vazio mostrar zerado

        if (listaFuncionario.isEmpty()){
            System.out.println("Nenhum funcionário cadastrado");
        } else{
            
            System.out.println("Funcionários cadastrados: "+listaFuncionario.size());
            double totalFuncionarioPadrao=0;
            double totalComissao=0;
            double totalFuncionarioVendedor=0;
            double totalProdutividade=0;
            double totalFuncionarioOperador=0;
            double totalFolhaPgto=0;

            for (Funcionario funcionario : listaFuncionario){   
                System.out.println("==================================");
                System.out.println("Nome: "+funcionario.getNomeCompleto());
                System.out.println("Matrícula: "+funcionario.getMatricula());
                System.out.println("Salário Fixo: "+String.format("%,.2f", funcionario.getSalarioFixo()));
                switch (funcionario.tipo) {
                    case "base":
                        System.out.println("Extras: 0.00"); 
                        System.out.println("Salario Final: "+ String.format("%,.2f", funcionario.getSalarioFixo()));
                        totalFuncionarioPadrao +=funcionario.getSalarioFixo();
                        System.out.println("----------------------------------");
                        break;
                    case "vendedor":
                        System.out.println("Comissão: " + String.format("%,.2f", funcionario.getExtras()));
                        System.out.println("Salario Final: "+ String.format("%,.2f", funcionario.getSalarioFinal()));
                        totalComissao += funcionario.getExtras();
                        totalFuncionarioVendedor += funcionario.getSalarioFinal();                        
                        break;
                    case "operacao":
                        System.out.println("Produtividade: " + String.format("%,.2f", funcionario.getExtras()));
                        System.out.println("Salario Final: "+ String.format("%,.2f", funcionario.getSalarioFinal()));
                        totalProdutividade += funcionario.getExtras();
                        totalFuncionarioOperador += funcionario.getSalarioFinal();                        
                        break;

                }
            }
            totalFolhaPgto = totalFuncionarioPadrao + totalFuncionarioVendedor + totalFuncionarioOperador;
            System.out.println("==================================");
            System.out.println("Total Comissão: " + String.format("%,.2f", totalComissao));
            System.out.println("Total Produtividade: " + String.format("%,.2f", totalProdutividade));

            System.out.println("Subtotal Funcionário Padrão: " + String.format("%,.2f", totalFuncionarioPadrao));
            System.out.println("Subtotal Funcionário Vendedor: " + String.format("%,.2f", totalFuncionarioVendedor));
            System.out.println("Subtotal Funcionário Operador: " + String.format("%,.2f", totalFuncionarioOperador));
            System.out.println("Total Folha Pagamento: " + String.format("%,.2f", totalFolhaPgto));
            
            System.out.println("==================================");
            System.out.println("==================================");
        }
    }

    private static void mostrarTelaLogin(){
        System.out.println("==================================");
        System.out.println("FOLHA DE PAGAMENTO");
        System.out.println("==================================");
        System.out.println("Digite uma das opções abaixo: ");
        System.out.println("1. Cadastrar funcionário padrão");
        System.out.println("2. Cadastrar funcionário vendedor");
        System.out.println("3. Cadastrar funcionário operação");
        System.out.println("4. Verificar folha de pagamento");
        System.out.println("0. Sair");
    }
}
