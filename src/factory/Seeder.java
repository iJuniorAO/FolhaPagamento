package factory;

import java.util.ArrayList;
import model.Funcionario;
import model.FuncionarioOperacao;
import model.FuncionarioVendedor;


public class Seeder {


        // instancia 2 objetos de cada classe para testes
        public static void carregarDadosDemonstracao(ArrayList<Funcionario> lista){
                
                Funcionario f = new Funcionario("123456","Ismael Junior");
                lista.add(f);
        
                FuncionarioVendedor v = new FuncionarioVendedor("654321", "Fulano Henrique", 1680.65, 0.1f);
                lista.add(v);
        
                FuncionarioOperacao o = new FuncionarioOperacao("123789", "Ciclano Alves", 1400, 0.15);
                lista.add(o);
                
                
                Funcionario f2 = new Funcionario("123987","Qualquer Nome");
                lista.add(f2);
                
                FuncionarioVendedor v2 = new FuncionarioVendedor("741258", "Alguem Silva", 5320.51, 0.5f);
                lista.add(v2);
                
                FuncionarioOperacao o2 = new FuncionarioOperacao("963258", "Desenvolvedor Java", 1150, 0.13);
                lista.add(o2);

        }
}
