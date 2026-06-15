package modeli;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Bicikla;
import model.BiciklaZaOdrasle;
import model.BiciklaZaDecu;
import model.BiciklaSaRiksom;

public class ModelTabeleBicikla extends AbstractTableModel {

    private List<Bicikla> bicikle = new ArrayList<>();
    private String[] kolone = {"Tip", "Marka", "Model", "Boja", "Cena sata", "Cena dana", "Spec. atributi"};

    public List<Bicikla> getLista() {
        return bicikle;
    }

    public void setLista(List<Bicikla> bicikle) {
        this.bicikle = bicikle;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public ModelTabeleBicikla(List<Bicikla> bicikle) {
        this.bicikle = bicikle;
    }

    @Override
    public int getRowCount() {
        return bicikle.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bicikla b = bicikle.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (b instanceof BiciklaZaOdrasle) return "Za odrasle";
                if (b instanceof BiciklaZaDecu) return "Za decu";
                if (b instanceof BiciklaSaRiksom) return "Sa riksom";
                return "N/A";
            case 1:
                return b.getMarka();
            case 2:
                return b.getModel();
            case 3:
                return b.getBoja();
            case 4:
                return b.getCenaPoSatu();
            case 5:
                return b.getCenaPoDanu();
            case 6:
                if (b instanceof BiciklaZaOdrasle bo) {
                    return "Točkovi: " + bo.getVelicinaTockova() + ", Brzine: " + bo.getBrojBrzina();
                }
                if (b instanceof BiciklaZaDecu bd) {
                    return "Točkovi: " + bd.getVelicinaTockova() + ", Brzine: " + bd.getBrojBrzina()
                            + ", Pomoćni: " + (bd.isPomocniTockovi() ? "Da" : "Ne");
                }
                if (b instanceof BiciklaSaRiksom br) {
                    return "Sedišta: " + br.getBrojSedista() + ", Tip rikse: " + br.getTipRikse()
                            + ", Max nosivost: " + br.getMaxNosivost();
                }
                return "N/A";
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void refresujPodatke() {
        fireTableDataChanged();
    }
}