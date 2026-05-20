
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
            // sair = 1; // TODO bypass das opções

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
                    do { 
                        System.out.println("Digite o nome completo | campo vazio para cancelar");
                        
                        nome = validaInputNome(input.nextLine());
                    } while (nome.equals("-1"));

                    if (nome.equals(" ")){
                        System.out.println("Cancelando Cadastro...");
                        continue;
                    }
                    do { 
                        System.out.println("Digite o número de matrícula | campo vazio para cancelar");
                        matricula = validaInputMatricula(input.nextLine());
                        
                    } while (matricula.equals("-1"));
                    
                    if (nome.equals(" ")){
                        System.out.println("Cancelando Cadastro...");
                        continue;
                    }

                    Funcionario NovoFuncionario = new Funcionario(matricula, nome);
                    listaFuncionario.add(NovoFuncionario);

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

    private static String validaInputNome(String nome){
        //  return "-1" se invalido
        //  return " " para cancerlar loop
        //  return nome se validado
        if (nome.equals(" ")){
            System.out.println("ESPAÇO");
            return " ";
        }
        nome = nome.trim();
        // TODO validar se não tem número
        
        if (! nome.contains(" ")){
            System.out.println("Inválido. Necessário preencher nome Completo");
            return "-1";
        }else{
            return nome;
        }
    }
    
    private static String validaInputMatricula(String matricula){
        //  return "-1" se invalido
        //  return " " para cancerlar loop
        //  return matricula se validado
        if (matricula.equals(" ")){
            return " ";
        }
        for (Funcionario funcionario : listaFuncionario){
            if(funcionario.getMatricula().equals(matricula)){
                System.out.println("Inválido. Matrícula já utilizada");
                return "-1";
            }
        }
        // TODO validacao se matricula é unico

        return matricula;
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
