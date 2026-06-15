/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Prodavac;

/**
 *
 * @author HP
 */
public class GlavnaFormaKontroler {
    
    private final GlavnaForma gf;
    
    public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
        gf.GlavnaFormaAddActionListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odjava();

            }

            private void odjava() {
                Komunikacija.getInstance().odjaviSe(Koordinator.getInstance().getUlogovaniProdavac());
                System.out.println("Odjavili ste se.");
                gf.dispose();
                Koordinator.getInstance().otvoriLoginFormu();

            }
        });
    }

    public void otvoriFormu() {
        gf.getjLabelIme().setText("Zdravo, " + Koordinator.getInstance().getUlogovaniProdavac().getIme() + " " + Koordinator.getInstance().getUlogovaniProdavac().getPrezime() + "!");
        gf.setVisible(true);
    }
    
}
