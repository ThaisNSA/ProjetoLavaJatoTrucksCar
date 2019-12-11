
package ui.ClientesNaoCadastrados;

import com.jfoenix.controls.JFXTextField;
import dados.entidades.Cliente;
import excecoes.ValorInvalidoException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import servicos.ClienteServico;
import util.AlertaUtil;
/**
 * FXML Controller class
 *
 * @author Pandora(SSQ)
 */
public class CadastrarClientesController implements Initializable {

    
    @FXML
    private JFXTextField tfID;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfCpfCnpj;
    @FXML
    private JFXTextField tfEndereco;
    
 //Atributo para representar o servico
    private ClienteServico servico = new ClienteServico();

    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<?, ?> C1ID;
    @FXML
    private TableColumn<?, ?> C2Nome;
    @FXML
    private TableColumn<?, ?> C3CpfCnpj;
    @FXML
    private TableColumn<?, ?> C4Email;
    @FXML
    private TableColumn<?, ?> C5Telefone;
    @FXML
    private TableColumn<?, ?> C6Endereco;
   
    
    //Atributo que representa os dados para tabela
    private ObservableList<Cliente> dados
            = FXCollections.observableArrayList();

    //Atributo que vai armazenar qual o cliente 
    //foi selecionado na tabela
    private Cliente selecionado;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        configurarTabela();

        //Carregue a lista de clientes na tabela
        listarClientesTabela();
    }    

    @FXML
    private void salvar(ActionEvent event) throws ValorInvalidoException {
        
       // System.out.println("teste1");
        
         //Verificar se está atualizando ou inserindo
        if(tfID.getText().isEmpty()){ //inserindo
            
        
            try{
            
           // System.out.println("teste");
            //Pega os dados do fomulário
            //e cria um objeto cliente
            Cliente c = new Cliente();
            c.setNome(tfNome.getText());
            c.setCpfCnpj(tfCpfCnpj.getText());
            c.setEmail(tfEmail.getText());
            c.setTelefone(tfTelefone.getText());
            c.setEndereco(tfEndereco.getText());
            
            //Mandar o cliente para a camada de servico
            servico.salvar(c);
            
            
            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Cliente salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            listarClientesTabela();
            }
             catch(ValorInvalidoException ex){
                AlertaUtil.mensagemErro(ex.getMessage());
             }
            
        }else{ //atualizando o cliente
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
           
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                try{
                //Pegar os novos dados do formulário e
                //atualizar o meu cliente
                
        
                selecionado.setNome(tfNome.getText());
                selecionado.setCpfCnpj(tfCpfCnpj.getText());
                selecionado.setEmail(tfEmail.getText());
                selecionado.setTelefone(tfTelefone.getText());
                selecionado.setEndereco(tfEndereco.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                servico.editar(selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Cliente atualizado com sucesso!"); 
                
              //Chama o metodo para atualizar a tabela
               listarClientesTabela();
                }
                 catch(ValorInvalidoException ex){
                AlertaUtil.mensagemErro(ex.getMessage());
                 }
                
           }
           
       }

        //Limpando os campos do form
       limparCampos();
        
    }
    private void limparCampos(){
        
        //Limpando o form
        
        tfID.setText("");
        tfNome.setText("");
        tfCpfCnpj.setText("");
        tfEmail.setText("");
        tfTelefone.setText("");
        tfEndereco.setText("");
    }
    
    /**
     * Fazendo configuração das colunas da tabela
     */
    private void configurarTabela() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        C1ID.setCellValueFactory(
                new PropertyValueFactory("id_cadastro"));
        C2Nome.setCellValueFactory(
                new PropertyValueFactory("nome"));
        C3CpfCnpj.setCellValueFactory(
                new PropertyValueFactory("cpfCnpj"));
        C4Email.setCellValueFactory(
                new PropertyValueFactory("email"));
        C5Telefone.setCellValueFactory(
                new PropertyValueFactory("Telefone"));
        C6Endereco.setCellValueFactory(
                new PropertyValueFactory("endereco"));
          

    }//configurarTabela

    /**
     * Responsável por carregar a lista de atores na tabela
     */
    private void listarClientesTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de clientes
        List<Cliente> clientes = servico.listar();

        //Transformar a lista de clientes no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(clientes);

        //Jogando os dados na tabela
        tabela.setItems(dados);

    }


    @FXML
    private void editar(ActionEvent event) {
    
         //Pegar o acliente que foi selecionado na tabela
        selecionado = tabela.getSelectionModel().getSelectedItem();

        //Se tem algum cliente selecionado
        if (selecionado != null) { //tem ator selecionado
            //Pegar os dados do ator e jogar nos campos do
            //formulario
            tfID.setText(
                    String.valueOf( selecionado.getId_cadastro() ) );
            
            tfNome.setText( selecionado.getNome() );
            tfCpfCnpj.setText( selecionado.getCpfCnpj() );
            tfEmail.setText( selecionado.getEmail() );
            tfTelefone.setText( selecionado.getTelefone() );
            tfEndereco.setText( selecionado.getEndereco() );
            
        }else{ //não tem cliente selecionado na tabela
            AlertaUtil.mensagemErro("Selecione um Cliente.");
        }

    }
    

    @FXML
    private void excluir(ActionEvent event) {
    
         //Pegar o cliente que foi selecionado na tabela
        selecionado = tabela.getSelectionModel()
                .getSelectedItem();
        
        //Verifico se tem cliente selecionado
        if(selecionado != null){ //existe cliente selecionado
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
            //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                servico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Cliente excluído com sucesso");
                
                //Atualizar a tabela
                listarClientesTabela();              
                
            }
            
            
            
        }else{
            AlertaUtil.mensagemErro("Selecione um cliente.");
        }
        
    }




    @FXML
    private void agendar(ActionEvent event) throws IOException {
        
 //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/ClienteCadastrado/AgendarClientes.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Agendamento");
        //Adicionando a cena na janela
        stage.setScene(scene);
        
        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);
        
        //Mostrando a nova janela
        stage.show();
                
        
    }



   
    
}