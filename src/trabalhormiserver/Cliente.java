/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author asuspc
 */
public class Cliente {
    
    private static Cliente mySelf;
    public Service _service = null;
    public String ipServer;
    public Integer portServer; 
    public Integer limiteNoticias;
    
    public static Cliente getInstance() throws IOException, FileNotFoundException, NotBoundException{
        if (mySelf == null){
            mySelf = new Cliente();
        }
        return mySelf;
    }
    
    private Cliente() throws FileNotFoundException, IOException, NotBoundException {
        Properties p = new Properties();
        File arquivo = new File( "Parametros.xml" );
        if( arquivo.exists() ) {
            FileInputStream fis = new FileInputStream( arquivo );
            try {
                p.loadFromXML( fis );
                ipServer = p.getProperty("ipServer");
                portServer = Integer.parseInt(p.getProperty("portServer"));                
                if (limiteNoticias == null || limiteNoticias == 0 )
                    limiteNoticias = 50;
            } finally {
                fis.close();
            }
        }
        String _local = "//" + ipServer + ":" + portServer + "/service";                      
        _service = (Service) Naming.lookup(_local);                
    }
    
}
