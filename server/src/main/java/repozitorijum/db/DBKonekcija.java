/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
/**
 *
 * @author HP
 */
public class DBKonekcija {
    
    private static DBKonekcija instance;
    private Connection connection;

    private DBKonekcija() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(Konfiguracija.getInstance().ucitajPodatak("url"), Konfiguracija.getInstance().ucitajPodatak("username"), Konfiguracija.getInstance().ucitajPodatak("password"));
                connection.setAutoCommit(false);

            }
        } catch (SQLException ex) {
            System.err.println("Greška: Nije moguće uspostaviti vezu sa MySQL serverom.");
        }
    }

    
    public static DBKonekcija getInstance(){
        if(instance == null){
            instance = new DBKonekcija();
        }
        
        return instance;
    }

    public Connection getConnection() throws SQLException, FileNotFoundException, IOException {
       return connection;
    }
    
    public static void resetuj(){
        instance = null;
    }
    
    
}
