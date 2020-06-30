package com.infs3605.infs3605;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {


    public List<IndustryClass> mDataset;


    public MainActivityAdapter(List<IndustryClass> myDataset){
        mDataset = myDataset;
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView industryNameTextView;

        public ViewHolder(View v){
            super(v);
            industryNameTextView = v.findViewById(R.id.industryName);
        }


    }

//    public  MainActivityAdapter(@NonNull View itemView) {
//        super(itemView);
//        view = itemView;
//        industryName = view.findViewById(R.id.industryName);
//    }

}

