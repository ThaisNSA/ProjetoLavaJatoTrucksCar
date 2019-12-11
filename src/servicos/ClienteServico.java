package servicos;

import dados.daos.ClienteDAO;
import dados.entidades.Cliente;
import java.util.List;

public class ClienteServico {
    
    //Atributo para representar a camada de dados
    private ClienteDAO dao = new ClienteDAO();
    
    public void salvar(Cliente c){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(c);
        
    }
    
    
    /**
     * Solicita a camada DAO para buscar os clientes
     * cadastrados
     * @return 
     */
    public List<Cliente> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe um cliente e manda para a camada DAO atualizar 
     */
    public void editar(Cliente c){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(c);
        
    }
    
    /**
     *  Recebe um cliente para passar para a DAO excluir no BD
     */
    public void excluir(Cliente c){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(c);
    }
    
     
    
}