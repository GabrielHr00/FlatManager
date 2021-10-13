import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HausverwaltungSerializationDAO<T> implements HausverwaltungDAO{
    private List<Wohnung> wohnungList;
    private String dateiName;

    public HausverwaltungSerializationDAO(List<Wohnung> wohnungList, String dateiName) {
        this.setWohnungList(wohnungList);
        this.setDateiName(dateiName);
    }

    public <T> void serializeObject(T object){
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(dateiName));
            stream.writeObject(object);
            stream.close();
        } catch (IOException ioex) {
            System.err.println("Fehler bei Serialisierung: " + ioex.getMessage());
            System.exit(1);
        }
    }

    public <T> void deserializeObject(T object) {

    }

    @Override
    public List<Wohnung> getWohnungen() {
        return this.wohnungList;
    }

    @Override
    public Wohnung getWohnungbyId(int idWohnung) {
        List<Wohnung> wohn = wohnungList.stream().filter(e -> e.getId() == idWohnung).collect(Collectors.toList());
        return wohn.isEmpty() ? null : wohn.get(0);
    }

    @Override
    public void saveWohnung(Wohnung newWohnung) {
        long count = this.wohnungList.stream().filter(e -> e.getId() == newWohnung.getId()).count();
        if(count != 0){
            throw new IllegalArgumentException("Error: Wohnung bereits vorhanden. (id=" + newWohnung + ")");
        }
        this.wohnungList.add(newWohnung);
    }

    @Override
    public void deleteWohnung(int idForDeletion) {
        Wohnung tempWohn = null;
        for (Wohnung w : this.wohnungList) {
            if(w.getId() == idForDeletion){
                tempWohn = w;
                break;
            }
        }
        if(tempWohn == null){
            throw new IllegalArgumentException("Error: Wohnung nicht vorhanden. (id=" + idForDeletion + ")");
        }
        this.wohnungList.remove(tempWohn);
    }

    public void setWohnungList(List<Wohnung> wohnungList) {
        this.wohnungList = wohnungList;
    }

    public void setDateiName(String dateiName) {
        if(dateiName.isEmpty()){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.dateiName = dateiName;
    }
}
