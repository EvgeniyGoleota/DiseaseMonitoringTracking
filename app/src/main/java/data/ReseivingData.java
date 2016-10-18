package data;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ReseivingData {

    public static void reseivingData(){
        new MyThread().execute();
    }

    public static void reseivingDataTest(){
        Variables.diseaseList.clear();

        ArrayList<DiseaseCase> dcMalaria = new ArrayList();
        dcMalaria.add(new DiseaseCase("Malaria", "There are 296 cases of malaria reported this year in Gautam Budh Nagar and officials said that the number of cases is more than what was reported last year.",
                269, "2016-09-30 16:11:00", "India", "Gautam Budh Nagar", 28.359, 77.551));

        dcMalaria.add(new DiseaseCase("Malaria", "Malaria and dengue are widespread in urban and rural areas of the district causing grave concern to people. Sanitation has been hit in villages and towns due to heavy rains that lashed the district under the influence of depression.",
                0, "2016-09-30 15:28:00", "India", "Kadapa", 14.467, 78.824));

        dcMalaria.add(new DiseaseCase("Malaria", "Since the onset of monsoon, the number of people suffering from malaria and dengue has been on the rise. Between June to September 19, as many as 802 malaria cases have been reported in the city.",
                802, "2016-09-29 15:27:00", "India", "Thane", 19.218, 72.978));

        Variables.diseaseList.add(new Disease_outbreak("Malaria", "Malaria description", dcMalaria));

        ArrayList<DiseaseCase> dcDengue = new ArrayList<>();
        dcDengue.add(new DiseaseCase("Dengue", "Two dengue deaths have been reported in Kharghar.",
                2, "2016-10-07 17:21:00", "India", "Kharghar", 19.047, 73.070));

        dcDengue.add(new DiseaseCase("Dengue", "As many as 24 new dengue fever cases have been reported in Chachro city, taking the total number of the cases to 42.",
                24, "2016-10-10 01:35:00", "Pakistan", "Chachro", 25.116, 70.256));

        dcDengue.add(new DiseaseCase("Dengue", "According to the Provincial Health Office, the lone fatality recorded last Sept. 24 in San Isidro Village meant the number of deaths is about halfway to the 15 recorded in 2015.",
                15, "2016-10-10 01:33:00", "Philippines", "Catanduanes", 13.585, 124.207));

        Variables.diseaseList.add(new Disease_outbreak("Dengue", "Dengue description", dcDengue));

    }

    static class MyThread extends AsyncTask<Void, Void, Void> {

        private Elements element;

        @Override
        protected Void doInBackground(Void... params) {

            Document doc;

            try{
                doc = Jsoup.connect("http://outbreaks.globalincidentmap.com").get();

                element = doc.select(".events-head");

            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
