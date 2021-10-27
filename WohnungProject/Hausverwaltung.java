import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hausverwaltung{
    private HausverwaltungDAO hausverwaltungDAO;

    public Hausverwaltung(String dateiName) {
        this.hausverwaltungDAO = new HausverwaltungSerializationDAO(dateiName);
    }
    
    public List<Wohnung> getAllFlatData(){
        return this.hausverwaltungDAO.getWohnungen();
    }

    public Wohnung getWohnung(int id){
        return this.hausverwaltungDAO.getWohnungbyId(id);
    }

    public void addNewWohnung(Wohnung newWohnung){
        this.hausverwaltungDAO.saveWohnung(newWohnung);
    }

    public void deleteWohnung(int id){
        this.hausverwaltungDAO.deleteWohnung(id);
    }

    public int allWohnungenCount(){
        return (int) this.hausverwaltungDAO.getWohnungen().stream().count();
    }

    public int EWCount(){
        return (int) this.hausverwaltungDAO.getWohnungen().stream().filter(e -> e instanceof EigentumsWohnung).count();
    }

    public int MWCount(){
        return (int) this.hausverwaltungDAO.getWohnungen().stream().filter(e -> e instanceof MietWohnung).count();
    }

    public double averageMonthCostsAllWohnungen(){
//        double temp = this.hausverwaltungDAO.getWohnungen().stream()
//                .mapToDouble(Wohnung::gesamtKosten)
//                .sum();
        double temp = 0.0;
        for (var w: this.getAllFlatData()) {
            temp += w.gesamtKosten();
        }
        temp /= this.allWohnungenCount();
        return temp;
    }

    public List<Integer> getOldestWohnungen(){
        Wohnung w = this.getAllFlatData().stream()
                .min((e1,e2) -> Integer.compare(Integer.parseInt(e1.getBuildYear()), Integer.parseInt(e2.getBuildYear())))
                .get();

        List<Wohnung> flats = this.hausverwaltungDAO.getWohnungen().stream()
                .filter(e -> e.getBuildYear().equals(w.getBuildYear()))
                .collect(Collectors.toList());

        List<Integer> ids = flats.stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        return ids;
    }
}
