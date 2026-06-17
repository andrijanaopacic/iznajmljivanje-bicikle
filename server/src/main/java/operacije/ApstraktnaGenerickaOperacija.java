/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repozitorijum.db.impl.DBRepozitorijumGenericki;
import repozitorijum.Repozitorijum;
import repozitorijum.db.DBRepozitorijum;

/**
 * Predstavlja apstraktnu sistemsku operaciju koja definise zajednicki tok
 * izvrsavanja za sve konkretne sistemske operacije (SO) u sistemu.
 * Tok izvrsavanja se sastoji od provere preduslova, otvaranja transakcije,
 * izvrsavanja konkretne logike operacije i potvrdjivanja transakcije. Ukoliko
 * dodje do greske u bilo kojem koraku, transakcija se ponistava i izuzetak
 * se prosledjuje pozivajucem kodu.
 *
 * @author Andrijana Opacic
 * @see Repozitorijum
 * @see DBRepozitorijumGenericki
 */
public abstract class ApstraktnaGenerickaOperacija {
    
	/** Broker preko kojeg se vrsi komunikacija sa bazom podataka. */
    protected final Repozitorijum broker;

    /**
     * Konstruktor koji inicijalizuje brokera kao novu instancu
     * {@link DBRepozitorijumGenericki}.
     */
    public ApstraktnaGenerickaOperacija() {
        this.broker = new DBRepozitorijumGenericki();
    }
    
    /**
     * Izvrsava kompletan tok sistemske operacije: provera preduslova,
     * otvaranje transakcije, izvrsavanje konkretne logike i potvrdjivanje
     * transakcije. Ukoliko dodje do greske u bilo kojem koraku, transakcija
     * se ponistava i izuzetak se prosledjuje dalje.
     *
     * @param objekat objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni parametar koji koristi konkretna operacija, ili null
     *        ako nije potreban
     * @throws Exception ako preduslovi nisu ispunjeni, ako dodje do greske
     *         prilikom rada sa bazom podataka, ili ako izvrsavanje operacije
     *         baci izuzetak
     */
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

    /**
     * Proverava da li su ispunjeni preduslovi za izvrsavanje konkretne
     * sistemske operacije. Implementira se u svakoj konkretnoj operaciji
     * u zavisnosti od specificnih pravila te operacije.
     *
     * @param objekat objekat nad kojim se provjeravaju preduslovi
     * @throws Exception ako preduslovi nisu ispunjeni
     */
    protected abstract void preduslovi(Object objekat) throws Exception;

    /**
     * Otvara transakciju nad bazom podataka preko brokera.
     *
     * @throws Exception ako dodje do greske prilikom otvaranja konekcije
     */
    private void zapocniTransakciju() throws Exception {
        ((DBRepozitorijum) broker).connect();
    }

    /**
     * Izvrsava konkretnu logiku sistemske operacije. Implementira se u svakoj
     * konkretnoj operaciji u zavisnosti od njene poslovne logike.
     *
     * @param objekat objekat nad kojim se izvrsava operacija
     * @param kljuc dodatni parametar koji koristi konkretna operacija, ili null
     *        ako nije potreban
     * @throws Exception ako dodje do greske prilikom izvrsavanja operacije
     */
    protected abstract void izvrsi(Object objekat, Object kljuc) throws Exception;

    /**
     * Potvrdjuje (komituje) transakciju nad bazom podataka preko brokera.
     *
     * @throws Exception ako dodje do greske prilikom potvrdjivanja transakcije
     */
    private void potvrdiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).commit();
    }

    /**
     * Ponistava (rollback) transakciju nad bazom podataka preko brokera.
     * Poziva se kada dodje do greske u toku izvrsavanja sistemske operacije.
     *
     * @throws Exception ako dodje do greske prilikom ponistavanja transakcije
     */
    private void ponistiTransakciju() throws Exception {
        ((DBRepozitorijum) broker).rollback();
    }

    /**
     * Zatvara konekciju sa bazom podataka preko brokera. Trenutno se ne
     * poziva u toku izvrsavanja operacije (kod je zakomentarisan u
     * {@link #izvrsiOperaciju(Object, Object)}).
     *
     * @throws Exception ako dodje do greske prilikom zatvaranja konekcije
     */
    private void ugasiTransakciju()throws Exception {
        ((DBRepozitorijum) broker).disconnect();
    }
    
}
