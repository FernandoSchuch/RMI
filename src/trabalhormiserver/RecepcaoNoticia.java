/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asuspc
 */
public class RecepcaoNoticia implements Runnable{
    private ServerSocket server;
    private NoticiaTableModel tm;
    private ArrayList<Noticia> noticias;
    private boolean executar = true;
    
    public RecepcaoNoticia(ServerSocket server, NoticiaTableModel tm, ArrayList<Noticia> noticias){
        this.server = server;
        this.tm = tm;
        this.noticias = noticias;
    }    
    
    @Override
    public void run(){        
        try {
            while (executar){
                Socket cliente = server.accept();
                Scanner s = new Scanner(cliente.getInputStream());
                while (s.hasNextLine()) {
                    System.out.println("Rodando");
                    novaNoticia(s.nextLine());
                }      
            }
        } catch (IOException ex) {
            Logger.getLogger(RecepcaoNoticia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private synchronized void novaNoticia(String msg){
        Noticia n = NoticiaProtocol.NoticiaProtocol(msg);
        tm.addNoticia(n, true);        
    }
    
    public void parar(){
        executar = false;
    }
    
}
