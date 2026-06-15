/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class Odgovor implements Serializable {
    
    private Object odgovor;
    
    public Odgovor() {
    }
    
    public Odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
     
     
    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
     
     
    
//    private TipOdgovora tipOdgovora;
//    private Object rezultat;
//    private Exception exception;
//
//    public Odgovor() {
//    }
//
//    public TipOdgovora getTipOdgovora() {
//        return tipOdgovora;
//    }
//
//    public void setTipOdgovora(TipOdgovora tipOdgovora) {
//        this.tipOdgovora = tipOdgovora;
//    }
//
//    public Object getRezultat() {
//        return rezultat;
//    }
//
//    public void setRezultat(Object rezultat) {
//        this.rezultat = rezultat;
//    }
//
//    public Exception getException() {
//        return exception;
//    }
//
//    public void setException(Exception exception) {
//        this.exception = exception;
//    }

    
}
