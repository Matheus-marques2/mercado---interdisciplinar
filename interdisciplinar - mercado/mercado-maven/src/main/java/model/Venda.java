package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_venda")
    private Integer idVenda;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    private BigDecimal valorPago;

    private BigDecimal troco;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda(){}

    public Venda(Integer idVenda, LocalDateTime dataVenda, String formaPagamento, BigDecimal valorTotal, BigDecimal valorPago, BigDecimal troco){
        this.idVenda = idVenda;
        this.dataVenda = dataVenda;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.troco = troco;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    public void adicionarItem(ItemVenda item) {
        item.setVenda(this);
        this.itens.add(item);
    }

    public void calcularTotal() {
        this.valorTotal = itens.stream()
                .map(i -> i.getSubtotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void calcularTroco() {
        if (valorPago != null && valorTotal != null) {
            this.troco = valorPago.subtract(valorTotal);
        }
    }
}
