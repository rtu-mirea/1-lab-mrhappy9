package fin;

public class Request {
    private String country;
    private String day_from;
    private String day_to;


    public Request(String country, String day_from, String day_to) {
        this.country = country;
        this.day_from = day_from;
        this.day_to = day_to;
    }

    public Request(){}

    protected String getCountry() {
        return country;
    }

    protected String getDay_from() {
        return day_from;
    }

    protected String getDay_to() {
        return day_to;
    }
}
