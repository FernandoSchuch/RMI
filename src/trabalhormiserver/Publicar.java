/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asuspc
 */
public class Publicar extends javax.swing.JDialog implements KeyListener {

    private Usuario usuarioAtual;
    private ArrayList<Topico> topicos; 
    public Publicar(java.awt.Frame parent, boolean modal, Usuario user) {
        super(parent, modal);
        this.usuarioAtual = user;        
        initComponents();
        Util.centralizaTela(this);
        setTitle("Publicar nova notícia");        
        cbTopicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfTopicoNovo.setEnabled(cbTopicos.getSelectedIndex() == 0);
                if (!tfTopicoNovo.isEnabled())
                    tfTopicoNovo.setText("");
            }
        });   
        AtualizaTopicos();
        lbRestante.setText("180");
        taTexto.addKeyListener(this);
        setTitle("Publique uma nova notícia");
        setVisible(true);
    }
    
    private void AtualizaTopicos() {
        try {
            cbTopicos.removeAllItems();
            cbTopicos.addItem("Criar novo tópico");            
            topicos = Cliente.getInstance()._service.getTopicos();            
            for (Topico t : topicos){
               cbTopicos.addItem(t.codigo + ". " + t.nome);
            }
        } catch (Exception e){
            btPublicar.setEnabled(false);
            Util.MensagemErro(this, "Não foi possível carregar os tópicos: " + e.getMessage());
        }
    }
    
    public static void main(String args[]){
        new Publicar(null, true, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbTopicos = new javax.swing.JComboBox<>();
        tfTitulo = new javax.swing.JTextField();
        btPublicar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taTexto = new javax.swing.JTextArea();
        tfTopicoNovo = new javax.swing.JTextField();
        lbRestante = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cbTopicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTopicosActionPerformed(evt);
            }
        });

        tfTitulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfTitulo.setToolTipText("");
        tfTitulo.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        tfTitulo.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        tfTitulo.setName(""); // NOI18N
        tfTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTituloActionPerformed(evt);
            }
        });

        btPublicar.setText("Enviar");
        btPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPublicarActionPerformed(evt);
            }
        });

        jLabel2.setText("Título");

        jLabel3.setText("Tópico");

        taTexto.setColumns(20);
        taTexto.setLineWrap(true);
        taTexto.setRows(5);
        jScrollPane1.setViewportView(taTexto);

        lbRestante.setText("jLabel1");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbRestante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btPublicar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(tfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfTopicoNovo)
                                    .addComponent(cbTopicos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbTopicos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTopicoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btPublicar)
                    .addComponent(lbRestante))
                .addContainerGap())
        );

        tfTitulo.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTopicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTopicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTopicosActionPerformed

    private void tfTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTituloActionPerformed

    private void btPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPublicarActionPerformed
        try{
            String titulo = tfTitulo.getText();
            Topico topic;
            if (cbTopicos.getSelectedIndex() == 0){
                topic = new Topico(tfTopicoNovo.getText());
            } else {
                topic = topicos.get(cbTopicos.getSelectedIndex() - 1);
            }
            String texto  = taTexto.getText();
            if (titulo.trim().equals("")){
                Util.MensagemErro(this, "Título deve ser informado.");
            } else if (topic == null){
                Util.MensagemErro(this, "Tópico deve ser informado.");                
            } else if (topic.codigo == null && topic.nome.trim().equals("")){
                Util.MensagemErro(this, "Descrição do tópico deve ser informada quando um novo tópico for criado.");
            } else if (texto.trim().equals("")){
                Util.MensagemErro(this, "Texto deve ser informado.");
            } else if (texto.length() > 180){
                Util.MensagemErro(this, "Texto não pode ser maior que 180 caracteres.");
            } else {
               Cliente.getInstance()._service.publicar(new Noticia(titulo, texto, topic, (Escritor) usuarioAtual));    
               Util.Mensagem(this, "Notícia publicada com sucesso.");
               limparCampos();
            }
        } catch(Exception e){
            Util.MensagemErro(this, "Erro ao enviar notícia: " + e.getMessage());
        }
    }//GEN-LAST:event_btPublicarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AtualizaTopicos();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void limparCampos(){
        tfTitulo.setText("");
        taTexto.setText("");     
    }
    
    private void aceitaTecla(KeyEvent e){
        /*if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            //odd: the Object param of getContents is not currently used
            Transferable contents = clipboard.getContents(null);
            boolean hasTransferableText =
              (contents != null) &&
              contents.isDataFlavorSupported(DataFlavor.stringFlavor);
            if (hasTransferableText) {              
                try {
                    String s = (String)contents.getTransferData(DataFlavor.stringFlavor);
                    if (s.length() > 180-taTexto.getText().length()){
                        e.consume();
                    }
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(Publicar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Publicar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }*/   
        lbRestante.setText(String.valueOf(180-taTexto.getText().length()));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPublicar;
    private javax.swing.JComboBox<String> cbTopicos;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbRestante;
    private javax.swing.JTextArea taTexto;
    private javax.swing.JTextField tfTitulo;
    private javax.swing.JTextField tfTopicoNovo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        aceitaTecla(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {        
        aceitaTecla(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {        
    }
}
