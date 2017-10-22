/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author asuspc
 */
public class Util {
    
    private static Icon mailIcon;
    private static Icon emptyIcon;
    private static Icon updateIcon;
    
    public static Icon getMailIcon(){
        if (mailIcon == null){
            mailIcon = new ImageIcon(Util.class.getResource("/imagens/mail.png"));
        }
        return mailIcon;
    }
    
    public static Icon getEmptyIcon(){
        if (emptyIcon == null){
            emptyIcon = new ImageIcon();
        }
        return emptyIcon;
    }
    
    public static Icon getUpdateIcon(){
        if (updateIcon == null){
            updateIcon = new ImageIcon(Util.class.getResource("/imagens/update.png"));
        }
        return updateIcon;
    }
    
    public static void MensagemErro(Component owner, String msg){
        JOptionPane.showMessageDialog(owner, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void Mensagem(Component owner, String msg){
        JOptionPane.showMessageDialog(owner, msg, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void centralizaTela(Window tela){
        Dimension bounds = Toolkit.getDefaultToolkit().getScreenSize();
		tela.setLocation((bounds.width-tela.getSize().width)/2,   
                (bounds.height-tela.getSize().height)/2);
    }
}
