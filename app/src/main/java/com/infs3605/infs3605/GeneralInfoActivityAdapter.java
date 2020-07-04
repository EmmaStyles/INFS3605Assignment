package com.infs3605.infs3605;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GeneralInfoActivityAdapter extends RecyclerView.Adapter<GeneralInfoActivityAdapter.ViewHolder> {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<GeneralInfoArticle> mGeneralInfoArticleList;

    public GeneralInfoActivityAdapter(ArrayList<GeneralInfoArticle> generalInfoArticleList){
        mGeneralInfoArticleList = generalInfoArticleList;
    }

    @NonNull
    @Override
    public GeneralInfoActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_info_activity_recycler_view, parent, false);
        GeneralInfoActivityAdapter.ViewHolder viewHolder = new GeneralInfoActivityAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralInfoActivityAdapter.ViewHolder holder, int position) {
        GeneralInfoArticle currentItem = mGeneralInfoArticleList.get(position);

        holder.generalInfoTitle.setText(currentItem.generalInfoTitle);
        holder.generalInfoDate.setText(currentItem.generalInfoDate);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView generalInfoTitle;
        public TextView generalInfoDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            generalInfoTitle = itemView.findViewById(R.id.generalInfo_title);
            generalInfoDate = itemView.findViewById(R.id.generalInfo_date);


        }
    }
}
