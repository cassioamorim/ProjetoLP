package GUIs;

import DAOs.*;
import Entidades.*;
import java.util.List;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import tools.*;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class GUIItens extends JDialog {

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
    JPanel pnQuantidade = new JPanel(new GridLayout(1, 2));
    JPanel pnPrecoProduto = new JPanel(new GridLayout(1, 2));
    JPanel pnDesconto = new JPanel(new GridLayout(1, 2));
    JPanel pnTotal = new JPanel(new GridLayout(1, 2));
    JLabel lbIdVenda = new JLabel("ID Venda");
    JTextField tfIdVenda = new JTextField(5);
    JLabel lbIdProduto = new JLabel("ID Produto");
    JTextField tfIdProduto = new JTextField(5);
    JLabel lbQuantidade = new JLabel("Quantidade");
    JTextField tfQuantidade = new JTextField(20);
    JLabel lbPrecoProduto = new JLabel("Preço do produto");
    JTextField tfPrecoProduto = new JTextField(20);
    JLabel lbDesconto = new JLabel("Desconto (%)");
    JTextField tfDesconto = new JTextField(20);
    JLabel lbTotal = new JLabel("Total");
    JTextField tfTotal = new JTextField(20);
    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";
    DAOItensPK daoItensPK = new DAOItensPK();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Itens itens;
    DAOItens daoItens = new DAOItens();
    Produto produto = new Produto();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean venda, boolean produto, boolean quantidade, boolean preco, boolean desconto) {
        if (produto) {
            tfIdVenda.requestFocus();
            tfIdVenda.selectAll();
        }
        tfIdVenda.setEditable(venda);
        tfIdProduto.setEditable(produto);
        tfQuantidade.setEditable(quantidade);
        tfPrecoProduto.setEditable(preco);
        tfDesconto.setEditable(desconto);
    }

    public void zerarAtributos() {
        tfIdVenda.setText("");
        tfIdProduto.setText("");
        tfQuantidade.setText("");
        tfPrecoProduto.setText("");
        tfDesconto.setText("");
        tfTotal.setText("");
    }
    Color corPadrao = lbIdProduto.getBackground();

    public GUIItens() {
        setTitle("CRUD - ItensPK");
        setSize(600, 200);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        Container cp = getContentPane();
        atvBotoes(false, true, false, false);
        habilitarAtributos(false, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(lbIdVenda);
        Toolbar1.add(tfIdVenda);
        Toolbar1.add(lbIdProduto);
        Toolbar1.add(tfIdProduto);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        JPanel centro = new JPanel(new GridLayout(4, 1));
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        pnQuantidade.add(lbQuantidade);
        pnQuantidade.add(tfQuantidade);
        pnPrecoProduto.add(lbPrecoProduto);
        pnPrecoProduto.add(tfPrecoProduto);
        pnDesconto.add(lbDesconto);
        pnDesconto.add(tfDesconto);
        pnTotal.add(lbTotal);
        pnTotal.add(tfTotal);
        centro.add(pnQuantidade);
        centro.add(pnPrecoProduto);
        centro.add(pnDesconto);
        centro.add(pnTotal);
        tfTotal.setEnabled(false);
        tfIdProduto.requestFocus();
        tfIdProduto.selectAll();
        labelAviso.setText("Aperte ENTER nos campos ID para selecionar os ID's e click [Pesquisar].");
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                itens = new Itens();
                try {
                    ItensPK itensPK = new ItensPK();
                    itensPK.setIdVenda(Integer.valueOf(tfIdVenda.getText()));
                    itensPK.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                    DAOItens daoItens = new DAOItens();
                    itens = daoItens.obter(itensPK);
                    if (itens != null) {
                        tfQuantidade.setText(String.valueOf(itens.getQuantidade()));
                        tfPrecoProduto.setText(String.valueOf(itens.getPrecoProduto()));
                        tfDesconto.setText(String.valueOf(itens.getDesconto()));
                        tfTotal.setText(String.valueOf(itens.getTotal()));
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(false, false, false, false, false);
                        labelAviso.setText("Encontrou - click [Alterar] ou [Excluir].");
                        acao = "encontrou";
                    } else {
                        atvBotoes(true, true, false, false);
                        labelAviso.setText("Não cadastrado - click [Inserir] ou aperte ENTER nos campos ID para selecionar outro ID [Pesquisar].");
                    }
                    tfIdProduto.setBackground(Color.green);
                    tfIdVenda.setBackground(Color.green);
                } catch (Exception x) {
                    if (tfIdVenda.getText().equals("")) {
                        labelAviso.setText("Erro no campo ID Venda.");
                        tfIdVenda.setBackground(Color.red);
                    } else {
                        labelAviso.setText("Erro no campo ID Produto.");
                        tfIdProduto.setBackground(Color.red);
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                habilitarAtributos(false, false, true, true, true);
                tfQuantidade.requestFocus();
                mostrarBotoes(false);
                btnSave.setEnabled(true);
                DAOProduto daoProduto = new DAOProduto();
                produto = new Produto();
                int identificador = Integer.valueOf(tfIdProduto.getText());
                produto = daoProduto.obter(identificador);
                tfPrecoProduto.setText(String.valueOf(produto.getPreco()));
                labelAviso.setText("Preencha os campos e clic [Salvar] ou click [Cancelar].");
                acao = "insert";
                tfIdProduto.setBackground(corPadrao);
                tfIdVenda.setBackground(corPadrao);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    itens = new Itens();
                }
                try {
                    int idVenda = Integer.valueOf(tfIdVenda.getText());
                    int idProd = Integer.valueOf(tfIdProduto.getText());
                    itens.setItensPK(new ItensPK(idVenda, idProd));
                } catch (Exception erro2) {
                    deuRuim = true;
                    tfIdProduto.setBackground(Color.red);
                    tfIdVenda.setBackground(Color.red);
                }
                try {
                    int quantidade = Integer.valueOf(tfQuantidade.getText());
                    itens.setQuantidade(quantidade);
                    double precoProduto = Double.valueOf(tfPrecoProduto.getText());
                    itens.setPrecoProduto(precoProduto);
                    double desconto = (Double.valueOf(tfDesconto.getText())) / 100;
                    itens.setDesconto(Double.valueOf(tfDesconto.getText()));
                    double subtotal = precoProduto * quantidade;
                    double total = subtotal;
                    if (desconto != 0) {
                        total = subtotal - (subtotal * desconto);
                    }
                    itens.setTotal(total);
                    zerarAtributos();
                } catch (Exception erro3) {
                    deuRuim = true;
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoItens.inserir(itens);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoItens.atualizar(itens);
                        labelAviso.setText("Registro alterado.");
                    }
                    tfQuantidade.setBackground(corPadrao);
                    tfPrecoProduto.setBackground(corPadrao);
                    tfDesconto.setBackground(corPadrao);
                    habilitarAtributos(false, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                } else {
                    tfQuantidade.setBackground(Color.RED);
                    tfPrecoProduto.setBackground(Color.RED);
                    tfDesconto.setBackground(Color.RED);
                    labelAviso.setText("Erro em algum dos campos.");
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(false, false, false, false, false);
                mostrarBotoes(true);
                tfIdProduto.setBackground(corPadrao);
                tfIdVenda.setBackground(corPadrao);
                tfQuantidade.setBackground(corPadrao);
                tfPrecoProduto.setBackground(corPadrao);
                tfDesconto.setBackground(corPadrao);
                tfTotal.setBackground(corPadrao);
                labelAviso.setText("Aperte ENTER nos campos ID e click [Pesquisar].");
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "list";
                GUIItensListagem guiItensListagem = new GUIItensListagem(daoItens.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                btnSave.setEnabled(true);
                habilitarAtributos(false, false, true, true, true);
                labelAviso.setText("Editando - click [Salvar] ou [Cancelar].");
                tfIdVenda.setBackground(corPadrao);
                tfIdProduto.setBackground(corPadrao);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão da venda?\n"
                        + "ID: " + Integer.valueOf(itens.getItensPK().getIdVenda()) + "\n"
                        + "R$" + itens.getPrecoProduto() + "\n",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoItens.remover(itens);
                    zerarAtributos();
                    habilitarAtributos(false, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    tfIdVenda.setBackground(corPadrao);
                    tfIdProduto.setBackground(corPadrao);
                    tfIdVenda.requestFocus();
                    tfIdVenda.selectAll();
                }
            }
        });
        DAOVenda daoVenda = new DAOVenda();
        tfIdVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoVenda.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdVenda.setText(aux[0]);
                        tfIdVenda.setBackground(Color.GREEN);
                        labelAviso.setText("Aperte ENTER nos campos ID e click [Pesquisar].");
                        btnCreate.setEnabled(false);
                        btnDelete.setEnabled(false);
                        btnSave.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        btnRetrieve.setEnabled(true);
                    } else {
                        labelAviso.setText("Erro no campo ID Venda.");
                        tfIdVenda.setBackground(Color.RED);
                    }
                } else {
                    System.out.println("Nenhum dado adicionado.");
                }
            }
        });
        DAOProduto daoProduto = new DAOProduto();
        tfIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfIdProduto.setText(aux[0]);
                        tfIdProduto.setBackground(Color.GREEN);
                        labelAviso.setText("Aperte ENTER nos campos ID e click [Pesquisar].");
                        btnCreate.setEnabled(false);
                        btnDelete.setEnabled(false);
                        btnSave.setEnabled(false);
                        btnUpdate.setEnabled(false);
                        btnRetrieve.setEnabled(true);
                    } else {
                        labelAviso.setText("Erro no campo ID Produto.");
                        tfIdProduto.setBackground(Color.RED);
                    }
                } else {
                    System.out.println("Nenhum dado adicionado.");
                }
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIItens();
    }
}
