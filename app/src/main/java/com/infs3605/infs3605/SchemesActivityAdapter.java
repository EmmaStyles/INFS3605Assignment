package com.infs3605.infs3605;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SchemesActivityAdapter extends RecyclerView.Adapter<SchemesActivityAdapter.ViewHolder> {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<Scheme> mSchemesList;

    public SchemesActivityAdapter(ArrayList<Scheme> schemesList, ArticleClickInterface articleClickInterface){
        mSchemesList = schemesList;
        this.articleClickInterface =articleClickInterface;
    }


    @NonNull
    @Override
    public SchemesActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schemes_adapter, parent, false);
        SchemesActivityAdapter.ViewHolder viewHolder = new SchemesActivityAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchemesActivityAdapter.ViewHolder holder, int position) {
        Scheme currentItem = mSchemesList.get(position);
        holder.schemesTitle.setText(currentItem.getTitle());
        holder.schemesDate.setText(currentItem.getDateCloses());
    }

    @Override
    public int getItemCount() {
        return mSchemesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView schemesTitle;
        public TextView schemesDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schemesTitle = itemView.findViewById(R.id.schemes_title);
            schemesDate = itemView.findViewById(R.id.schemes_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleClickInterface.onArticleClick(getAdapterPosition());
                }
            });

        }
    }
}