package com.infs3605.infs3605;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GeneralInfoActivityAdapter extends RecyclerView.Adapter<GeneralInfoActivityAdapter.ViewHolder> implements Filterable {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<GeneralInfoArticle> mGeneralInfoArticleList;
    private ArrayList<GeneralInfoArticle> mGeneralInfoArticleListFull;
    private Context context;
    private FavDBGenInfo favDBGenInfo;


    public GeneralInfoActivityAdapter(ArrayList<GeneralInfoArticle> generalInfoArticleList, ArticleClickInterface articleClickInterface, Context context){
        mGeneralInfoArticleList = generalInfoArticleList;
        mGeneralInfoArticleListFull = new ArrayList<>(mGeneralInfoArticleList);
        this.articleClickInterface =articleClickInterface;
        this.context = context;
    }

    @NonNull
    @Override
    public GeneralInfoActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDBGenInfo= new FavDBGenInfo(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStartGenInfo", true);
        if(firstStart){
            createTableOnFirstStart();
        }


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_info_activity_recycler_view, parent, false);
        GeneralInfoActivityAdapter.ViewHolder viewHolder = new GeneralInfoActivityAdapter.ViewHolder(v);
        return viewHolder;
}

    private void createTableOnFirstStart() {

        favDBGenInfo.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStartGenInfo", false);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull GeneralInfoActivityAdapter.ViewHolder holder, int position) {
        GeneralInfoArticle currentItem = mGeneralInfoArticleList.get(position);

        readCursorData(currentItem, holder);
        holder.generalInfoTitle.setText(currentItem.generalInfoTitle);
        holder.generalInfoDate.setText(currentItem.generalInfoDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void readCursorData(GeneralInfoArticle generalInfoArticle, ViewHolder viewHolder) {

        Cursor cursor = favDBGenInfo.read_all_data(generalInfoArticle.getArticleID());
        SQLiteDatabase db = favDBGenInfo.getReadableDatabase();
        try{
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDBGenInfo.INFO_STATUS));
                generalInfoArticle.setInfoStatus(item_fav_status);

                //check fav status
                if(item_fav_status != null && item_fav_status.equals("1")){
                    viewHolder.setFavBtn.setBackgroundResource(R.drawable.ic_bookmark_filled);
                } else if (item_fav_status != null && item_fav_status.equals("0")){
                    viewHolder.setFavBtn.setBackgroundResource(R.drawable.ic_bookmark);
                }
            }
        }finally{
            if (cursor != null && cursor.isClosed()){
                cursor.close();
                db.close();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mGeneralInfoArticleList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<GeneralInfoArticle> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(mGeneralInfoArticleListFull);
            } else{
                String filterPattern= constraint.toString().toLowerCase().trim();

                for(GeneralInfoArticle item : mGeneralInfoArticleListFull){
                    if (item.getGeneralInfoTitle().toLowerCase().contains(filterPattern)){
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

            mGeneralInfoArticleList.clear();
            mGeneralInfoArticleList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView generalInfoTitle;
        public TextView generalInfoDate;
        public Button setFavBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            generalInfoTitle = itemView.findViewById(R.id.generalInfo_title);
            generalInfoDate = itemView.findViewById(R.id.generalInfo_date);
            setFavBtn = itemView.findViewById(R.id.info_fav_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleClickInterface.onArticleClick(getAdapterPosition());
                }
            });

            setFavBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    GeneralInfoArticle generalInfoArticle = mGeneralInfoArticleList.get(position);

                    if(generalInfoArticle.getInfoStatus().equals("0")){
                        generalInfoArticle.setInfoStatus("1");
                        favDBGenInfo.insertIntoTheDatabase(generalInfoArticle.getArticleID(), generalInfoArticle.getArticleType(), generalInfoArticle.generalInfoTitle,
                                generalInfoArticle.getGeneralInfoDate(), generalInfoArticle.getGeneralInfoContent(),generalInfoArticle.getInfoStatus());
                        setFavBtn.setBackgroundResource(R.drawable.ic_bookmark_filled);
                    }else {
                        generalInfoArticle.setInfoStatus("0");
                        favDBGenInfo.remove_fav(generalInfoArticle.getArticleID());
                        setFavBtn.setBackgroundResource(R.drawable.ic_bookmark );
                    }
                }
            });

        }
    }
}
