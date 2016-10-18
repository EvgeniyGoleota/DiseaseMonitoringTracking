package Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.diseasemonitoringtracking.R;

import java.util.ArrayList;

import data.DiseaseCase;

public class DiseaseRecyclerViewAdapter extends RecyclerView.Adapter<DiseaseRecyclerViewAdapter.ViewHolder>{

    ArrayList<DiseaseCase> caseList;

    public DiseaseRecyclerViewAdapter(ArrayList<DiseaseCase> list) {
        this.caseList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_detail_recycler_view_item, parent, false);
        return new DiseaseRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(caseList.get(position).getName());
        holder.country.setText(caseList.get(position).getCountry());
        holder.description.setText(caseList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return caseList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView name;
        TextView country;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            card        = (CardView) itemView.findViewById(R.id.dd_card_view);
            name        = (TextView) itemView.findViewById(R.id.tv_name);
            country     = (TextView) itemView.findViewById(R.id.tv_country);
            description = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}
