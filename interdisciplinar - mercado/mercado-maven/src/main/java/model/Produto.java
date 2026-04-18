package model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_produto")
    private Integer idProduto;

    @Column(name = "codigo_de_barras")
    private String codigoDeBarras;

    @Column(name = "nome_do_produto")
    private String nomeDoProduto;

    @Column(name = "preco")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    private int quantidade;
    private String categoria;
    private int estoque_minimo;

    public Produto(){}

    public Produto(Integer idProduto, String codigoDeBarras, String nomeDoProduto, BigDecimal preco, Fornecedor fornecedor, int quantidade, String categoria, int estoque_minimo){
        this.idProduto = idProduto;
        this.codigoDeBarras = codigoDeBarras;
        this.nomeDoProduto = nomeDoProduto;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.estoque_minimo = estoque_minimo;
    }

    public Integer getIdProduto() {
        return idProduto;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void reduzirEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        }
    }

    public void aumentarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }
}


