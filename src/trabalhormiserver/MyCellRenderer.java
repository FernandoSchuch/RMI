/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author asuspc
 */
public class MyCellRenderer extends DefaultTableCellRenderer{
    
    protected void setValue(Object value){        
        //Sempre vai ser uma imagem quando cair aqui
        if (value != null){
            ImageIcon d = (ImageIcon) value;
            setIcon(d);
        } else{
            setText("");
            setIcon(null);
        }
    }
}
