# Iznajmljivanje bicikli
 
Aplikacija za upravljanje iznajmljivanjem bicikli, razvijena u okviru predmeta **Softverski alati** na Fakultetu organizacionih nauka, Univerziteta u Beogradu.
 
Projekat predstavlja klijent-server desktop aplikaciju u Javi koja prodavcima omogućava da vode evidenciju o kupcima, biciklama (za odrasle, za decu i sa riksom) i iznajmljivanjima, uključujući automatsko generisanje računa.
 
Ovo je nadograđena verzija projekta rađenog na predmetu **Projektovanje softvera** ([originalni repozitorijum](https://github.com/andrijanaopacic/projektovanjeSoftvera)), gde je aplikacija bila bez Maven-a, testova i JavaDoc dokumentacije. Pored uvođenja tih tehnologija, promenjen je i način modelovanja bicikli - umesto jedne klase sa poljem koje označava tip bicikle, uvedeno je nasleđivanje (`Bicikla` kao apstraktna klasa sa konkretnim podklasama za svaki tip). Cilj ove izmene je da dodavanje novog tipa bicikle u budućnosti ne zahteva izmenu postojećih klasa, već samo dodavanje nove podklase i odgovarajuće tabele u bazi. 

## Tehnologije
 
- **Maven** - projekat je organizovan kao multi-module Maven projekat sa tri modula: `zajednicki` (domenske klase, prenosni objekti, JSON utility), `server` (sistemske operacije, pristup bazi, serverska logika) i `klijent` (Swing korisnički interfejs)
- **JUnit Jupiter (JUnit 5)** - testiranje domenskih klasa i sistemskih operacija; Mockito se koristi za mokovanje konekcije na bazu i brokera u testovima sistemskih operacija
- **JavaDoc** - dokumentovane su sve domenske klase i sve sistemske operacije
- **JSON** - perzistencija generisanih računa u JSON fajlove (Gson), plus pozivanje eksternog web servisa za konverziju RSD u EUR
- **Lombok** - korišćen u domenskim klasama za generisanje gettera, settera i konstruktora
- **Stream API i lambda izrazi** - korišćeni u jednoj od sistemskih operacija za iteraciju kroz listu iznajmljivanja

## Domenske klase
 
`Bicikla` (apstraktna), `BiciklaZaOdrasle`, `BiciklaZaDecu`, `BiciklaSaRiksom`, `Kupac`, `Prodavac`, `Mesto`, `Termin`, `ProdavacTermin`, `Iznajmljivanje`, `StavkaIznajmljivanja`
 
Bicikle su modelovane kroz nasleđivanje (Table Per Subclass) - zajednički atributi se čuvaju u tabeli `bicikla`, a specifični atributi svakog tipa u odgovarajućoj podtabeli.
 
## Sistemske operacije
 
Preko 40 sistemskih operacija koje pokrivaju kreiranje, izmenu, brisanje i pretragu za sve domenske entitete, kao i generisanje i prikaz JSON računa.
 
## Pokretanje
 
Build i testovi se izvode isključivo kroz Maven:
 
```
mvn clean install
```
 
Za pokretanje servera i klijenta potrebno je podesiti konekciju na bazu u `server/konfiguracija` i `klijent/konfiguracija`.
 
## Git istorija
 
Projekat je rađen u fazama, svaka na svojoj grani (`json`, `lambda`, `javadoc`, `junit`), koje su naknadno mergovane u `master`. Ključne tačke u razvoju su obeležene tagovima.
 
## O predmetu
 
Predmet: Softverski alati

Fakultet: Fakultet organizacionih nauka, Univerzitet u Beogradu

Autor: Andrijana Opačić
 