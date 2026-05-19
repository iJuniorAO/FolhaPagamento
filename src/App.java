
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<String> listaFuncionario = new ArrayList<>();
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
                    System.out.println("Digite o nome completo | campo vazio para cancelar");
                    
                    nome = validaInputNome();
                    
                    System.out.println("Digite o número de matrícula | campo vazio para cancelar");
                    matricula = validaInputMatricula();

                    if ((nome!=" ") && (matricula!=" ")){
                        Funcionario funcionarioCadastrado = new Funcionario(matricula, nome);
                        listaFuncionario.add(matricula);
                    }

                    cadastraFuncionario(sair);
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
                System.out.println("Opção Inválida. Digite somente números de 0 a 4");
                return -1;
            }
            return numeroValido;
        }catch(java.lang.NumberFormatException e) {
            System.out.println("Opção Inválida. Digite somente números");
            return -1;

        } catch (Exception e) {
            System.out.println("Erro Inesperado. Tente novamente: "+e);
            return -1;
        }

    }

    private static String validaInputNome(){
        do { 
            String nome = input.nextLine();
            
            if (nome.equals(" ")){
                return nome;
            }
            
            nome = nome.trim();
            
            // validar se não tem número

            if (! nome.contains(" ")){
                System.out.println("Inválido. Necessário preencher nome Completo");
            }else{
                return nome;
            }
        } while (true);
    }
    
    private static String validaInputMatricula(){
        do { 
           String matricula = input.nextLine();
            if (matricula.equals(" ")){
                // TODO validacao se matricula é unico
                return matricula;
            }else{
                return matricula;
            }
        } while (true);
    }
    
    private static void cadastraFuncionario(int tipoFuncionario){
        String nome;
        String matricula;
        System.out.println("==================================");
        System.out.println("Cadastro Funcionário");
        System.out.println("Digite o nome completo | campo vazio para cancelar");
        
        nome = input.nextLine();
        if (validaNome(nome)){
            System.out.println("Cancelando...");
            return;
        }

        System.out.println("Digite o número de matrícula | campo vazio para cancelar");
        matricula = input.nextLine();
        if (matricula.equals(""))
            // TODO validacao se matricula é unico
            return;
        System.out.println(matricula + " " + nome + " Adicionado");

        Funcionario funcionarioCadastrado = new Funcionario(matricula, nome);
        listaFuncionario.add(matricula);
    }

    private static boolean validaNome(String nome){
        // validaNome possui lógica invertida(erro), se tudo estiver ok return False

        // repetição, somente cancelar quando input zerado, enquanto usuário tentar não cancelar
        // valida se tem numero
        
        if (nome.equals("")){
            System.out.println("Cancelando...");
            return true;
        }        
        
        nome = nome.trim();
        if (! nome.contains(" ")){
            System.out.println("Inválido. Necessário preencher nome Completo");
            return true;
        }
    
        return false;
    }

    private static void mostraFolhaPgto(){
        // se estiver vazio mostrar zerado

        for (String funcionario : listaFuncionario){

            System.out.println(funcionario);
            
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
