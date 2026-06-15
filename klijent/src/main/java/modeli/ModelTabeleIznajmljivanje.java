/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Iznajmljivanje;
import model.ProdavacTermin;

/**
 *
 * @author HP
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {

    private List<Iznajmljivanje> iznajmljivanje = new ArrayList<>();
    private String[] kolone = {"ID","Prodavac", "Kupac", "Ukupan iznos"};

    public List<Iznajmljivanje> getLista() {
        return iznajmljivanje;
    }

    public void setLista(List<Iznajmljivanje> stavke) {
        this.iznajmljivanje = stavke;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> upisnice) {
        this.iznajmljivanje = upisnice;
    }
    
    @Override
    public int getRowCount() {
        if (iznajmljivanje.isEmpty()) {
            return 0;
        } else {
            return iznajmljivanje.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje i = iznajmljivanje.get(rowIndex);
        

        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanje();
            case 1:
                return i.getProdavac();
            case 2:
                return i.getKupac();
            case 3:
                return i.getUkupanIznos();
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

    public void dodajElemmt(Iznajmljivanje i) {
        iznajmljivanje.add(i);
        fireTableDataChanged();
    }
    
}
