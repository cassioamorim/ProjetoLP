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
 * @author Sandro
 */
@Entity
@Table(name = "venda_produto")
@NamedQueries({
    @NamedQuery(name = "VendaProduto.findAll", query = "SELECT v FROM VendaProduto v")})
public class VendaProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendaProdutoPK vendaProdutoPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "preco_venda")
    private double precoVenda;
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;

    public VendaProduto() {
    }

    public VendaProduto(VendaProdutoPK vendaProdutoPK) {
        this.vendaProdutoPK = vendaProdutoPK;
    }

    public VendaProduto(VendaProdutoPK vendaProdutoPK, int quantidade, double precoVenda) {
        this.vendaProdutoPK = vendaProdutoPK;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public VendaProduto(int idVenda, int idProduto) {
        this.vendaProdutoPK = new VendaProdutoPK(idVenda, idProduto);
    }

    public VendaProdutoPK getVendaProdutoPK() {
        return vendaProdutoPK;
    }

    public void setVendaProdutoPK(VendaProdutoPK vendaProdutoPK) {
        this.vendaProdutoPK = vendaProdutoPK;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
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
        hash += (vendaProdutoPK != null ? vendaProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaProduto)) {
            return false;
        }
        VendaProduto other = (VendaProduto) object;
        if ((this.vendaProdutoPK == null && other.vendaProdutoPK != null) || (this.vendaProdutoPK != null && !this.vendaProdutoPK.equals(other.vendaProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.VendaProduto[ vendaProdutoPK=" + vendaProdutoPK + " ]";
    }
    
}
