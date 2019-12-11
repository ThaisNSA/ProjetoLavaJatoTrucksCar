package teste;

import dados.entidades.Cliente;
import excecoes.ValorInvalidoException;
import javax.persistence.EntityManager;
import util.JPAUtil;

public class EditaCliente {
    
    public static void main(String[] args) throws ValorInvalidoException {
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Buscando um cliente pelo id (segundo parametro)
        Cliente c = gerenciador.find(Cliente.class, 1);
        
        //Editando o nome (colocando um novo nome)
        c.setNome("Jonas");
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
        //fechar o gerenciador
        gerenciador.close();
        
    }
    
}
