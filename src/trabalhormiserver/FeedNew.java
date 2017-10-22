/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author asuspc
 */
public class FeedNew extends JFrame implements ActionListener{
    
    private Usuario usuarioAtual;
    private JButton btPublicar  = new JButton("Publicar");
    private JButton btInscrever = new JButton("Inscrever");
    private JButton btDesinscrever = new JButton("Desinscrever");
    private JButton btConsultar = new JButton("Consultar");
    private JButton btAtualizar = new JButton("");
    private JLabel lbTopicos = new JLabel("Tópico:");
    private JLabel lbDataIni = new JLabel("Data inicial:");
    private JLabel lbDataFim = new JLabel("Data final:");
    private JLabel lbUsuario = new JLabel("Usuário: ");
    private JComboBox cbTopicos = new JComboBox<String>();
    private JCheckBox cbUltima = new JCheckBox("Última notícia");
    private JFormattedTextField tfDataIni = new JFormattedTextField();
    private JFormattedTextField tfDataFim = new JFormattedTextField();   
    private JTable tbNoticias = new JTable();
    private JScrollPane spNoticias = new JScrollPane(tbNoticias);
    private NoticiaTableModel tmNoticias = new NoticiaTableModel();
    private JTextArea taTexto = new JTextArea();
    private ArrayList<Noticia> noticias = new ArrayList<Noticia>();
    private ArrayList<Topico> arrTopicos = new ArrayList<Topico>();
    
