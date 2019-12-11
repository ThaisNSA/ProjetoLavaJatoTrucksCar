package dados.entidades;


import excecoes.ValorInvalidoException;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {

    
    private String email;
    private String telefone;
    private String endereco;
            
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cadastro;
    private String cpfCnpj;
    private String nome;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public Cliente(){}
    
    //Construtor
    public Cliente(String n) throws ValorInvalidoException{
        this.setNome(n);
   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) 
            throws ValorInvalidoException {
        
        if(!nome.matches("[\\p{L}\\s]+")){
            throw new ValorInvalidoException("Nome Inválido! São aceitos apenas letras e espaços.");
        }
        this.nome = nome;
    }

    public Integer getId_cadastro() {
        return id_cadastro;
    }

    public void setId_cadastro(Integer id_cadastro) {
        this.id_cadastro = id_cadastro;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpf) {
        this.cpfCnpj = cpf;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone){
        
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id_cadastro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id_cadastro, other.id_cadastro)) {
            return false;
        }
        return true;
    }
  
    
    public String toString(){
        return nome;
    }
    
}
