package DAOs;

import Entidades.VendaProduto;
import Entidades.VendaProdutoPK;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOVendaProduto extends DAOGenerico<VendaProduto> {

    private List<VendaProduto> lista = new ArrayList<>();

    public DAOVendaProduto() {
        super(VendaProduto.class);
    }

    public VendaProduto obter(VendaProdutoPK precoProdutoPK) {
        return em.find(VendaProduto.class, precoProdutoPK);
    }

    public List<VendaProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM VendaProduto e WHERE e.produto.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<VendaProduto> listById(int id) {
        return em.createQuery("SELECT e FROM VendaProduto e WHERE e.vendaProdutoPK.idProduto = :id").setParameter("id", id).getResultList();
    }

    public List<VendaProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM VendaProduto e ORDER BY e.produto").getResultList();
    }

    public List<VendaProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM VendaProduto e ORDER BY e.vendaProdutoPK.idProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<VendaProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getVendaProdutoPK().getIdProduto() + "-"
                    + lf.get(i).getProduto().getNome() + "-"
                    + Integer.valueOf(lf.get(i).getVendaProdutoPK().getIdVenda())
                    + "-" + lf.get(i).getPrecoVenda());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOVendaProduto daoVendaProduto = new DAOVendaProduto();
        List<VendaProduto> listaVendaProduto = daoVendaProduto.list();
        for (VendaProduto precoProduto : listaVendaProduto) {
            System.out.println(precoProduto.getPrecoVenda() + "-" + precoProduto.getProduto());
        }
    }
}