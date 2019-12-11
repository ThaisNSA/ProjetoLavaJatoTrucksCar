/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ClienteCadastrado;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dados.entidades.Agendamento;
import dados.entidades.Cliente;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import servicos.AgendamentoServico;
import servicos.ClienteServico;
import util.AlertaUtil;
import java.time.LocalDate;
import static java.time.LocalDate.parse;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author Pandora(SSQ)
 */
public class AgendarClientesController implements Initializable {

    @FXML
    private JFXTextField tfID;
    @FXML
    private Label labelPorteDoAutomovel;
    @FXML
    private Label labelTipoDeLavagem;
    @FXML
    private Label labelDataDoAgendamento;
    @FXML
    private JFXDatePicker dateAgendamento;
    @FXML
    private JFXTimePicker timeAgendamento;
    @FXML
    private JFXTextArea taValor;
    
      //Atributos para representar os servicos
    private AgendamentoServico agendamentoServico = new AgendamentoServico();
    private ClienteServico servico = new ClienteServico();
    

    @FXML
    private TableView<Agendamento> tabela;
    @FXML
    private TableColumn<?, ?> c1ID;
    @FXML
    private TableColumn<?, ?> c2Cliente;
     @FXML
    private TableColumn<?, ?> c3PorteDoAutomovel;
    @FXML
    private TableColumn<?, ?> c4TipoDeLavagem;
    @FXML
    private TableColumn<?, ?> c5DataDoAgendamento;
    @FXML
    private TableColumn<?, ?> c6Valor;
     @FXML
    private TableColumn<?, ?> c7HoraDoAgendamento;
     
     //Atributo que representa os dados para tabela
    private ObservableList<Agendamento> dados
            = FXCollections.observableArrayList();

    //Atributo que vai armazenar qual o cliente 
    //foi selecionado na tabela
    private Agendamento selecionado;
    @FXML
    private JFXComboBox<String> cbPorteDoAutomovel;
    @FXML
    private JFXComboBox<String> cbTipoDeLavagem;
    @FXML
    private JFXComboBox<Cliente> cbCliente;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          //Configure a tabela
        configurarTabela();

        //Carregue a lista de clientes na tabela
        listarAgendamentoTabela();
        
        listarClientes();
        
         //Configurar os elementos que vao aparecer no 
        //combobox
        
        //Criar uma Lista JavaFX de String para
        //colocar no combobox
        ObservableList lista1 = FXCollections.observableArrayList();
        lista1.add("Pequeno");
        lista1.add("Médio");
        lista1.add("Grande");
        
        //Adicionar a lista no comboBox
        cbPorteDoAutomovel.setItems(lista1);
        
       
        
       
        
        //Criar uma Lista JavaFX de String para
        //colocar no combobox
        ObservableList lista2 = FXCollections.observableArrayList();
        lista2.add("Ducha Rápida");
        lista2.add("Ducha Completa");
        lista2.add("Lavagem Completa com Motor");
        
