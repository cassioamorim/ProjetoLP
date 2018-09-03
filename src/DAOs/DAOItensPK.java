package DAOs;

import Entidades.ItensPK;
import java.util.ArrayList;
import java.util.List;

public class DAOItensPK extends DAOGenerico<ItensPK> {

    private List<ItensPK> lista = new ArrayList<>();

    public DAOItensPK() {
        super(ItensPK.class);
    }

    public static void main(String[] args) {
        DAOItensPK daoItensPK = new DAOItensPK();
        List<ItensPK> listaItensPK = daoItensPK.list();
        for (ItensPK precoProdutoPK : listaItensPK) {
            System.out.println(precoProdutoPK.getIdProduto() + "-" + precoProdutoPK.getIdVenda());
        }
    }
}
