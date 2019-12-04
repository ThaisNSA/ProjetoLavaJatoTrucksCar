
package dados.daos;


import dados.entidades.Agendamento;
import dados.entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Pandora(SSQ)
 */
public class AgendamentoDAO {
    
     public List<Agendamento> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select a from Agendamento a", Agendamento.class);

        //Retornar a lista de clientes
        return consulta.getResultList();

    }

    /**
     * Manda salvar o agendamento no BD
     *
     */
    public void salvar(Agendamento a) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o cliente
        gerenciador.persist(a);

        //Commit
        gerenciador.getTransaction().commit();

    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Agendamento a) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(a);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    /**
     * Exclui o agendamento do BD
     */
    public void excluir(Agendamento a) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o cliente que foi
        //selecionado na tela
        a = gerenciador.merge(a);

        //Mandar sincronizar as alterações 
        gerenciador.remove(a);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    public List<Agendamento> buscarPeloCliente(String cliente) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery<Agendamento> consulta = gerenciador.createQuery(
                "Select a from Agendamento a where a.cliente like :cliente",
                Agendamento.class);

        //Substituindo o parametro :cliente pelo valor da variavel c
        consulta.setParameter("cliente", cliente + "%");

        //Retornar os dados
        return consulta.getResultList();

    }
    
    public void incluirClienteNoAgendamento(Agendamento agendamento, Cliente cliente) {
    
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //sincronizar com o BD para tornar o status managed
        agendamento = gerenciador.merge(agendamento);

        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }

    /*public void removerAtorDoFilme(FilmeAtor filmeAtor) {
        
        Filme filme = filmeAtor.getFilme();
        Ator ator = filmeAtor.getAtor();
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //sincronizar com o BD para tornar o status managed
        filme = gerenciador.merge(filme);
        ator = gerenciador.merge(ator);
        
        //Adicionar o ator
        filme.getAtores().remove(ator);

        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }*/

}


