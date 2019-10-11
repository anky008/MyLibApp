package com.example.mylibrary;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.NumberViewHolder> {

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    private int mNumberItems;
    final private ListItemClickListener mOnClickListener;
    ArrayList<File> all_pdf;

    public myAdapter(ArrayList<File> doc_pdf,int numberItems,ListItemClickListener listener)
    {
        all_pdf=doc_pdf;
        mNumberItems=numberItems;
        mOnClickListener=listener;
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
        return mNumberItems;
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
            mBookName.setText(all_pdf.get(listIndex).getName());
            mBookInfo.setText("ViewHolder index: "+ listIndex);
            mBookCover.setImageResource(R.drawable.bookcover);

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
}

