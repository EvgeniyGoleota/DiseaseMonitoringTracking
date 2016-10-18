package data;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.ArrayList;
import java.util.HashMap;

public class Disease_outbreak {
    private String                 name;
    private String                 description;
    private ArrayList<DiseaseCase> dc;

    public Disease_outbreak(String name, String description, ArrayList<DiseaseCase> dc) {
        this.name = name;
        this.description = description;
        this.dc = dc;
    }

    public ArrayList<WeightedLatLng> getWeightLatLngCollection(){
        ArrayList<WeightedLatLng> weightedLatLngList = new ArrayList<>();
        for(DiseaseCase diseaseCase : this.dc){
            weightedLatLngList.add(new WeightedLatLng(diseaseCase.getLatLng(),
                    diseaseCase.getNumberOfSubmission()));
        }
        return weightedLatLngList;
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

    public ArrayList<DiseaseCase> getDc() {
        return dc;
    }

    public void setDc(ArrayList<DiseaseCase> dc) {
        this.dc = dc;
    }
}
