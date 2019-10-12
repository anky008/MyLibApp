package com.example.mylibrary;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.NumberViewHolder> implements Filterable {


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList <EachViewHolderItem> filteredList=new ArrayList<>();

            if (constraint==null || constraint.length()==0)
            {
                filteredList.addAll(dataSetFull);
                Log.e("filtered list size full",Integer.toString(filteredList.size()));

            }

            else
            {
                String filterPattern=constraint.toString().toLowerCase().trim();

                for (EachViewHolderItem eachViewHolderItem: dataSetFull)
                {
                    if (eachViewHolderItem.getBookName().toLowerCase().contains(filterPattern) || eachViewHolderItem.getBookAuthor().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(eachViewHolderItem);
                        Log.e("filtered list size:",Integer.toString(filteredList.size()));
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          dataSet.clear();
          dataSet.addAll((ArrayList) results.values);
          notifyDataSetChanged();
        }
    };

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    final private ListItemClickListener mOnClickListener;
    ArrayList <EachViewHolderItem> dataSet;
    ArrayList <EachViewHolderItem> dataSetFull;
    ArrayList<File> all_pdf;
    ArrayList <File> all_pdf_full;

   // ArrayList <File> doc_pdf,ArrayList <EachViewHolderItem> dataSet,int numberItems,ListItemClickListener listener
    public myAdapter(ArrayList <EachViewHolderItem> dataSet,ListItemClickListener listener)
    {
        //all_pdf=doc_pdf;
        //mNumberItems=numberItems;
        mOnClickListener=listener;
        //all_pdf_full=new ArrayList<>(all_pdf);
        this.dataSet=dataSet;
        dataSetFull=new ArrayList<>(dataSet);

        Log.e("Size of dataSet: ",Integer.toString(dataSet.size()));
    }



    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context=viewGroup.getContext();
        int layoutIdForListItem=R.layout.view_holder_design;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately=false;

        View view=inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
        NumberViewHolder viewHolder=new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        TextView mBookName;
        TextView mBookInfo;
        ImageView mBookCover;
        ImageView mstarIcon;
        ImageView mClockIcon;
        ImageView mHaveReadIcon;
        ImageView mNewCollectionIcon;
        ImageView mSettings;
        ProgressBar mProgressBar;

        public NumberViewHolder(View itemView)
        {
            super(itemView);

            mBookName= itemView.findViewById(R.id.book_title);
            mBookInfo= itemView.findViewById(R.id.book_info);
            mBookCover= itemView.findViewById(R.id.book_image);
            mstarIcon= itemView.findViewById(R.id.star_icon);
            mClockIcon= itemView.findViewById(R.id.clock_icon);
            mHaveReadIcon= itemView.findViewById(R.id.have_read);
            mNewCollectionIcon= itemView.findViewById(R.id.new_collection);
            mSettings= itemView.findViewById(R.id.settings);
            mProgressBar=itemView.findViewById(R.id.book_progressbar);

            itemView.setOnClickListener(this);

        }

        void bind(int listIndex)
        {
            /*mBookName.setText(all_pdf.get(listIndex).getName());
            mBookInfo.setText("ViewHolder index: "+ listIndex);
            mBookCover.setImageResource(R.drawable.bookcover);
            */
             mBookName.setText(dataSet.get(listIndex).getBookName());
             mBookInfo.setText(dataSet.get(listIndex).getBookAuthor());
             mBookCover.setImageResource(dataSet.get(listIndex).getImageResource());


            mstarIcon.setImageResource(R.drawable.ic_action_star_icon_black);
            mClockIcon.setImageResource(R.drawable.ic_action_clock_icon_black);
            mHaveReadIcon.setImageResource(R.drawable.ic_action_have_read_icon_black);
            mNewCollectionIcon.setImageResource(R.drawable.ic_action_new_collection_icon_black);
            mSettings.setImageResource(R.drawable.ic_action_settings_icon_black);
            mProgressBar.setVisibility(View.VISIBLE);

        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
    };