    public FeedNew(Usuario user){
        usuarioAtual = user;
        setTitle("Leia suas notícias a qualquer momento");
        setLayout(null);        
        inicializaComponentes();
        Util.centralizaTela(this);    
        criaSocket();
        AtualizaTopicos();        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void criaSocket(){
        if (usuarioAtual != null){
            if (usuarioAtual instanceof Leitor && !usuarioAtual.login.equals("visitante")){
                try {                
                    ServerSocket server = new ServerSocket(usuarioAtual.porta);
                    Thread t = new Thread(new RecepcaoNoticia(server, tmNoticias, noticias));
                    t.start();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao criar Socket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void AtualizaTopicos(){
        try {
            cbTopicos.removeAllItems();                                               
            arrTopicos = Cliente.getInstance()._service.getTopicos();                     
            for (Topico t : arrTopicos){
               cbTopicos.addItem(t.codigo + ". " + t.nome);
            }            
        } catch (Exception e){
            btPublicar.setEnabled(false);
            Util.MensagemErro(this, "Não foi possível carregar os tópicos: " + e.getMessage());
        } 
    }
    
    private boolean validouData() throws Exception{                
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(tfDataIni.getText());                    
        } catch (ParseException ex) {
            throw new Exception("Data inicial inválida.");            
        }
        try {
            df.parse(tfDataFim.getText());                    
        } catch (ParseException ex) {
            throw new Exception("Data final inválida.");
        }
        return true;
    }
    
    private void inicializaComponentes(){
        int leftInicial = 10, height = 25, margem = 10, leftBotao = 600, tamLabels = 80;  
        btPublicar.setBounds(leftBotao, 20, 120, 50);
        btInscrever.setBounds(leftBotao, 80, 120, 50);
        btDesinscrever.setBounds(leftBotao, 140, 120, 50);
        if (usuarioAtual instanceof Leitor){
            btPublicar.setVisible(false); 
            if (usuarioAtual.login.equals("visitante")){
                btDesinscrever.setVisible(false);
                btInscrever.setVisible(false);
            }
            lbTopicos.setBounds(leftInicial, 20,  tamLabels, height);            
            cbTopicos.setBounds(leftInicial + lbTopicos.getWidth() + margem, lbTopicos.getY(), 150, height);
            btAtualizar.setBounds(cbTopicos.getX() + cbTopicos.getWidth() + margem, cbTopicos.getY(), height, height);
            btAtualizar.setIcon(Util.getUpdateIcon());
            cbUltima.setBounds(cbTopicos.getX(), cbTopicos.getY() + height + margem, 150, height);
            lbDataIni.setBounds(leftInicial, cbUltima.getY() + height + margem, tamLabels, height);
            tfDataIni.setBounds(leftInicial + lbDataIni.getWidth() + margem, lbDataIni.getY(), 80, height);
            lbDataFim.setBounds(leftInicial, lbDataIni.getY() + height + margem, tamLabels, height);
            tfDataFim.setBounds(leftInicial + lbDataFim.getWidth() + margem, lbDataFim.getY(), 80, height);
            btConsultar.setBounds(tfDataFim.getX(), tfDataFim.getY() + height + margem, 100, height);
        } else if (usuarioAtual instanceof Escritor){
            btDesinscrever.setVisible(false);
            btInscrever.setVisible(false);
            lbDataIni.setVisible(false);
            lbDataFim.setVisible(false);
            tfDataIni.setVisible(false);
            tfDataFim.setVisible(false);            
            cbUltima.setVisible(false);
            btConsultar.setText("Consultar todas");
            btConsultar.setBounds(leftInicial, 10, 150, height);
            lbTopicos.setBounds(leftInicial + btConsultar.getWidth() + margem, btConsultar.getY(), tamLabels, height);
            cbTopicos.setBounds(lbTopicos.getX() + lbTopicos.getWidth() + margem, lbTopicos.getY(), 150, height);
            btAtualizar.setBounds(cbTopicos.getX() + cbTopicos.getWidth() + margem, cbTopicos.getY(), height, height);
            btAtualizar.setIcon(Util.getUpdateIcon());            
        }
        tmNoticias = new NoticiaTableModel();
        tbNoticias.setModel(tmNoticias);        
        tbNoticias.setRowHeight(25);
        TableColumnModel ColumnModel = tbNoticias.getColumnModel();        
        ColumnModel.getColumn(0).setCellRenderer(new MyCellRenderer());
        ColumnModel.getColumn(0).setPreferredWidth(30);
        ColumnModel.getColumn(1).setPreferredWidth(240);
        ColumnModel.getColumn(2).setPreferredWidth(140);
        ColumnModel.getColumn(3).setPreferredWidth(140);
        taTexto.setEditable(false);
        taTexto.setLineWrap(true);
	taTexto.setWrapStyleWord(true);
        tbNoticias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tbNoticias.getSelectedRow() > -1) {
                    taTexto.setText(tmNoticias.getTextoNoticia(tbNoticias.getSelectedRow()));                    
                    tmNoticias.setIcone(Util.getEmptyIcon(), tbNoticias.getSelectedRow());
                    try {
                        if (!usuarioAtual.login.equals("visitante")){
                            Cliente.getInstance()._service.marcarComoLida(tmNoticias.getCodigoNoticia(tbNoticias.getSelectedRow()), usuarioAtual.login);                                
                        }
                    } catch (Exception e){
                        Util.MensagemErro(null, "Erro ao marcar notícia como lida: " + e.getMessage());
                    }
                    tmNoticias.fireTableCellUpdated(tbNoticias.getSelectedRow(), 0);                    
                } else {
                    taTexto.setText("");
                }
            }
        });        
        JButton yTabela;
        if (btPublicar.isVisible())
            yTabela = btPublicar;
        else
            yTabela = btConsultar;
        tbNoticias.setBounds(leftInicial, yTabela.getY() + yTabela.getHeight() + 20, 550, 200);
        spNoticias.setBounds(tbNoticias.getBounds());
        taTexto.setBounds(tbNoticias.getX() + tbNoticias.getWidth() + margem, tbNoticias.getY(), 250, 200);        
        lbUsuario.setBounds(leftInicial, tbNoticias.getY() + tbNoticias.getHeight() + margem, 300, height);
        lbUsuario.setText(lbUsuario.getText() + usuarioAtual.login);
        lbTopicos.setHorizontalAlignment(JLabel.RIGHT);
        lbDataIni.setHorizontalAlignment(JLabel.RIGHT);
        lbDataFim.setHorizontalAlignment(JLabel.RIGHT);
        Container container = getContentPane();
        container.add(btPublicar);
        container.add(btInscrever);
        container.add(btDesinscrever);
        container.add(btConsultar);
        container.add(btAtualizar);
        container.add(lbTopicos);
        container.add(lbDataIni);
        container.add(lbDataFim);
        container.add(lbUsuario);
        container.add(cbTopicos);
        if (!usuarioAtual.login.equals("visitante"))
            container.add(cbUltima);
        container.add(tfDataIni);
        container.add(tfDataFim);        
        container.add(spNoticias);        
        container.add(taTexto);
        
        btConsultar.addActionListener(this);
        btPublicar.addActionListener(this);
        btInscrever.addActionListener(this);
        btDesinscrever.addActionListener(this);
        btAtualizar.addActionListener(this);
        cbUltima.addActionListener(this);
        
        try {
            MaskFormatter mascara1 = new MaskFormatter("##/##/####");
            mascara1.install(tfDataIni);            
            MaskFormatter mascara2 = new MaskFormatter("##/##/####");
            mascara2.install(tfDataFim);            
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setSize(850, lbUsuario.getY() + lbUsuario.getHeight() + 50);        
    }
    
    public void consultar(){
        Topico topic   = arrTopicos.get(cbTopicos.getSelectedIndex());        
        noticias.clear();
        try{
            tmNoticias.limpar();
            if (usuarioAtual instanceof Escritor) {
                noticias = Cliente.getInstance()._service.getTodasNoticias(usuarioAtual.login);
            } else {
                if (cbUltima.isSelected()){
                    Noticia n = Cliente.getInstance()._service.getUltimaNoticia(usuarioAtual.login, topic);
                    if (n != null) {
                        noticias.add(n);
                    }
                } else {
                    if (validouData()){                        
                        noticias = Cliente.getInstance()._service.getNoticias(usuarioAtual.login, topic, tfDataIni.getText(), tfDataFim.getText());
                    }
                }
            }
            if (noticias.size() > 0){
               tmNoticias.addAll(noticias);            
            } else {
               Util.Mensagem(this, "Não há notícias a serem exibidas.");
            }
        } catch(Exception e){
            Util.MensagemErro(this, "Erro ao consultar notícias: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btConsultar){
            consultar();
        } else if (source == btPublicar) {
            new Publicar(this, true, usuarioAtual);
        } else if (source == btInscrever) {
            new InscricaoTopicos(this, true, usuarioAtual, true);
        } else if (source == btDesinscrever) {
            new InscricaoTopicos(this, true, usuarioAtual, false);
        } else if (source == btAtualizar) {
            AtualizaTopicos();
        } else if (source == cbUltima){
            tfDataIni.setEnabled(!cbUltima.isSelected());
            tfDataFim.setEnabled(!cbUltima.isSelected());
        }
    }
    
}
