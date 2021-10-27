import java.util.List;

public interface HausverwaltungDAO {
    /** gibt alle persistent gespeicherten Wohnungsobjekte */
    List<Wohnung> getWohnungen();

    /**
    * gibt anhand der Wohnungsnummer ein Wohnungsobjekt zurück. Falls die Wohnung nicht gefunden wird,
    * soll null zurückgeben werden.
     */
    Wohnung getWohnungbyId(int idWohnung);

    /**
    soll ein Wohnungsobjekt persistent speichern.
    Stellen Sie sicher, dass beim Speichern einer neuen
    Wohnung nicht die Id einer bereits gespeicherten Wohnung
    verwendet wird. Werfen Sie in diesem Fall eine IllegalArgumentException mit einer entsprechenden Fehlermeldung
     */
    void saveWohnung(Wohnung newWohnung);

    /**
    soll eine Wohnung aus der persistenten Datenquelle löschen.
    Falls es die Wohnung nicht gibt, soll eine
    IllegalArgumentException mit entsprechender Fehlermeldung
     */
    void deleteWohnung(int idForDeletion);

}
