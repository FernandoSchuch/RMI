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
public class TopicoTableModel extends AbstractTableModel {

    private ArrayList<Topico> data;
    private String[] colunas = new String[] {"", "Tópico"};
    
    public TopicoTableModel(){
        data = new ArrayList<Topico>();       
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
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
            case 0: return data.get(rowIndex).selecionado;
            case 1: return data.get(rowIndex).nome;
            default: return null;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){        
        Topico t = data.get(rowIndex);
        if (columnIndex == 0){
            t.selecionado = (Boolean) aValue;
        } else {
            t.nome = (String) aValue;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    } 
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out of bounds");
        }
    }
    
    public void addTopico(Topico t, Boolean first){
        if (t != null){
            if (first){
                data.add(0, t);
                fireTableDataChanged();
            } else {
                data.add(t);
                Integer ultimaLinha = getRowCount() + 1;
                fireTableRowsInserted(ultimaLinha, ultimaLinha);
            }       
            t.selecionado = false;
        }
    }
    
    public void addAll(ArrayList<Topico> topicos){
        if (topicos != null && topicos.size() > 0){
            for (Topico t : topicos){
                addTopico(t, false);
            }
        }
    }
    
    public ArrayList<Topico> getTopicosSelecionados(){
        ArrayList<Topico> topicos = new ArrayList<>();
        for (Topico t : data){
            if (t.selecionado)
                topicos.add(t);
        }
        return topicos;
    }
    
    public void removeTopico(int linha){
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
    
    public Integer getCodigoTopico(int indice){
        return data.get(indice).codigo;
    }
}
