package DAOs;

import Entidades.VendaProdutoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOVendaProdutoPK extends DAOGenerico<VendaProdutoPK> {

    private List<VendaProdutoPK> lista = new ArrayList<>();

    public DAOVendaProdutoPK() {
        super(VendaProdutoPK.class);
    }

    public static void main(String[] args) {
        DAOVendaProdutoPK daoVendaProdutoPK = new DAOVendaProdutoPK();
        List<VendaProdutoPK> listaVendaProdutoPK = daoVendaProdutoPK.list();
        for (VendaProdutoPK precoProdutoPK : listaVendaProdutoPK) {
            System.out.println(precoProdutoPK.getIdProduto() + "-" + precoProdutoPK.getIdVenda());
        }
    }
}
