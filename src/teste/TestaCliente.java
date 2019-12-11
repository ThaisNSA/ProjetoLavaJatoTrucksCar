package teste;

import dados.entidades.Cliente;
import excecoes.ValorInvalidoException;
import javax.persistence.*;
import util.JPAUtil;

public class TestaCliente {
    
    public static void main(String[] args) throws ValorInvalidoException {
        
        //Criando um objeto cliente
        Cliente c1 = new Cliente();
        c1.setNome("Luiz");
        c1.setCpfCnpj("00001000000");
        c1.setTelefone("998570579");
        c1.setEmail("luiz@gmail.com");
        c1.setEndereco("R a N22, B Jardim");
        
        Cliente c2 = new Cliente();
        c2.setNome("Juliano");
        c2.setCpfCnpj("00002000000");
        c2.setTelefone("999477634");
        c2.setEmail("juliano@gmail.com");
        c2.setEndereco("R b N33, B Enceada");
        
        Cliente c3 = new Cliente();
        c3.setNome("Glauber");
        c3.setCpfCnpj("00002000000");
        c3.setTelefone("999477335");
        c3.setEmail("Glauber@gmail.com");
        c3.setEndereco("R c N44, B Centro");
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandando persistir o objeto
        gerenciador.persist(c1);
        gerenciador.persist(c2);
        gerenciador.persist(c3);
        
        //Finalizo a transação
        gerenciador.getTransaction().commit();
        
        //Fechar o gerenciador
        gerenciador.close();
        
    }
    
}
