package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import java.util.Date;

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
    JPanel pnPrecoVenda = new JPanel(new GridLayout(1, 2));
    JLabel lbIdVenda = new JLabel("ID Venda");
    JTextField tfIdVenda = new JTextField(5);
    JLabel lbIdProduto = new JLabel("ID Produto");
    JTextField tfIdProduto = new JTextField(5);
    JLabel lbQuantidade = new JLabel("Quantidade");
    JTextField tfQuantidade = new JTextField(20);
    JLabel lbPrecoVenda = new JLabel("Preço de venda");
    JTextField tfPrecoVenda = new JTextField(20);

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOItensPK daoItensPK = new DAOItensPK();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Itens itens;
    DAOItens daoItens = new DAOItens();

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

    private void habilitarAtributos(boolean venda, boolean produto, boolean quantidade, boolean preco) {
        if (produto) {
            tfIdVenda.requestFocus();
            tfIdVenda.selectAll();
        }
        tfIdVenda.setEditable(venda);
        tfIdProduto.setEditable(produto);
        tfQuantidade.setEditable(quantidade);
        tfPrecoVenda.setEditable(preco);

    }

    public void zerarAtributos() {
        tfIdVenda.setText("");
        tfIdProduto.setText("");
        tfQuantidade.setText("");
        tfPrecoVenda.setText("");
    }
    Color corPadrao = lbIdProduto.getBackground();

    public GUIItens() {
        setTitle("CRUD - ItensPK");
        setSize(450, 200);
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, true, false, false);
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

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel(new GridLayout(2, 1));

        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        pnQuantidade.add(lbQuantidade);
        pnQuantidade.add(tfQuantidade);
        pnPrecoVenda.add(lbPrecoVenda);
        pnPrecoVenda.add(tfPrecoVenda);
        centro.add(pnQuantidade);
        centro.add(pnPrecoVenda);
        tfIdProduto.requestFocus();
        tfIdProduto.selectAll();
        labelAviso.setText("Digite no campo ID e clic [Pesquisar]");

//        try {
//            //esse código é para facilitar os testes
//            tfIdProduto.setText("1");
//            tfIdVenda.setText(sdf.format(sdf.parse("10/06/2018")));
//
//        } catch (Exception e) {
//        }
//--------------- listeners ----------------- 
        tfIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                itens = new Itens();
                tfIdProduto.setText(tfIdProduto.getText().trim());//caso tenham sido digitados espaços
                DAOItens daoItens1 = new DAOItens();
                if (tfIdProduto.getText().equals("")) {
                    // DAOProduto daoProduto = new DAOProduto();
                    List<String> listaAuxiliar = daoItens.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            tfIdProduto.setText(aux[0]);
                            tfIdVenda.setText(aux[2]);
                            btnRetrieve.doClick();
                        } else {
                            tfIdProduto.requestFocus();
                            tfIdProduto.selectAll();
                        }
                    }
                    tfIdProduto.requestFocus();
                    tfIdProduto.selectAll();
                } else {
                    try {
                        ItensPK itensPK = new ItensPK();
                        itensPK.setIdVenda(Integer.valueOf(tfIdVenda.getText()));
                        itensPK.setIdProduto(Integer.valueOf(tfIdProduto.getText()));
                        DAOItens daoItens = new DAOItens();
                        itens = daoItens.obter(itensPK);
                        if (itens != null) { //se encontrou na lista                            
                            tfQuantidade.setText(String.valueOf(itens.getQuantidade()));
                            tfPrecoVenda.setText(String.valueOf(itens.getPrecoVenda()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {  //não achou na lista
                            atvBotoes(true, true, false, false);
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        tfIdProduto.setBackground(Color.green);
                        tfIdVenda.setBackground(Color.green);
                    } catch (Exception x) {
                        tfIdProduto.setOpaque(true);
                        tfIdProduto.selectAll();
                        tfIdProduto.requestFocus();
                        tfIdProduto.setBackground(Color.red);
                        tfIdVenda.setBackground(Color.red);
                        labelAviso.setText("Erro em algum dos campos ID - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                habilitarAtributos(false, false, true, true);
                tfQuantidade.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
                tfIdProduto.setBackground(corPadrao);
                tfIdVenda.setBackground(corPadrao);
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    itens = new Itens();
                }
                try {
                    sdf.setLenient(false);
                    int idVenda = Integer.valueOf(tfIdVenda.getText());
                    int idProd = Integer.valueOf(tfIdProduto.getText());
                    itens.setItensPK(new ItensPK(idVenda, idProd));

                } catch (Exception erro2) {
                    deuRuim = true;
                    tfIdProduto.setBackground(Color.red);
                    tfIdVenda.setBackground(Color.red);
                }
                try {
                    itens.setQuantidade(Integer.valueOf(tfQuantidade.getText()));
                    itens.setPrecoVenda(Double.valueOf(tfPrecoVenda.getText()));
                    zerarAtributos();
                } catch (Exception erro3) {
                    tfPrecoVenda.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoItens.inserir(itens);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoItens.atualizar(itens);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, true, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, true, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "list";
                GUIItensListagem guiItensListagem = new GUIItensListagem(daoItens.listInOrderNome());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, false, true, true);
                tfIdVenda.setBackground(corPadrao);
                tfIdProduto.setBackground(corPadrao);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro?\n "
                        + itens.getProduto().getNome() + "\n"
                        + Integer.valueOf(itens.getItensPK().getIdVenda()) + "\n"
                        + "R$" + itens.getPrecoVenda() + "\n",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
//                    daDoItensPK.remover(produto);
                    daoItens.remover(itens);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
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
                    } else {
                        tfIdVenda.requestFocus();
                        tfIdVenda.selectAll();
                    }
                } else {
                    System.out.println("Nenhum dado adicionado");
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
                        btnRetrieve.doClick();
                    } else {
                        tfIdProduto.requestFocus();
                        tfIdProduto.selectAll();
                    }
                } else {
                    System.out.println("Nenhum dado adicionado");
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIItens();
    }
}
