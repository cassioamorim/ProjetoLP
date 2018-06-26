package GUIs;

import DAOs.DAOCliente;
import Entidades.*;
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
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Image;
import javax.swing.JTextField;
import tools.*;
import DAOs.*;

public class GUICliente extends JFrame {

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
    private Date data2;
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(6, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private JPanel pnE1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnE2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel pnE3 = new JPanel(new GridLayout(1, 1));
    private JLabel lbCpf = new JLabel("CPF");
    private JTextField tfCpf = new JTextField(10);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField(10);
    private JLabel lbDataNascimento = new JLabel("Data de nascimento");
    private JTextField tfDataNascimento = new JTextField(10);
    private JButton btEscolha2 = new JButton("Escolha");
    private JPanel pnDataNascimento = new JPanel(new GridLayout(1, 2));
    private JLabel lbLogin = new JLabel("Login");
    private JTextField tfLogin = new JTextField(10);
    private JLabel lbSenha = new JLabel("Senha");
    private JTextField tfSenha = new JTextField(10);
    private JLabel lbNumeroTelefone = new JLabel("Número de telefone");
    private JTextField tfNumeroTelefone = new JTextField(10);
    private JLabel lbEndereco = new JLabel("Endereço");
    private JTextField tfEndereco = new JTextField(10);
    JTextField tfCaminho = new JTextField();
    ScrollPane scroll = new ScrollPane();
    JTextArea jTextArea = new JTextArea();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String qualAcao = "";//variavel para facilitar insert e update
    DAOCliente daoCliente = new DAOCliente();
    Cliente cliente;
    private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();
    private JPanel pnEsquerda = new JPanel(new BorderLayout());
    private JPanel pnDireita = new JPanel(new BorderLayout());
    private JLabel rotulo = new JLabel();
    private JButton btAbrirImagem = new JButton("Selecionar imagem");
    private String caminho;
    private Image imagemAux;
    private ImageIcon icone;

    public GUICliente() {
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Cliente");
        Container cp = getContentPane();
        cp = getContentPane();
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.setLayout(new GridLayout(1, 2));
        cp.add(pnEsquerda);
        cp.add(pnDireita);
        try {
            String caminho = "";
            tfCaminho.setText(caminho);
            icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            rotulo.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());
        }
        pnDireita.add(pnE1, BorderLayout.NORTH);
        pnE1.add(rotulo);
        pnDireita.add(pnE2, BorderLayout.CENTER);
        pnE2.add(btAbrirImagem);
        pnDireita.add(pnE3, BorderLayout.SOUTH);
        pnE3.add(tfCaminho);
        pnEsquerda.add(pnNorte, BorderLayout.NORTH);
        pnEsquerda.add(pnCentro, BorderLayout.CENTER);
        pnEsquerda.add(pnSul, BorderLayout.SOUTH);
        pnNorte.add(lbCpf);
        pnNorte.add(tfCpf);
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
        btAbrirImagem.setEnabled(false);
        tfCaminho.setEditable(false);
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbDataNascimento);
        pnCentro.add(pnDataNascimento);
        pnDataNascimento.add(btEscolha2);
        pnDataNascimento.add(tfDataNascimento);
        pnCentro.add(lbLogin);
        pnCentro.add(tfLogin);
        pnCentro.add(lbSenha);
        pnCentro.add(tfSenha);
        pnCentro.add(lbNumeroTelefone);
        pnCentro.add(tfNumeroTelefone);
        pnCentro.add(lbEndereco);
        pnCentro.add(tfEndereco);
        pnSul.setBackground(Color.red);
        scroll.add(jTextArea);
        pnSul.add(scroll);
        tfCaminho.setEditable(false);
        tfNome.setEditable(false);
        btEscolha2.setEnabled(false);
        tfDataNascimento.setEditable(false);
        tfLogin.setEditable(false);
        tfSenha.setEditable(false);
        tfNumeroTelefone.setEditable(false);
        tfEndereco.setEditable(false);
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tfCpf.setBackground(Color.white);
                    jTextArea.setText("");
                    cliente = new Cliente();
                    String identificador = tfCpf.getText();
                    cliente.setCpf(identificador);
                    cliente = daoCliente.obter(cliente.getCpf());
                    if (cliente == null) {
                        pnNorte.setBackground(Color.red);
                        tfCaminho.setText("");
                        tfNome.setText("");
                        tfDataNascimento.setText("");
                        tfLogin.setText("");
                        tfSenha.setText("");
                        tfNumeroTelefone.setText("");
                        tfEndereco.setText("");
                        tfCaminho.setText("");
                        btnCreate.setVisible(true);
                    } else {
                        pnNorte.setBackground(Color.green);
                        caminho = cliente.getCaminho();
                        tfCaminho.setText(caminho);
                        icone = new ImageIcon(caminho);
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                        tfNome.setText(cliente.getNome());
                        tfDataNascimento.setText(sdf.format(cliente.getDataNascimento()));
                        tfLogin.setText(cliente.getLogin());
                        tfSenha.setText(cliente.getSenha());
                        tfNumeroTelefone.setText(cliente.getNumeroTelefone());
                        tfEndereco.setText(cliente.getEndereco());
                        tfCaminho.setText(cliente.getCaminho());
                        btnUpdate.setVisible(true);
                        btnDelete.setVisible(true);
                        btnCreate.setVisible(false);
                    }
                    btAbrirImagem.setEnabled(false);
                    tfCpf.setEditable(false);
                    tfNome.setEditable(false);
                    tfDataNascimento.setEditable(false);
                    tfLogin.setEditable(false);
                    tfSenha.setEditable(false);
                    tfNumeroTelefone.setEditable(false);
                    tfEndereco.setEditable(false);
                    tfCpf.selectAll();
                } catch (Exception erro) {
                    pnNorte.setBackground(Color.yellow);
                    tfCpf.requestFocus();
                    tfCpf.setBackground(Color.red);
                    jTextArea.setText("Erro... \n");
                    jTextArea.append(erro.getMessage());
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfCpf.setEditable(false);
                tfNome.requestFocus();
                btnCreate.setVisible(false);
                btnSave.setVisible(true);
                qualAcao = "incluir";
                cliente = new Cliente();
                tfNome.setEditable(true);
                btEscolha2.setEnabled(true);
                tfLogin.setEditable(true);
                tfSenha.setEditable(true);
                tfNumeroTelefone.setEditable(true);
                tfEndereco.setEditable(true);
                tfCpf.setEditable(false);
                btAbrirImagem.setEnabled(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    cliente = new Cliente();
                    cliente.setCpf(tfCpf.getText());
                    cliente.setNome(tfNome.getText());
                    sdf.setLenient(false);
                    data2 = sdf.parse(tfDataNascimento.getText());
                    try {
                        cliente.setDataNascimento(sdf.parse(tfDataNascimento.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(GUICliente.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    cliente.setLogin(tfLogin.getText());
                    cliente.setSenha(tfSenha.getText());
                    cliente.setNumeroTelefone(tfNumeroTelefone.getText());
                    cliente.setEndereco(tfEndereco.getText());
                    cliente.setCaminho(tfCaminho.getText());
                    caminho = tfCaminho.getText();
                    cliente.setCaminho(caminho);
                    caminho = "";
                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);
                    if (qualAcao.equals("incluir")) {
                        daoCliente.inserir(cliente);
                    } else {
                        daoCliente.atualizar(cliente);
                    }
                    tfCpf.setEditable(true);
                    tfCpf.requestFocus();
                    tfNome.setText("");
                    tfDataNascimento.setText("");
                    tfLogin.setText("");
                    tfSenha.setText("");
                    tfNumeroTelefone.setText("");
                    tfEndereco.setText("");
                    tfCaminho.setText("");
                    tfCaminho.setText("");
                    btnSave.setVisible(false);
                    pnNorte.setBackground(Color.green);
                    tfNome.setEditable(false);
                    btEscolha2.setEnabled(false);
                    tfLogin.setEditable(false);
                    tfSenha.setEditable(false);
                    tfNumeroTelefone.setEditable(false);
                    tfEndereco.setEditable(false);
                    tfCaminho.setEditable(false);
                    btAbrirImagem.setEnabled(false);
                } catch (Exception erro) {
                    jTextArea.append("Erro............");
                    tfCpf.setEditable(true);
                    pnNorte.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
                btAbrirImagem.setEnabled(true);
                tfNome.requestFocus();
                btnSave.setVisible(true);
                qualAcao = "editar";
                tfNome.setEditable(true);
                btEscolha2.setEnabled(true);
                tfLogin.setEditable(true);
                tfSenha.setEditable(true);
                tfNumeroTelefone.setEditable(true);
                tfEndereco.setEditable(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + cliente.getCpf() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    daoCliente.remover(cliente);
                    tfCpf.requestFocus();
                    tfNome.setText("");
                    tfDataNascimento.setText("");
                    tfLogin.setText("");
                    tfSenha.setText("");
                    tfNumeroTelefone.setText("");
                    tfEndereco.setText("");
                    tfCaminho.setText("");
                    String caminho = "";

                    icone = new ImageIcon(caminho);
                    rotulo.setIcon(icone);

                    tfCaminho.setText("");
                    tfCpf.setEditable(true);
                    btnUpdate.setVisible(false);
                    btnDelete.setVisible(false);
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIListagemCliente guiListagem = new GUIListagemCliente(daoCliente.list());
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        btEscolha2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jTextArea.setText("");
                    DateChooser dc2 = new DateChooser((JFrame) null, "Escolha uma data");
                    data2 = dc2.select();
                    tfDataNascimento.setText(sdf.format(data2));
                } catch (Exception ex) {
                    jTextArea.setText("Escolha uma data\n");
                }
            }
        });
        btAbrirImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(pnDireita) == JFileChooser.APPROVE_OPTION) {
                    File img = fc.getSelectedFile();
                    String caminho = fc.getSelectedFile().getAbsolutePath();
                    try {
                        tfCaminho.setText(caminho);
                        icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                        rotulo.setIcon(icone);
                    } catch (Exception ex) {
                        System.out.println("Erro: " + ex.getMessage());
                    }
                }
            }
        });
        tfCpf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        tfCpf.setText(aux[0]);
                        btnRetrieve.doClick();

                    } else {
                        tfCpf.requestFocus();
                        tfCpf.selectAll();
                    }
                }
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setVisible(true);
    }

    public static void main(String[] args) {
        GUICliente guiCliente = new GUICliente();
    }
}
