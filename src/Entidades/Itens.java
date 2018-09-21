/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Cássio
 */
@Entity
@Table(name = "itens")
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i")})
public class Itens implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItensPK itensPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "preco_produto")
    private double precoProduto;
    @Basic(optional = false)
    @Column(name = "desconto")
    private double desconto;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public Itens() {
    }

    public Itens(ItensPK itensPK) {
        this.itensPK = itensPK;
    }

    public Itens(ItensPK itensPK, int quantidade, double precoProduto, double desconto, double total) {
        this.itensPK = itensPK;
        this.quantidade = quantidade;
        this.precoProduto = precoProduto;
        this.desconto = desconto;
        this.total = total;
    }

    public Itens(int idVenda, int idProduto) {
        this.itensPK = new ItensPK(idVenda, idProduto);
    }

    public ItensPK getItensPK() {
        return itensPK;
    }

    public void setItensPK(ItensPK itensPK) {
        this.itensPK = itensPK;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itensPK != null ? itensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.itensPK == null && other.itensPK != null) || (this.itensPK != null && !this.itensPK.equals(other.itensPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Itens[ itensPK=" + itensPK + " ]";
    }
    
}
