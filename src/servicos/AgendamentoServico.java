
package servicos;

import dados.daos.AgendamentoDAO;
import dados.entidades.Agendamento;
import dados.entidades.Cliente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Pandora(SSQ)
 */
public class AgendamentoServico {
    
    
    //Atributo para representar a camada de dados
    private AgendamentoDAO dao = new AgendamentoDAO();
    
    
    /**
     * Solicita a camada DAO para buscar os clientes
     * cadastrados
     * @return 
     */
    public List<Agendamento> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe o agendamento e manda para a camada DAO salvar
     * no BD
     */
    public void salvar(Agendamento a){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Manda a camada DAO salvar no BD
        dao.salvar(a);
        
    }
    
    /**
     * Recebe o agndamento e manda para a camada DAO atualizar 
     */
    public void editar(Agendamento a){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(a);
        
    }
    
    /**
     *  Recebe um agendamento para passar para a DAO excluir no BD
     */
    public void excluir(Agendamento a){
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(a);
    }
    
    public List<Agendamento> buscarPelaDataAgendamento(LocalDate dataAgendamento){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO buscar os clientes pelo nome
        return dao.buscarPelaDataAgendamento(dataAgendamento);
    }
    
   

    public void incluirClienteNoAgendamento(Agendamento agendamento, Cliente cliente) {
    
        //Mandar a DAO atualizar os dados no BD
        dao.incluirClienteNoAgendamento(agendamento, cliente);
    }        

//    public List<Agendamento> buscarPelaDataAgendamento(String dataAgendamento) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public void excluir(Cliente selecionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
    


    

