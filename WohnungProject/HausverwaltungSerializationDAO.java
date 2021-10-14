import java.io.*;
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

    public void serializeObject(Wohnung wohnung){
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.dateiName));
            stream.writeObject(wohnung);
            stream.close();
        } catch (IOException ioex) {
            System.err.println("Fehler bei Serialisierung: " + ioex.getMessage());
            System.exit(1);
        }
    }

    public <T> T deserializeObject() {
        try{
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.dateiName));
            T deserializedObject = (T) stream.readObject();
            stream.close();
            return deserializedObject;
        } catch (IOException e) {
            System.err.println("Fehler bei Deserialisierung: " + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler bei Deserialisierung: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
