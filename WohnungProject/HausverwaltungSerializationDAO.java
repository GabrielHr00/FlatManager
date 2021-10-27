import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HausverwaltungSerializationDAO implements HausverwaltungDAO{
    private List<Wohnung> wohnungList;
    private String dateiName;

    public HausverwaltungSerializationDAO(String dateiName) {
        this.setWohnungList(wohnungList);
        this.setDateiName(dateiName);
    }

    @Override
    public List<Wohnung> getWohnungen() {
        return this.wohnungList;
    }

    @Override
    public Wohnung getWohnungbyId(int idWohnung){
        try{
            Wohnung wohnung = wohnungList.stream().filter(e -> e.getId() == idWohnung).collect(Collectors.toList()).get(0);
            return wohnung;
        } catch (IndexOutOfBoundsException e){
            return null;
        }
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

    public void serializeWohnungen(List<Wohnung> wohnungen){
        File file = new File(this.dateiName);
        if(file.exists()){
            file.delete();
        }

        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(this.dateiName, true));
            writer.writeObject(wohnungen);
            writer.close();
        } catch (Exception e) {
            System.err.println("Fehler bei Serialisierung: " + e.getMessage());
            System.exit(1);
        }
    }

    public List<Wohnung> deserializeWohnungen() {
        List<Wohnung> wohnungen = new ArrayList<>();

        try{
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(this.dateiName));
            wohnungen = (List<Wohnung>) reader.readObject();
            reader.close();
        } catch (Exception e) {
            System.err.println("Fehler bei Deserialisierung: " + e.getMessage());
            System.exit(1);
        }
        return wohnungen;
    }
}
