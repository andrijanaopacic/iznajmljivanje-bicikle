/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repozitorijum.db.impl.DBRepozitorijumGenericki;
import repozitorijum.Repozitorijum;
import repozitorijum.db.DBRepozitorijum;

/**
 *
 * @author HP
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DBRepozitorijumGenericki();
    }
    
    public final void izvrsiOperaciju(Object objekat, java.lang.Object kljuc) throws Exception {
        try{
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsi(objekat,kljuc);
            potvrdiTransakciju();
        }catch (Exception e){
            ponistiTransakciju();
            throw e;
            
        }
//        finally{
//            ugasiTransakciju();
//        }
        }

    protected abstract void preduslovi(Object objekat) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DBRepozitorijum) broker).connect();
    }

    protected abstract void izvrsi(Object objekat, Object kljuc) throws Exception;

    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).rollback();
    }

    private void ugasiTransakciju()throws Exception {
        ((DBRepozitorijum) broker).disconnect();
    }
    
}
