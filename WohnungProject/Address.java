public class Address {
    private String plz;
    private String street;
    private int houseNumber;
    private int top;
    private final static int PLZ_NUMBERS = 4;

    public Address(String plz, String street, int houseNumber, int top) {
        this.setPlz(plz);
        this.setStreet(street);
        this.setHouseNumber(houseNumber);
        this.setTop(top);
    }

    public String getPlz() {
        return this.plz;
    }

    public String getStreet() {
        return this.street;
    }

    public int getHouseNumber() {
        return this.houseNumber;
    }

    public int getTop() {
        return this.top;
    }

    public void setPlz(String plz) {
        if(plz.length() == PLZ_NUMBERS){
            this.plz = plz;
        }
        else{
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
    }

    public void setStreet(String street) {
        if(street == null || street.length() <= 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.street = street;
    }

    public void setHouseNumber(int houseNumber) {
        // NB: maybe w/out = 0
        if(houseNumber <= 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.houseNumber = houseNumber;
    }

    public void setTop(int top) {
        if(top < 0){
            throw new IllegalArgumentException("Error: Parameter ungueltig.");
        }
        this.top = top;
    }
}
