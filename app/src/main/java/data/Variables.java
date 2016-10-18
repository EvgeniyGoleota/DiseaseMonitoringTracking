package data;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables {

    public static ArrayList<Disease_outbreak> diseaseList = new ArrayList<>();
    public static double                      Latitude;
    public static double                      Longitude;
    public static int DISPLAY_WIDTH;
    public static int DISPLAY_HEIGHT;

    public static String[] getArrayDiseaseName(){
        String[] array =new String[diseaseList.size()];
        for (int i=0; i < diseaseList.size(); i++){
            array[i] = diseaseList.get(i).getName();
        }
        return array;
    }

    public static HashMap<String, LatLng> getDiseaseCaseMap(){
        HashMap<String, LatLng> map = new HashMap<>();
        for(Disease_outbreak dis_out : diseaseList){
            for(DiseaseCase diseaseCase : dis_out.getDc()){
                map.put(diseaseCase.getName()+"_"+diseaseCase.getCountry()+"_"+diseaseCase.getCity(), new LatLng(diseaseCase.getLatitude(), diseaseCase.getLatitude()));
            }
        }
        return map;
    }
}
