/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokreni;

import forme.ServerskaForma;

/**
 *
 * @author HP
 */
public class PokreniServer {
    
    public static void main(String[] args) {
        try{
            ServerskaForma sf = new ServerskaForma();
            sf.setVisible(true);
            sf.unesiPodatke();
        }catch(Exception e){
            System.err.println("Greška pri pokretanju serverske forme: "+e);
        }
    }
    
}
