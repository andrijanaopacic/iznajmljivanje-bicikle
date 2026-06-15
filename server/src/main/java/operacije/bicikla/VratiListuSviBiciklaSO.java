package operacije.bicikla;

import java.util.ArrayList;
import java.util.List;
import model.Bicikla;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import model.BiciklaSaRiksom;
import operacije.ApstraktnaGenerickaOperacija;

public class VratiListuSviBiciklaSO extends ApstraktnaGenerickaOperacija {

    private List<Bicikla> lista;

    public List<Bicikla> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        // nema posebnih preduslova
    }

    @Override
    protected void izvrsi(Object objekat, Object kljuc) throws Exception {
        lista = new ArrayList<>();
        // Svaki broker.getAll poziv automatski koristi JOIN iz vratiNazivTabele()
        lista.addAll(broker.getAll(new BiciklaZaOdrasle(), ""));
        lista.addAll(broker.getAll(new BiciklaZaDecu(), ""));
        lista.addAll(broker.getAll(new BiciklaSaRiksom(), ""));
    }
}