package com.infs3605.infs3605;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GeneralInfoActivityAdapter extends RecyclerView.Adapter<GeneralInfoActivityAdapter.ViewHolder> implements Filterable {
    private ArticleClickInterface articleClickInterface;
    private ArrayList<GeneralInfoArticle> mGeneralInfoArticleList;
    private ArrayList<GeneralInfoArticle> mGeneralInfoArticleListFull;

    public GeneralInfoActivityAdapter(ArrayList<GeneralInfoArticle> generalInfoArticleList, ArticleClickInterface articleClickInterface){
        mGeneralInfoArticleList = generalInfoArticleList;
        mGeneralInfoArticleListFull = new ArrayList<>(mGeneralInfoArticleList);
        this.articleClickInterface =articleClickInterface;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            generalInfoTitle = itemView.findViewById(R.id.generalInfo_title);
            generalInfoDate = itemView.findViewById(R.id.generalInfo_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleClickInterface.onArticleClick(getAdapterPosition());
                }
            });

        }
    }
}
