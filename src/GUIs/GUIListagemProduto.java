package GUIs;

import Entidades.Produto;
import tools.*;
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

public class GUIListagemProduto extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    public GUIListagemProduto(List<Produto> texto) {
        setTitle("Listagem de Produto");
        setSize(1000, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        setModal(true);
        Container cp = getContentPane();
        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{"ID", "Nome", "Especificações", "Preço", "Tipo", "Marca"};
        String[][] dados = new String[0][5];
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        scroll.setViewportView(tabela);
        for (int i = 0; i < texto.size(); i++) {
            String[] linha = new String[]{String.valueOf(texto.get(i).getIdProduto()), String.valueOf(texto.get(i).getNome()), String.valueOf(texto.get(i).getEspecificacoes()), String.valueOf(texto.get(i).getPreco()), String.valueOf(texto.get(i).getIdTipo()), String.valueOf(texto.get(i).getIdMarca())};
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
