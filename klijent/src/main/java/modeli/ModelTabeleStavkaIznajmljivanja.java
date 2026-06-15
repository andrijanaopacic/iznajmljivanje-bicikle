package modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Bicikla;
import model.BiciklaZaDecu;
import model.BiciklaZaOdrasle;
import model.BiciklaSaRiksom;
import model.StavkaIznajmljivanja;

public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel {

    private List<StavkaIznajmljivanja> stavke = new ArrayList<>();
    private String[] kolone = {"ID", "Tip bicikle", "Broj dana", "Broj sati", "Cena sata", "Ukupan iznos"};

    public List<StavkaIznajmljivanja> getStavke() { return stavke; }
    public void setStavke(List<StavkaIznajmljivanja> stavke) { this.stavke = stavke; }
    public String[] getKolone() { return kolone; }
    public void setKolone(String[] kolone) { this.kolone = kolone; }

    public ModelTabeleStavkaIznajmljivanja(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja stavka = stavke.get(rowIndex);
        Bicikla b = stavka.getBicikla();
        switch (columnIndex) {
            case 0: return stavka.getIdStavkaIznajmljivanja();
            case 1:
                if (b instanceof BiciklaZaOdrasle) return "Za odrasle";
                if (b instanceof BiciklaZaDecu) return "Za decu";
                if (b instanceof BiciklaSaRiksom) return "Sa riksom";
                return "N/A";
            case 2: return stavka.getBrojDana();
            case 3: return stavka.getBrojSati();
            case 4: return stavka.getCena();
            case 5: return stavka.getIznos();
            default: return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void refresujPodatke() {
        fireTableDataChanged();
    }

    public void dodajElement(StavkaIznajmljivanja sp) {
        stavke.add(sp);
        fireTableDataChanged();
    }
}