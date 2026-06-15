/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokreni;


import java.io.FileInputStream;
import java.io.IOException; 
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author HP
 */
public class PokreniKlijent {
    
    public static void main(String[] args) {
        
        Koordinator.getInstance().otvoriLoginFormu();
        
    }

   
    
}
