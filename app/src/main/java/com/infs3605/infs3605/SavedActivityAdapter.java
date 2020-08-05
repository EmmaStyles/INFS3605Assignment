package com.infs3605.infs3605;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedActivityAdapter extends RecyclerView.Adapter<SavedActivityAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FavArticle> favArticleList;
    private FavDB favDB;
    private FavDBGenInfo favDBGenInfo;
    private String savedArticleType;
    private ArticleClickInterface articleClickInterface;

    SavedActivityAdapter(Context context, ArrayList<FavArticle> favArticleList, String savedArticleType, ArticleClickInterface articleClickInterface){
        this.context = context;
        this.favArticleList = favArticleList;
        this.savedArticleType = savedArticleType;
        this.articleClickInterface = articleClickInterface;

    }
    @NonNull
    @Override
    public SavedActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        favDBGenInfo = new FavDBGenInfo(context);


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_article, parent, false);

        SavedActivityAdapter.ViewHolder viewHolder = new SavedActivityAdapter.ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedActivityAdapter.ViewHolder holder, int position) {
        holder.savedArticleTitle.setText(favArticleList.get(position).getItem_title());
        holder.saveArticleDate.setText(favArticleList.get(position).getItem_date());

    }

    @Override
    public int getItemCount() {
        return favArticleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView savedArticleTitle;
        TextView saveArticleDate;
        Button favBtn;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            savedArticleTitle = itemView.findViewById(R.id.saved_article_title);
            saveArticleDate = itemView.findViewById(R.id.saved_article_date);
            favBtn = itemView.findViewById(R.id.saved_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleClickInterface.onArticleClick(getAdapterPosition());
                }
            });


           favBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position = getAdapterPosition();
                   final FavArticle favArticle = favArticleList.get(position);
                   final String id = favArticle.getKey_id();
                   Log.d("SavedActivityAdapter", "favArticle  " + favArticle.getKey_id());

                   if(savedArticleType.equalsIgnoreCase("restrictions articles")) {
                       favDB.remove_fav(favArticle.getKey_id());
                       removeItem(position);
                   } else{
                       if(savedArticleType.equalsIgnoreCase("general information articles")){
                           favDBGenInfo.remove_fav(favArticle.getKey_id());
                           removeItem(position);
                       }
                   }
               }
           });


        }
    }

    private void removeItem(int position){
        favArticleList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, favArticleList.size());
    }
}
