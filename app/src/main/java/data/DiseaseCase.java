package data;

import com.google.android.gms.maps.model.LatLng;

public class DiseaseCase {

    private String name;
    private String description;
    private int    numberOfSubmission;
    private String dateTime;
    private String country;
    private String city;
    private double latitude;  //широта
    private double longitude; //долгота

    public DiseaseCase(String name, String description, int numberOfSubmission, String dateTime,
                       String country, String city, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.numberOfSubmission = numberOfSubmission;
        this.dateTime = dateTime;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfSubmission() {
        return numberOfSubmission;
    }

    public void setNumberOfSubmission(int numberOfSubmission) {
        this.numberOfSubmission = numberOfSubmission;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
