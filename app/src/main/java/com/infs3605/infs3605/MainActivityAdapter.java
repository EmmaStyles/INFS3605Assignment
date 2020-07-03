package com.infs3605.infs3605;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> implements Filterable {


    public List<IndustryClass> mDataset;
    public List<IndustryClass> mDatasetFull;
    private IndustryClickInterface industryClickInterface;


    public MainActivityAdapter(List<IndustryClass> myDataset, IndustryClickInterface industryClickInterface ){
        mDataset = myDataset;
        mDatasetFull = new ArrayList<>(mDataset);
        this.industryClickInterface =industryClickInterface ;
    }

    @NonNull
    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_recycler_view, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IndustryClass industryClass = mDataset.get(position);
        TextView textView = holder.industryNameTextView;
        textView.setText(industryClass.getIndustryName());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView industryNameTextView;


        public ViewHolder(View v){
            super(v);
            industryNameTextView = v.findViewById(R.id.industryName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    industryClickInterface.onIndustryClick(getAdapterPosition());
                }
            });


        }


    }
    // Filter search methods
    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<IndustryClass> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mDatasetFull);
            } else{
                String filterPattern= constraint.toString().toLowerCase().trim();

                for(IndustryClass item : mDatasetFull){
                    if (item.getIndustryName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {

            mDataset.clear();
            mDataset.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

//    public interface onIndustryListener{
//
//        void onIndustryClick(int position);
//    }


}
