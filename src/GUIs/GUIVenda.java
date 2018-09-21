package GUIs;

import DAOs.DAOVenda;
import Entidades.*;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import tools.*;
import DAOs.*;

public class GUIVenda extends JFrame {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Date data1;
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(2, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JLabel lbIdVenda = new JLabel("id");
    private JTextField tfIdVenda = new JTextField(10);
    private JLabel lbData = new JLabel("Data");
    private JTextField tfData = new JTextField(10);
    private JButton btEscolha1 = new JButton("Escolha");
    private JPanel pnData = new JPanel(new GridLayout(1, 2));
    private JPanel pnClienteCpf = new JPanel(new GridLayout(1, 2));
    private JLabel lbClienteCpf = new JLabel("CPF");
    private JTextField tfClienteCpf = new JTextField();
    private JButton btClienteCpf = new JButton("Procurar");
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";
    DAOVenda daoVenda = new DAOVenda();
    Venda venda;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();

    public GUIVenda() {
        setSize(500, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Venda");
        Container cp = getContentPane();
        cp = getContentPane();
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        pnNorte.add(lbIdVenda);
        pnNorte.add(tfIdVenda);
        pnNorte.add(btnRetrieve);
        pnNorte.add(btnCreate);
        pnNorte.add(btnUpdate);
        pnNorte.add(btnDelete);
        pnNorte.add(btnSave);
        pnNorte.add(btnList);
        btnCancel.setVisible(false);
        btnDelete.setVisible(false);
        btnCreate.setVisible(false);
        btnSave.setVisible(false);
        btnUpdate.setVisible(false);
        pnCentro.add(lbData);
        pnCentro.add(pnData);
        pnData.add(tfData);
        pnData.add(btEscolha1);
        pnCentro.add(lbClienteCpf);
        pnCentro.add(pnClienteCpf);
        pnClienteCpf.add(tfClienteCpf);
        pnClienteCpf.add(btClienteCpf);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        btEscolha1.setEnabled(false);
        tfData.setEditable(false);
        tfClienteCpf.setEditable(false);
        btClienteCpf.setEnabled(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfIdVenda.setBackground(Color.white);
                    jTextArea.setText("");
                    venda = new Venda();
                    int identificador = Integer.valueOf(tfIdVenda.getText());
                    venda.setIdVenda(identificador);
                    venda = daoVenda.obter(venda.getIdVenda());
                    if (venda == null) {
                        pnNorte.setBackground(Color.red);
                        tfData.setText("");
                        tfClienteCpf.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        tfData.setText(sdf.format(venda.getData()));
                        String dao1 = String.valueOf(venda.getClienteCpf());
                        String[] aux1 = dao1.split("-");
                        tfClienteCpf.setText(aux1[0]);
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    tfIdVenda.setEditable(false);
                    tfData.setEditable(false);
                    btClienteCpf.setEnabled(false);
                    tfIdVenda.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfIdVenda.requestFocus();
                    tfIdVenda.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdVenda.setEditable(false);
                tfData.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                venda = new Venda();
                btEscolha1.setEnabled(true);
                btClienteCpf.setEnabled(true);
                tfIdVenda.setEditable(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    venda = new Venda();
                    venda.setIdVenda(Integer.valueOf(tfIdVenda.getText()));
                    sdf.setLenient(false);
                    data1 = sdf.parse(tfData.getText());
                    try {
                        venda.setData(sdf.parse(tfData.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(GUIVenda.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    String[] aux0 = tfClienteCpf.getText().split("-");
                    DAOCliente daoCliente = new DAOCliente();
                    Cliente d0 = daoCliente.obter(aux0[0]);
                    venda.setClienteCpf(d0);
                    if (qualAcao.equals("incluir")) {
                        daoVenda.inserir(venda);
                    } else {
                        daoVenda.atualizar(venda);
                    }
                    tfIdVenda.setEditable(true);
                    tfIdVenda.requestFocus();
                    tfData.setText("");
                    tfClienteCpf.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    btEscolha1.setEnabled(false);
                    btClienteCpf.setEnabled(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfIdVenda.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                tfData.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                btEscolha1.setEnabled(true);
                btClienteCpf.setEnabled(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + venda.getIdVenda() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoVenda.remover(venda);
                    tfIdVenda.requestFocus();
                    tfData.setText("");
                    tfClienteCpf.setText("");
                    tfIdVenda.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemVenda guiListagem = new GUIListagemVenda(daoVenda.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        btEscolha1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    DateChooser dc1 = new DateChooser((JFrame) null, "Escolha uma data");
                    data1 = dc1.select();
                    tfData.setText(sdf.format(data1));
                } catch (Exception ex) {
                    jTextArea.setText("Escolha uma data\n");
                }
            }
        });
        DAOCliente daoCliente = new DAOCliente();
        btClienteCpf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfClienteCpf.setText(aux[0]);
                    } else {
                        jTextArea.setText("Nenhum dado adicionado!");
                    }
                }
            }
        });
        tfIdVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoVenda.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdVenda.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfIdVenda.requestFocus();
                        tfIdVenda.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        GUIVenda guiVenda = new GUIVenda();
    }
}