        //Adicionar a lista no comboBox
        cbTipoDeLavagem.setItems(lista2);
        
         
    }   
    
    private void configurarTabela() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        c1ID.setCellValueFactory(
                new PropertyValueFactory("id_Agendamento"));
        c2Cliente.setCellValueFactory(
                new PropertyValueFactory("cliente"));
        c3PorteDoAutomovel.setCellValueFactory(
                new PropertyValueFactory("porteDoAutomovel"));
        c4TipoDeLavagem.setCellValueFactory(
                new PropertyValueFactory("tipoDeLavagem"));
        c5DataDoAgendamento.setCellValueFactory(
                new PropertyValueFactory("dataAgendamento"));
        c6Valor.setCellValueFactory(
                new PropertyValueFactory("valor"));
        c7HoraDoAgendamento.setCellValueFactory(
                new PropertyValueFactory("horaAgendamento"));

    }
    
      private void listarAgendamentoTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de clientes
        List<Agendamento> agendamento = agendamentoServico.listar();
        
        //  System.out.println("Size: " + agendamento.size());
        
        //Transformar a lista de clientes no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(agendamento);

        //Jogando os dados na tabela
        tabela.setItems(dados);

    }

    @FXML
    private void cbPorteDoAutomovel(ActionEvent event) {
    }

    @FXML
    private void cbTipoDeLavagem(ActionEvent event) {
    }
    
     private void listarClientes() {

        List<Cliente> clientes = servico.listar();

        cbCliente.setItems(FXCollections.observableArrayList(clientes));
     }


    @FXML
    private void salvar(ActionEvent event) {
         //Verificar se está atualizando ou inserindo
        if (tfID.getText().isEmpty()) { //inserindo

            //Criando o objeto agendamento
            Agendamento a = new Agendamento(
                    cbCliente.getValue(),
                    cbPorteDoAutomovel.getValue(),
                    cbTipoDeLavagem.getValue(),
                    dateAgendamento.getValue(),
                    timeAgendamento.getValue(),
                    new BigDecimal(taValor.getText()));
                    
                    
            
            //Mandando para a camada de serviço salvar
            agendamentoServico.salvar(a);

            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Agendamento salvo com sucesso!");

            //Carregando lista de agendamentos
            listarAgendamentoTabela();

        } else { //atualizando

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e
                //atualizar o agendamento
                selecionado.setCliente(cbCliente.getValue());
                selecionado.setValor(new BigDecimal(taValor.getText()));
                selecionado.setDataAgendamento(dateAgendamento.getValue());
                selecionado.setHoraAgendamento(timeAgendamento.getValue());
                selecionado.setTipoDeLavagem(cbTipoDeLavagem.getValue());
                selecionado.setPorteDoAutomovel(cbPorteDoAutomovel.getValue());
                //Mandando para a camada de serviço salvar as alterações
                agendamentoServico.editar(selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Agendamento atualizado com sucesso!");
                
                //Carregando lista 
                listarAgendamentoTabela();
            }
            
        }

        //Limpando o form
        limparCampos();
    }
    /**
     * Limpa os campos do formulário
     */
    private void limparCampos() {
        tfID.setText("");
        cbCliente.setValue(null);
        taValor.setText("");
        dateAgendamento.setValue(null);
        timeAgendamento.setValue(null);
    }
    
     @FXML
    private void editar(ActionEvent event) {
        
        //Pegar o agendamento selecionado na tabela 
        selecionado = tabela.getSelectionModel().getSelectedItem();

        //Se tem algum agendamento selecionado
        if (selecionado != null) {
            
            //Pega os dados do agendamento e joga no formulário
            tfID.setText(String.valueOf(selecionado.getId_Agendamento()));
            cbCliente.setValue(selecionado.getCliente());
            taValor.setText(selecionado.getValor().toString());
            dateAgendamento.setValue(selecionado.getDataAgendamento());
            timeAgendamento.setValue(selecionado.getHoraAgendamento());
            cbPorteDoAutomovel.setValue(selecionado.getPorteDoAutomovel());
            cbTipoDeLavagem.setValue(selecionado.getTipoDeLavagem());
            
        }else{//não selecionou agendamento na tabela
            AlertaUtil.mensagemErro("Selecione um Clinte.");
            
            
        }
    }

    @FXML
    private void excluir(ActionEvent event) {
        
        //Pegar o agendamento selecionado na tabela 
        selecionado = tabela.getSelectionModel().getSelectedItem();
        
        //Se tem algum agendamento selecionado
        if (selecionado != null) {
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
             //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                agendamentoServico.excluir(selecionado);
                
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Agendamento excluído com sucesso");
                
                //Carregando lista de agendamento
                listarAgendamentoTabela();
            }
        
        }
    }
    
    @FXML
    private void pesquisar(ActionEvent event) {
        
         //Limpando quaisquer dados anteriores
        dados.clear();

        //Pegando o cliente que a pessoa deseja pesquisar
        LocalDate dataAgendamento = dateAgendamento.getValue();
        
        
        //Solicitando a camada de servico a lista de clientes
        List<Agendamento> agendamento = agendamentoServico.buscarPelaDataAgendamento(dataAgendamento);

        //Transformar a lista de clientes no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(agendamento);

        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }

    }
    
    
