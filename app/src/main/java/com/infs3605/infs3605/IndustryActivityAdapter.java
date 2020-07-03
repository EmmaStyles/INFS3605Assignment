package com.infs3605.infs3605;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IndustryActivityAdapter extends RecyclerView.Adapter<IndustryActivityAdapter.ViewHolder> {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<Article> mArticleList;

    public IndustryActivityAdapter(ArrayList<Article> articleList, ArticleClickInterface articleClickInterface){
        mArticleList = articleList;
        this.articleClickInterface = articleClickInterface;
    }

    @NonNull
    @Override
    public IndustryActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.industry_activity_recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IndustryActivityAdapter.ViewHolder holder, int position) {
        Article currentItem = mArticleList.get(position);

        holder.titleText.setText(currentItem.getTitle());
        holder.dateText.setText(currentItem.getDate());

    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;
        public TextView dateText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.article_title);
            dateText = itemView.findViewById(R.id.article_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleClickInterface.onArticleClick(getAdapterPosition());
                }
            });

        }
    }
}
