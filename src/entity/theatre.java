package entity;

public class theatre {
    private int theatreId;
    private String name;
    private String city;

    public theatre(int theatreId, String name, String city) {
        this.name = name;
        this.city = city;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
