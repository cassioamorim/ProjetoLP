package GUIs;

import Entidades.Cliente;
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

public class GUIListagemCliente extends JDialog {

    JPanel painelTa = new JPanel();
    JScrollPane scroll = new JScrollPane();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");

    public GUIListagemCliente(List<Cliente> texto) {
        setTitle("Listagem de Cliente");
        setSize(1000, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);
        setModal(true);
        Container cp = getContentPane();
        JToolBar toolBar = new JToolBar();
        String[] colunas = new String[]{"CPF", "Nome", "Data de nascimento", "Login", "Senha", "Número de telefone", "Endereço"};
        String[][] dados = new String[0][6];
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        scroll.setViewportView(tabela);
        for (int i = 0; i < texto.size(); i++) {
            String senha = String.valueOf(texto.get(i).getSenha());
            String senhaAst = "";
            for (int j = 0; j < senha.length(); j++) {
                senhaAst += "*";
            }
            String[] linha = new String[]{String.valueOf(texto.get(i).getCpf()), String.valueOf(texto.get(i).getNome()), sdf.format(texto.get(i).getDataNascimento()), String.valueOf(texto.get(i).getLogin()), senhaAst, String.valueOf(texto.get(i).getNumeroTelefone()), String.valueOf(texto.get(i).getEndereco()),};
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
