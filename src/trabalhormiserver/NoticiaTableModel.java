/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhormiserver;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author asuspc
 */
public class NoticiaTableModel extends AbstractTableModel{
    private ArrayList<Noticia> data;
    private String[] colunas = new String[] {"", "Título", "Data", "Tópico"};
    
    public NoticiaTableModel(){
        data = new ArrayList<Noticia>();       
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    };

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return data.get(rowIndex).icone;
            case 1: return data.get(rowIndex).titulo;
            case 2: return data.get(rowIndex).dataPublicacao;
            case 3: return data.get(rowIndex).topico.nome;
            default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return Image.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
        }
    }
    
    public void setIcone(Icon imagem, int linha){
        data.get(linha).icone = imagem;
    }
    
    public void addNoticia(Noticia n, Boolean first){
        if (n != null){
            if (!n.lida)
               n.icone = Util.getMailIcon();            
            if (first){
                data.add(0, n);
                fireTableDataChanged();
            } else {
                data.add(n);
                fireTableRowsInserted(0, getRowCount()-1);
            }                        
        }
    }
    
    public void addAll(ArrayList<Noticia> noticias){        
        if (noticias != null && noticias.size() > 0){
            for (Noticia n : noticias){
                addNoticia(n, false);
            }
        }
    }
    
    public void removeNoticia(int linha){
        try{
            data.remove(linha); 
            fireTableDataChanged();
        } catch(Exception e){
            //Não faz nada
        }
    }
    
    public void limpar(){
        data.clear();
        fireTableDataChanged();
    }
    
    public String getTextoNoticia(int indice){
        return data.get(indice).texto;
    }
    
    public Integer getCodigoNoticia(int indice){
        return data.get(indice).codigo;
    }
    
    public Noticia getNoticiaAtual(int indice){
        return data.get(indice);
    }
    
    public Integer getLinhaNoticia(int indice){
        if (data.size() > 0)
            for (int i = 0; i < data.size(); i++)
                if (data.get(i).codigo == indice)
                    return i;
        return -1;
       
    }
}
