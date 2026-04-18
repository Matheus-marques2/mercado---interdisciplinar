package model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Integer idFornecedor;

    private String cnpj;
    @Column(name = "nome_fornecedor")
    private String nomeFornecedor;

    private String endereco;
    private String telefone;
    
    @Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
    
    private String email;
    private String complemento;

    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;

    public Fornecedor(){}

    public Fornecedor(Integer idFornecedor, String cnpj, String nomeFornecedor, String endereco, String telefone, String inscricaoEstadual,
    String email, String complemento, List<Produto> produtos){
        this.idFornecedor = idFornecedor;
        this.cnpj = cnpj;
        this.nomeFornecedor = nomeFornecedor;
        this.endereco = endereco;
        this.telefone = telefone;
        this.inscricaoEstadual = inscricaoEstadual;
        this.email = email;
        this.complemento = complemento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setId_fornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public void setInscricao_estadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public void setNome_fornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

     public void atualizarContato(String telefone, String email) {
        this.telefone = telefone;
        this.email = email;
    }

}
