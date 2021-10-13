public class EigentumsWohnung extends Wohnung{
    private double livingCosts; // pro m2 Betriebskosten
    private double repairContribution; // pro m2 Beitrag fur die Reparaturrücklage

    public EigentumsWohnung(int id, double area, int roomsCount, int floors, String buildYear, Address address, double livingCosts, double repairContribution) {
        super(id, area, roomsCount, floors, buildYear, address);
        this.setLivingCosts(livingCosts);
        this.setRepairContribution(repairContribution);
    }

    @Override
    public double gesamtKosten() {
        double allCosts = (livingCosts + repairContribution) * getArea();
        return allCosts + (allCosts * calculatePercentage());
    }

    private double calculatePercentage(){
        // Zuschlag
        double zuschlag;
        if(getFloors() == 0){
            zuschlag = 0.0;
            return zuschlag;
        }
        zuschlag = getFloors() * 0.02;
        return zuschlag;
    }

    public void setLivingCosts(double livingCosts) {
        // maybe with = 0 too
        if(livingCosts < 0.0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.livingCosts = livingCosts;
    }

    // TODO :
    public void setRepairContribution(double repairContribution) {
        if(repairContribution < 0.0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.repairContribution = repairContribution;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Typ:            EW").append(System.lineSeparator());
        sb.append("Id:             " + getId()).append(System.lineSeparator());
        sb.append(String.format("Flaeche:        %.2f%n", getArea()));
        sb.append("Zimmer:         " + getRoomsCount()).append(System.lineSeparator());
        sb.append("Stock:          " + getFloors()).append(System.lineSeparator());
        sb.append("Baujahr:        " + getBuildYear()).append(System.lineSeparator());
        sb.append("PLZ:            " + getAddress().getPlz()).append(System.lineSeparator());
        sb.append("Strasse:        " + getAddress().getStreet()).append(System.lineSeparator());
        sb.append("Hausnummer:     " + getAddress().getHouseNumber()).append(System.lineSeparator());
        sb.append("Top:            " + getAddress().getTop()).append(System.lineSeparator());
        sb.append(String.format("Betriebskosten: %.2f%n", this.livingCosts));
        sb.append(String.format("Ruecklage:      %.2f%n", this.repairContribution));
        return sb.toString();
    }
}
