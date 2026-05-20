
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
                            break;
                        case 2:
                            System.out.println("Digite o valor total vendido: | campo vazio para cancelar");
                            double valorVendas = input.nextDouble();
                            
                            System.out.println("Digite a porcentagem de comissão (0~100): | campo vazio para cancelar");
                            double comissao = input.nextDouble();
                            comissao /= 100;
                            
                            FuncionarioVendedor FuncionarioVendas = new FuncionarioVendedor(matricula, nome, valorVendas, comissao);
                            listaFuncionario.add(FuncionarioVendas);
                            break;
                        case 3:
                            System.out.println("Digite o unitário da peça: | campo vazio para cancelar");
                            double valorUnitario = input.nextDouble();
                            
                            System.out.println("Digite qtd de peças produzidas: | campo vazio para cancelar");
                            int quantidadePecaProduzida = input.nextInt();
                            
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
        //  return "-1" se invalido
        //  return " " para cancerlar loop
        //  return nome se validado
        // do { 
        //     boolean erro=false;
        //     System.out.println("Digite o nome completo | campo vazio para cancelar");
        //     String nome = input.nextLine();
            
        //     if (nome.equals(" ")){
        //         return " ";
        //     }
        //     if (! nome.contains(" ")){
        //         System.out.println("Inválido. Necessário preencher nome Completo");
        //         erro=true;
        //     }
        //     nome = nome.trim();
        //     for (char c : nome.toCharArray()){
        //         if (Character.isDigit(c)){
        //             System.out.println("Inválido. Nome não pode conter número");
        //             erro = true;
        //             break;
        //         }
        //     }
        //     if (erro==false)
        //         return nome;
        // } while(true);
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
        //  return "-1" se invalido
        //  return " " para cancerlar loop
        //  return matricula se validado
        // if (matricula.equals(" ")){
        //     return " ";
        // }
        // for (Funcionario funcionario : listaFuncionario){
        //     if(funcionario.getMatricula().equals(matricula)){
        //         System.out.println("Inválido. Matrícula já utilizada");
        //         return "-1";
        //     }
        // }
        // // TODO validacao se matricula é unico

        // return matricula;
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
                System.out.println("Salário Fixo: "+funcionario.getSalarioFixo());
                switch (funcionario.tipo) {
                    case "base":
                        System.out.println("Extras: 0.00");                        
                        System.out.println("Salario Final: "+ funcionario.getSalarioFixo());
                        totalFuncionarioPadrao +=funcionario.getSalarioFixo();
                        totalFolhaPgto += funcionario.getSalarioFixo();                        
                        break;
                    // case "vendedor":
                    //     System.out.println("Comissão: "+String.format("%.2f", funcionario.getExtras()));
                    //     System.out.println("Salario Final: "+ funcionario.getSalarioFinal());                        
                    //     break;
                    //     case "operacao":
                    //         System.out.println("Produtividade: "+String.format("%.2f", funcionario.getExtras()));
                    //         System.out.println("Salario Final: "+ funcionario.getSalarioFinal());                        
                    //     break;
                }
            }
            System.out.println("==================================");
            System.out.println("Subtotal Funcionário Padrão: " + totalFuncionarioPadrao);
            System.out.println("Total Folha Pagamento: " + totalFolhaPgto);
            System.out.println("==================================");
            System.out.println("==================================");
        }
    }

    private static void mostrarTelaLogin(){
        System.out.println("==================================");
        System.out.println("FOLHA DE PAGAMENTO");
        System.out.println("==================================");
        System.out.println("Digite uma das opções abaixo: ");
        System.out.println("1. Cadastrar funcionário");
        System.out.println("2. Cadastrar funcionário vendedor");
        System.out.println("3. Cadastrar funcionário operação");
        System.out.println("4. Verificar folha de pagamento");
        System.out.println("0. Sair");
    }
}
