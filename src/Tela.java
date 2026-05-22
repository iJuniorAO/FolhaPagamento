public class Tela {

    public static void MostrarOpcoes(){
        System.out.println("=".repeat(91));
        System.out.printf("|%31s %s %30s|\n", "", "SISTEMA FOLHA DE PAGAMENTO", "");
        System.out.printf("+"+"-".repeat(89)+"+\n");
        System.out.printf("| %-87s |\n","1. Cadastrar Funcionário Padrão");
        System.out.printf("| %-87s |\n","2. Cadastrar Funcionário Vendedor");
        System.out.printf("| %-87s |\n","3. Cadastrar Funcionário Operação");
        System.out.printf("| %-87s |\n","4. Verificar Folha de Pagamento");
        System.out.printf("| %-87s |\n","0. Sair");
        System.out.println("=".repeat(91));
    }

    public static void MostrarErro(String mensagemErro){
        System.out.printf("\n".repeat(2));
        System.out.println("╔"+"═".repeat(89)+"╗");
        System.out.printf("║ %41s %s %40s ║\n","","ERRO","");
        System.out.println("╠"+"═".repeat(89)+"╣");
        System.out.printf(" %s %s \n","",mensagemErro,"");
        System.out.println("═".repeat(91));
        System.out.printf("\n\n");
    }

    public static void MostrarAlerta(String mensagemAlerta){
        
        System.out.printf("\n".repeat(2));
        System.out.println("┌"+"─".repeat(89)+"┐");
        System.out.printf("│ %40s %s %39s │\n","","ALERTA","");
        System.out.println("├"+"─".repeat(89)+"┤");
        System.out.printf(" %s %s \n","",mensagemAlerta,"");
        System.out.println("─".repeat(91));
        System.out.printf("\n\n");
    }

    public static void MostrarHeader(String mensagem) {

        System.out.println("=".repeat(91));
        System.out.printf(" %20s %s %20s \n", "",mensagem,"");
        System.out.printf("-".repeat(91)+"\n| ");
    }
    public static void MostrarSubheader(String mensagem) {

        System.out.println("-".repeat(91));
        System.out.printf(" %20s %s %20s \n", "",mensagem,"");
        System.out.printf("-".repeat(91)+"\n| ");
    }

    public static void GeraFolhaZeroFuncionario(){
        System.out.printf("=".repeat(91));
        System.out.printf("\n| "+" ".repeat(29)+"Nenhum Funcionário Cadastrado"+" ".repeat(29)+" |\n");
        System.out.println("=".repeat(91)+"\n\n");
    }


    public static void GeraFolhaHeader(int QtdFuncionario){
        
        System.out.printf("\n===========================================================================================\n");
        System.out.printf("| %-26s %-60s |\n", "Funcionários Cadastrados: ",QtdFuncionario);
        System.out.printf("===========================================================================================\n");
        System.out.printf(
            "| %-20s | %-10s (%4s)| %-13s |  %-12s | %-13s |\n",
            "Nome Completo", "Matrícula", "TIPO", "Salário Fixo", "Bônus", "Salário Final"
        );
        System.out.printf("|----------------------|------------------|---------------|---------------|---------------|\n");
    }
    public static void GeraFolhaBody(String nomeCompleto, String matricula, String tipo, double salarioFixo, double extra, double salarioFinal){
        System.out.printf(
            "| %-20s | %-10s (%4s)| R$ %-,10.2f | R$ %-,10.2f | R$ %,10.2f | \n",
            nomeCompleto,
            matricula,
            tipo,
            salarioFixo,
            extra,
            salarioFinal
        );
    }            
    public static void GeraFolhaFooter(double totalComissao, double totalProdutividade, double totalFuncionarioPadrao, double totalFuncionarioVendedor, double totalFuncionarioOperador, double totalFolhaPgto){

        System.out.printf("-".repeat(91)+"\n");
        System.out.printf("| %-58s %6.2f %% %8s %,10.2f | \n", "Subtotal Comissão:",totalComissao/totalFolhaPgto*100, "R$",totalComissao);
        System.out.printf("| %-58s %6.2f %% %8s %,10.2f | \n", "Subtotal Produtividade:",totalProdutividade/totalFolhaPgto*100, "R$",totalProdutividade);
        System.out.printf("| %-58s %6.2f %% %8s %,10.2f | \n", "Subtotal Funcionário Padrão:",totalFuncionarioPadrao/totalFolhaPgto*100, "R$",totalFuncionarioPadrao);
        System.out.printf("| %-58s %6.2f %% %8s %,10.2f | \n", "Subtotal Funcionário Vendedor:",totalFuncionarioVendedor/totalFolhaPgto*100, "R$",totalFuncionarioVendedor);
        System.out.printf("| %-58s %6.2f %% %8s %,10.2f | \n", "Subtotal Funcionário Operador:",totalFuncionarioOperador/totalFolhaPgto*100, "R$",totalFuncionarioOperador);
        System.out.printf("=".repeat(91)+"\n");
        System.out.printf("| %-58s %6.2f %% %8s %,10.2f | \n", "Total Folha de Pagamento: ",totalFolhaPgto/totalFolhaPgto*100, "R$",totalFolhaPgto);            
        System.out.printf("=".repeat(91)+"\n\n\n");
    }



    

    
}