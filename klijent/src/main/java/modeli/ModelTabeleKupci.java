/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kupac;

/**
 *
 * @author HP
 */
public class ModelTabeleKupci extends AbstractTableModel{
    private List<Kupac> kupci = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "Broj lične karte", "Mesto"};

    public List<Kupac> getLista() {
        return kupci;
    }

    public void setLista(List<Kupac> kupci) {
        this.kupci = kupci;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }

    @Override
    public int getRowCount() {
        if (kupci.isEmpty()) {
            return 0;
        } else {
            return kupci.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kupac kupac = kupci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kupac.getIme();
            case 1:
                return kupac.getPrezime();
            case 2:
                return kupac.getBrojLicneKarte();
            case 3:
                return kupac.getMesto();
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
