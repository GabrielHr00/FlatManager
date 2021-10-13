abstract class Wohnung {
    private int id; // NB: eindeutig
    private double area;
    private int roomsCount;
    private int floors;  // (0 = Erdgeschoß)
    private String buildYear;
    private Address address;
    private final static int CURRENT_YEAR = 2021;

    public Wohnung(int id, double area, int roomsCount, int floors, String buildYear, Address address) {
        this.setId(id);
        this.setArea(area);
        this.setRoomsCount(roomsCount);
        this.setFloors(floors);
        this.setBuildYear(buildYear);
        this.setAddress(address);
    }

    public abstract double gesamtKosten();

    public int alter(){
        if(this.buildYear == null){
            throw new IllegalArgumentException("Error: Baujahr ungueltig.");
        }
        int buildYearFlat = Integer.parseInt(this.buildYear);
        return CURRENT_YEAR - buildYearFlat;
    }

    public void setId(int id) {
        if(id < 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.id = id;
    }

    public void setArea(double area) {
        // maybe w/out = 0
        if(area <= 0.0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.area = area;
    }

    public void setRoomsCount(int roomsCount) {
        if(roomsCount <= 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.roomsCount = roomsCount;
    }

    public void setFloors(int floors) {
        if(floors < 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.floors = floors;
    }

    public void setBuildYear(String buildYear) {
        int year = Integer.parseInt(buildYear);
        if(year > CURRENT_YEAR){
            throw new IllegalArgumentException("Error: Baujahr ungueltig.");
        }
        this.buildYear = buildYear;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public int getFloors() {
        return floors;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public Address getAddress() {
        return address;
    }

    public static int getCurrentYear() {
        return CURRENT_YEAR;
    }
}
