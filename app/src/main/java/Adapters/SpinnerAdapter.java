package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter{

    public static boolean flag = false;
    private Context context;
    private int textViewResourseId;
    private String[] titles;

    public SpinnerAdapter(Context context, int textViewResourseId, String[] titles){
        super(context, textViewResourseId, titles);
        this.context = context;
        this.textViewResourseId = textViewResourseId;
        this.titles = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
           convertView = View.inflate(context, textViewResourseId, null);
        if (flag != false) {
            TextView tv = (TextView) convertView;
            tv.setText(titles[position]);
        }else {
            TextView tv = (TextView) convertView;
            tv.setText("Select Disease/Outbreak");
        }
        return convertView;
    }
}
