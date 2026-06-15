/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Mesto;

/**
 *
 * @author HP
 */
public class ModelTabeleMesto extends AbstractTableModel {

    private List<Mesto> lista = new ArrayList<>();
    private String[] kolone = {"Naziv"};

    public List<Mesto> getLista() {
        return lista;
    }

    public void setLista(List<Mesto> lista) {
        this.lista = lista;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleMesto(List<Mesto> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        if (lista.isEmpty()) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mesto mesto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return mesto.getNaziv();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void refresujPodatke() {
        fireTableDataChanged();
    }
    
}
