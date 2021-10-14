import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hausverwaltung{
    private HausverwaltungDAO hausverwaltungDAO;

    public List<Wohnung> getAllFlatData(){
        return hausverwaltungDAO.getWohnungen();
    }

    public Wohnung getWohnung(int id){
        return hausverwaltungDAO.getWohnungbyId(id);
    }

    public void addNewWohnung(Wohnung newWohnung){
        hausverwaltungDAO.saveWohnung(newWohnung);
    }

    public void deleteWohnung(int id){
        hausverwaltungDAO.deleteWohnung(id);
    }

    public int allWohnungenCount(){
        return (int) hausverwaltungDAO.getWohnungen().stream().count();
    }

    public int EWCount(){
        return (int) hausverwaltungDAO.getWohnungen().stream().filter(e -> e instanceof EigentumsWohnung).count();
    }

    public int MWCount(){
        return (int) hausverwaltungDAO.getWohnungen().stream().filter(e -> e instanceof MietWohnung).count();
    }

    public double averageMonthCostsAllWohnungen(){
        double temp = hausverwaltungDAO.getWohnungen().stream()
                .mapToDouble(Wohnung::gesamtKosten)
                .sum();
        temp /= this.allWohnungenCount();
        return temp;
    }

    public List<Integer> getOldestWohnungen(){
        Wohnung w = hausverwaltungDAO.getWohnungen()
                .stream()
                .min((e1,e2) -> Integer.compare(Integer.parseInt(e1.getBuildYear()), Integer.parseInt(e2.getBuildYear())))
                .get();

        List<Wohnung> flats = hausverwaltungDAO.getWohnungen().stream()
                .filter(e -> e.getId() == w.getId())
                .collect(Collectors.toList());
        List<Integer> ids = flats.stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        return ids;
    }
}
