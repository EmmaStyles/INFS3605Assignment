package com.infs3605.infs3605;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IndustryActivityAdapter extends RecyclerView.Adapter<IndustryActivityAdapter.ViewHolder> {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<Article> mArticleList;
    private Context context;

    public IndustryActivityAdapter(ArrayList<Article> articleList, ArticleClickInterface articleClickInterface, Context context){
        mArticleList = articleList;
        this.articleClickInterface = articleClickInterface;
        this.context = context;

    }

    @NonNull
    @Override
    public IndustryActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.industry_activity_recycler_view, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v);
        IndustryActivityAdapter.ViewHolder viewHolder = new IndustryActivityAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IndustryActivityAdapter.ViewHolder holder, int position) {
        Article currentItem = mArticleList.get(position);

        holder.titleText.setText(currentItem.getTitle());
        holder.dateText.setText(currentItem.getDate());
        Picasso.with(context).load(currentItem.getArticleImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;
        public TextView dateText;
        public ImageView image;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            titleText = itemView.findViewById(R.id.article_title);
            dateText = itemView.findViewById(R.id.article_date);
            image = itemView.findViewById(R.id.article_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleClickInterface.onArticleClick(getAdapterPosition());
                }
            });

        }
    }
}
