package GUIs;

import DAOs.DAOProduto;
import DAOs.DAOVenda;
import Entidades.Itens;
import static Entidades.Itens_.produto;
import Entidades.Produto;
import Entidades.Venda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.CentroDoMonitorMaior;

public class GUIItensListagem extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    DAOProduto daoProduto = new DAOProduto();
    DAOVenda daoVenda = new DAOVenda();
    Produto produto = new Produto();
    Venda venda = new Venda();

    public GUIItensListagem(List<Itens> texto) {
        if (texto == null) {
            System.out.println("aqui");
            return;
        }
        setTitle("Listagem de Itens");
        setSize(1000, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        setModal(true);
        Container cp = getContentPane();
        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{"ID Venda", "Cliente", "Produto", "Quantidade", "Preço do produto", "Desconto", "Total"};
        String[][] dados = new String[0][6];
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        tabela.setEnabled(false);
        scroll.setViewportView(tabela);
        for (int i = 0; i < texto.size(); i++) {
            int idP = Integer.valueOf(texto.get(i).getItensPK().getIdProduto());
            produto = daoProduto.obter(idP);
            int idC = Integer.valueOf(texto.get(i).getItensPK().getIdVenda());
            venda = daoVenda.obter(idC);
            String[] linha = new String[]{String.valueOf(texto.get(i).getItensPK().getIdVenda()),
                venda.getClienteCpf().getNome(),
                produto.getNome(),
                String.valueOf(texto.get(i).getQuantidade()),
                String.valueOf(texto.get(i).getPrecoProduto()),
                String.valueOf(texto.get(i).getDesconto()),
                String.valueOf(texto.get(i).getTotal()),};
            model.addRow(linha);
        }
        painelTa.add(scroll);
        cp.add(toolBar, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }
}
