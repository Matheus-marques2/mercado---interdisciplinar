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
@Table(name = "item_venda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_item")
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int quantidade;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    public ItemVenda(){}

    public ItemVenda(Integer idItem, Venda venda, Produto produto, int quantidade, BigDecimal precoUnitario){
        this.idItem = idItem;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getIdItem() {
        return idItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public BigDecimal getprecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void setprecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public void definirProduto(Produto produto) {
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
    }
}
