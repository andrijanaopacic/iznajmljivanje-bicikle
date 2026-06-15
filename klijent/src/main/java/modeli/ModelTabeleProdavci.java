/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Prodavac;

/**
 *
 * @author HP
 */
public class ModelTabeleProdavci extends AbstractTableModel {
    
private List<Prodavac> prodavci = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "Korisničko ime", "Šifra"};

    public List<Prodavac> getProdavci() {
        return prodavci;
    }

    public void setProdavci(List<Prodavac> prodavci) {
        this.prodavci = prodavci;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleProdavci(List<Prodavac> prodavci) {
        this.prodavci = prodavci;
    }

    @Override
    public int getRowCount() {
        if (prodavci.isEmpty()) {
            return 0;
        } else {
            return prodavci.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodavac prodavac = prodavci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prodavac.getIme();
            case 1:
                return prodavac.getPrezime();
            case 2:
                return prodavac.getKorisnickoIme();
            case 3:
                return prodavac.getSifra();
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

