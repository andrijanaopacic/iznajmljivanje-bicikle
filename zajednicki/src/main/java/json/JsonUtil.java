package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    
    public static void serijalizujRacun(Iznajmljivanje iznajmljivanje, String putanja) throws IOException {
        JsonObject racun = new JsonObject();
        racun.addProperty("idIznajmljivanje", iznajmljivanje.getIdIznajmljivanje());
        racun.addProperty("datumGenerisanja", LocalDateTime.now().format(FORMATTER));
        racun.addProperty("ukupanIznos", iznajmljivanje.getUkupanIznos());
        
        try {
            double kurs = vratiKursRsdEur();
            double ukupanIznosEUR = Math.round(iznajmljivanje.getUkupanIznos() * kurs * 100.0) / 100.0;
            racun.addProperty("ukupanIznosEUR", ukupanIznosEUR);
            System.out.println("Kurs RSD/EUR: " + kurs + ", Iznos u EUR: " + ukupanIznosEUR);
        } catch (Exception e) {
            System.err.println("Upozorenje: Nije moguće dobiti kurs valuta: " + e.getMessage());
            racun.addProperty("ukupanIznosEUR", "N/A");
        }

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
    
    
    public static String deserijalizujRacun(String putanja) throws IOException {
        try (FileReader reader = new FileReader(putanja)) {
            JsonObject racun = JsonParser.parseReader(reader).getAsJsonObject();

            StringBuilder sb = new StringBuilder();
            sb.append("=== RAČUN ===\n");
            sb.append("ID iznajmljivanja: ").append(racun.get("idIznajmljivanje").getAsInt()).append("\n");
            sb.append("Datum generisanja: ").append(racun.get("datumGenerisanja").getAsString()).append("\n\n");

            JsonObject prodavac = racun.getAsJsonObject("prodavac");
            sb.append("Prodavac: ").append(prodavac.get("ime").getAsString())
                    .append(" ").append(prodavac.get("prezime").getAsString()).append("\n");

            JsonObject kupac = racun.getAsJsonObject("kupac");
            sb.append("Kupac: ").append(kupac.get("ime").getAsString())
                    .append(" ").append(kupac.get("prezime").getAsString()).append("\n");
            sb.append("Broj lične karte: ").append(kupac.get("brojLicneKarte").getAsString()).append("\n");
            sb.append("Mesto: ").append(kupac.get("mesto").getAsString()).append("\n\n");

            sb.append("--- Stavke ---\n");
            JsonArray stavke = racun.getAsJsonArray("stavkeIznajmljivanja");
            for (int i = 0; i < stavke.size(); i++) {
                JsonObject stavka = stavke.get(i).getAsJsonObject();
                sb.append(i + 1).append(". ")
                        .append(stavka.get("tipBicikle").getAsString()).append(" - ")
                        .append(stavka.get("marka").getAsString()).append(" ")
                        .append(stavka.get("model").getAsString()).append("\n");
                sb.append("   Od: ").append(stavka.get("vremeOd").getAsString())
                        .append(" Do: ").append(stavka.get("vremeDo").getAsString()).append("\n");
                sb.append("   Sati: ").append(stavka.get("brojSati").getAsInt())
                        .append(", Dana: ").append(stavka.get("brojDana").getAsInt()).append("\n");
                sb.append("   Cena: ").append(stavka.get("cena").getAsDouble())
                        .append(" RSD, Iznos: ").append(stavka.get("iznos").getAsDouble()).append(" RSD\n");
            }

            sb.append("\nUkupan iznos: ").append(racun.get("ukupanIznos").getAsDouble()).append(" RSD");
            if (racun.has("ukupanIznosEUR") && !racun.get("ukupanIznosEUR").getAsString().equals("N/A")) {
                sb.append("\nUkupan iznos: ").append(racun.get("ukupanIznosEUR").getAsDouble()).append(" EUR");
            }
            return sb.toString();
        }
        
        
    }
    
    
    public static double vratiKursRsdEur() throws Exception {
        String urlString = "https://open.er-api.com/v6/latest/RSD";

        java.net.URL url = new java.net.URL(urlString);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        StringBuilder response = new StringBuilder();
        try (java.io.BufferedReader in = new java.io.BufferedReader(
                new java.io.InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        
        JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        return rates.get("EUR").getAsDouble();
    }
    
}