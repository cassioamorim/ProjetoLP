package DAOs;

import Entidades.Itens;
import Entidades.ItensPK;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOItens extends DAOGenerico<Itens> {

    private List<Itens> lista = new ArrayList<>();

    public DAOItens() {
        super(Itens.class);
    }

    public Itens obter(ItensPK precoProdutoPK) {
        return em.find(Itens.class, precoProdutoPK);
    }

    public List<Itens> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Itens e WHERE e.produto.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Itens> listById(int id) {
        return em.createQuery("SELECT e FROM Itens e WHERE e.itensPK.idProduto = :id").setParameter("id", id).getResultList();
    }

    public List<Itens> listInOrderNome() {
        return em.createQuery("SELECT e FROM Itens e ORDER BY e.produto").getResultList();
    }

    public List<Itens> listInOrderId() {
        return em.createQuery("SELECT e FROM Itens e ORDER BY e.itensPK.idProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Itens> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getItensPK().getIdProduto() + "-"
                    + lf.get(i).getProduto().getNome() + "-"
                    + Integer.valueOf(lf.get(i).getItensPK().getIdVenda())
                    + "-" + lf.get(i).getPrecoVenda());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOItens daoItens = new DAOItens();
        List<Itens> listaItens = daoItens.list();
        for (Itens precoProduto : listaItens) {
            System.out.println(precoProduto.getPrecoVenda() + "-" + precoProduto.getProduto());
        }
    }
}