package operacije.bicikla;

import model.Bicikla;
import operacije.ApstraktnaGenerickaOperacija;

public class PretraziBiciklaSO extends ApstraktnaGenerickaOperacija {

    private Bicikla bicikla;

    public Bicikla getBicikla() {
        return bicikla;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Bicikla)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        Bicikla bicikla = (Bicikla) objekat;
        if (bicikla.getIdBicikla() <= 0) {
            throw new Exception("ID bicikle nije ispravan.");
        }
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        Bicikla bicikla = (Bicikla) objekat;
        // WHERE koristi bicikla.idBicikla jer je primarni kljuc u roditeljskoj tabeli
        String upit = " WHERE bicikla.idBicikla = " + bicikla.getIdBicikla();
        this.bicikla = (Bicikla) broker.getObject(bicikla, upit);
    }
}