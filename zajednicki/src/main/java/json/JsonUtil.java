package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility klasa za serijalizaciju i deserijalizaciju JSON fajlova.
 * Koristi Gson biblioteku za konverziju Java objekata u JSON format i obrnuto.
 */
public class JsonUtil {

    private static final DateTimeFormatter FORMATTER =  DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

    /**
     * Serijalizuje iznajmljivanje kao racun u JSON fajl.
     * Rucno gradi JSON strukturu da bi izbegao probleme sa LocalDateTime.
     *
     * @param iznajmljivanje iznajmljivanje koje se serijalizuje
     * @param putanja putanja do JSON fajla
     * @throws IOException ako dodje do greske pri pisanju fajla
     */
    public static void serijalizujRacun(Iznajmljivanje iznajmljivanje, String putanja) throws IOException {
        JsonObject racun = new JsonObject();
        racun.addProperty("idIznajmljivanje", iznajmljivanje.getIdIznajmljivanje());
        racun.addProperty("datumGenerisanja", LocalDateTime.now().format(FORMATTER));
        racun.addProperty("ukupanIznos", iznajmljivanje.getUkupanIznos());

        JsonObject prodavac = new JsonObject();
        prodavac.addProperty("ime", iznajmljivanje.getProdavac().getIme());
        prodavac.addProperty("prezime", iznajmljivanje.getProdavac().getPrezime());
        prodavac.addProperty("korisnickoIme", iznajmljivanje.getProdavac().getKorisnickoIme());
        racun.add("prodavac", prodavac);

        JsonObject kupac = new JsonObject();
        kupac.addProperty("ime", iznajmljivanje.getKupac().getIme());
        kupac.addProperty("prezime", iznajmljivanje.getKupac().getPrezime());
        kupac.addProperty("brojLicneKarte", iznajmljivanje.getKupac().getBrojLicneKarte());
        kupac.addProperty("mesto", iznajmljivanje.getKupac().getMesto().getNaziv());
        racun.add("kupac", kupac);

        JsonArray stavke = new JsonArray();
        for (StavkaIznajmljivanja s : iznajmljivanje.getListaStavkiIznajmljivanja()) {
            JsonObject stavka = new JsonObject();
            stavka.addProperty("idStavka", s.getIdStavkaIznajmljivanja());
            stavka.addProperty("marka", s.getBicikla().getMarka());
            stavka.addProperty("model", s.getBicikla().getModel());
            stavka.addProperty("tipBicikle", s.getBicikla().toString());
            stavka.addProperty("vremeOd", s.getVremeOd().format(FORMATTER));
            stavka.addProperty("vremeDo", s.getVremeDo().format(FORMATTER));
            stavka.addProperty("brojSati", s.getBrojSati());
            stavka.addProperty("brojDana", s.getBrojDana());
            stavka.addProperty("cena", s.getCena());
            stavka.addProperty("iznos", s.getIznos());
            stavke.add(stavka);
        }
        racun.add("stavkeIznajmljivanja", stavke);

        java.io.File folder = new java.io.File(putanja).getParentFile();
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }

        try (FileWriter writer = new FileWriter(putanja)) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(racun));
        }
    }
}