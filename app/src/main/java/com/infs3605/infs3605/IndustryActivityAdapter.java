package com.infs3605.infs3605;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.MessagePattern;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IndustryActivityAdapter extends RecyclerView.Adapter<IndustryActivityAdapter.ViewHolder> implements Filterable {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<Article> mArticleList;
    private ArrayList<Article> mArticleListFull;
    private Context context;
    private FavDB favDB;



    public IndustryActivityAdapter(ArrayList<Article> articleList, ArticleClickInterface articleClickInterface, Context context){
        mArticleList = articleList;
        mArticleListFull = new ArrayList<>(mArticleList);
        this.articleClickInterface = articleClickInterface;
        this.context = context;

    }

    @NonNull
    @Override
    public IndustryActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if(firstStart){
            createTableOnFirstStart();
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.industry_activity_recycler_view, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v);
        IndustryActivityAdapter.ViewHolder viewHolder = new IndustryActivityAdapter.ViewHolder(v);

        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull IndustryActivityAdapter.ViewHolder holder, int position) {
        final Article currentItem = mArticleList.get(position);

        readCursorData(currentItem, holder);
        holder.titleText.setText(currentItem.getTitle());
        holder.dateText.setText(currentItem.getDate());
//        Picasso.with(context).load(currentItem.getArticleImageUrl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Article> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mArticleListFull);
            } else{
                String filterPattern= constraint.toString().toLowerCase().trim();

                for(Article item : mArticleListFull){
                    if (item.getTitle().toLowerCase().contains(filterPattern)){
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

            mArticleList.clear();
            mArticleList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;
        public TextView dateText;
//        public ImageView image;
        Button favBtn;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            titleText = itemView.findViewById(R.id.article_title);
            dateText = itemView.findViewById(R.id.article_date);
//            image = itemView.findViewById(R.id.article_image);
            favBtn = itemView.findViewById(R.id.fav_button);

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
                    Article article = mArticleList.get(position);

                    if(article.getFavStatus().equals("0")){
                        article.setFavStatus("1");
                        favDB.insertIntoTheDatabase(article.getKeyId(), article.getIndustry(), article.getSegment(), article.getTitle(), article.getDate(),
                                article.getContent(), article.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_bookmark_filled);
                    }else {
                        article.setFavStatus("0");
                        favDB.remove_fav(article.getKeyId());
                        favBtn.setBackgroundResource(R.drawable.ic_bookmark );
                    }
                }
            });

        }
    }

    private void createTableOnFirstStart(){
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void readCursorData(Article article, ViewHolder viewHolder){

        Cursor cursor = favDB.read_all_data(article.getKeyId());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                article.setFavStatus(item_fav_status);

                //check fav status
                if(item_fav_status != null && item_fav_status.equals("1")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_bookmark_filled);
                } else if (item_fav_status != null && item_fav_status.equals("0")){
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_bookmark);
                }
            }
        }finally{
            if (cursor != null && cursor.isClosed()){
                cursor.close();
                db.close();
            }
        }


  }
}
