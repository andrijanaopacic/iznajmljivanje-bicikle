/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum.db;
import repozitorijum.Repozitorijum;
/**
 *
 * @author HP
 */
public interface DBRepozitorijum<T> extends Repozitorijum<T>{
    
    default public void connect() throws Exception{
        DBKonekcija.getInstance().getConnection();
    }
    
    default public void disconnect() throws Exception{
        if (DBKonekcija.getInstance().getConnection() == null) {
            System.err.println("Upozorenje: Konekcija nije uspostavljena.");
        } else {
            DBKonekcija.getInstance().getConnection().close();
        }
    }
    
    default public void commit() throws Exception{
        if (DBKonekcija.getInstance().getConnection() == null) {
            System.err.println("Upozorenje: Konekcija nije uspostavljena.");
        } else {
            DBKonekcija.getInstance().getConnection().commit();
        }
    }
    
    default public void rollback() throws Exception{
        if (DBKonekcija.getInstance().getConnection() == null) {
            System.err.println("Upozorenje: Konekcija nije uspostavljena.");
        } else {

            DBKonekcija.getInstance().getConnection().rollback();
        }
    }
    
}
