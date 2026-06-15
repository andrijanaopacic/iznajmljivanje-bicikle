/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Primalac {
    
    private Socket s;

    public Primalac(Socket s) {
        this.s = s;
    }

    public Object primi() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            try {
                try {
                    return ois.readObject();
                } catch (ClassNotFoundException ex) {
                    System.err.println("GREŠKA: Potrebna klasa za čitanje poruke nije pronađena: " + ex.getMessage());
                }
            } catch (EOFException ex) {
                System.out.println("Klijent se odjavio.");
                s.close();
                s = null;
            }

        } catch (IOException ex) {
            System.err.println("Greška prilikom primanja poruke: " + ex.getMessage());
        }
        return null;
    }
        
}
    
    

