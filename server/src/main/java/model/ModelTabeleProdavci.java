/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class ModelTabeleProdavci extends AbstractTableModel {
    private List<Prodavac> korisnici = new ArrayList<>();
    private String[] kolone = {"Ime", "Prezime", "Korisničko ime", "Šifra"};

    public List<Prodavac> getProdavci() {
        return korisnici;
    }

    public void setProdavci(List<Prodavac> korisnici) {
        this.korisnici = korisnici;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleProdavci(List<Prodavac> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public int getRowCount() {
        if (korisnici.isEmpty()) {
            return 0;
        } else {
            return korisnici.size();
        }
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodavac korisnik = korisnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return korisnik.getIme();
            case 1:
                return korisnik.getPrezime();
            case 2:
                return korisnik.getKorisnickoIme();
            case 3:
                return korisnik.getSifra();
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
