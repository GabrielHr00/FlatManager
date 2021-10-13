public class MietWohnung extends Wohnung{
    private double rentMonthsCosts; // pro m2 die monatlichen Mietkosten
    private int rentersCount; // die Anzahl der Mieter

    public MietWohnung(int id, double area, int roomsCount, int floors, String buildYear, Address address, double rentMonthsCosts, int rentersCount) {
        super(id, area, roomsCount, floors, buildYear, address);
        this.setRentMonthsCosts(rentMonthsCosts);
        this.setRentersCount(rentersCount);
    }

    @Override
    public double gesamtKosten() {
        double miete = rentMonthsCosts * getArea();
        return miete + (miete * calculatePercentage());
    }

    public void setRentMonthsCosts(double rentMonthsCosts) {
        // maybe with = 0 too
        if(rentMonthsCosts < 0.0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.rentMonthsCosts = rentMonthsCosts;
    }

    public void setRentersCount(int rentersCount) {
        if(rentersCount < 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.rentersCount = rentersCount;
    }

    private double calculatePercentage(){
        // Zuschlag
        double zuschlag;
        if(rentersCount <= 1){
            zuschlag = 0.0;
            return zuschlag;
        }
        else if(rentersCount >= 4){
            zuschlag = 0.10;
            return zuschlag;
        }
        zuschlag = rentersCount * 0.025;
        return zuschlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Typ:            MW").append(System.lineSeparator());
        sb.append("Id:             " + getId()).append(System.lineSeparator());
        sb.append(String.format("Flaeche:        %.2f%n", getArea()));
        sb.append("Zimmer:         " + getRoomsCount()).append(System.lineSeparator());
        sb.append("Stock:          " + getFloors()).append(System.lineSeparator());
        sb.append("Baujahr:        " + getBuildYear()).append(System.lineSeparator());
        sb.append("PLZ:            " + getAddress().getPlz()).append(System.lineSeparator());
        sb.append("Strasse:        " + getAddress().getStreet()).append(System.lineSeparator());
        sb.append("Hausnummer:     " + getAddress().getHouseNumber()).append(System.lineSeparator());
        sb.append("Top:            " + getAddress().getTop()).append(System.lineSeparator());
        sb.append(String.format("Miete/m2:       %.2f%n", this.rentMonthsCosts));
        sb.append("Anzahl Mieter:  " + this.rentersCount).append(System.lineSeparator());
        return sb.toString();
    }
}
