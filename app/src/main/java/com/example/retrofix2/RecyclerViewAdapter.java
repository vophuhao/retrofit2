package com.example.retrofix2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;
        public List<String> mItemList;
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        public ItemViewHolder (@NonNull View itemView) {
            super(itemView);
            tvItem =itemView.findViewById(R.id.tvItem);
        }
    }
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar= itemView.findViewById(R.id.progressBar);
        }
    }
        public RecyclerViewAdapter(List<String> itemList) {
            mItemList = itemList;
        }
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
                return new ItemViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_row, parent, false); // Đảm bảo bạn có layout loading_row.xml
                return new LoadingViewHolder(view);
            }
            return null;
        }
        @Override
        public void onBindViewHolder (@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (viewHolder instanceof ItemViewHolder) {
                populateItemRows((ItemViewHolder) viewHolder, position);
            }
            else if (viewHolder instanceof LoadingViewHolder) {
                showLoadingView((LoadingViewHolder) viewHolder, position);
            }
        }
        @Override
        public int getItemCount() {
            return mItemList == null ?0 : mItemList.size();
        }
        @Override
        public int getItemViewType(int position) {
            return mItemList.get(position) == null ? VIEW_TYPE_LOADING: VIEW_TYPE_ITEM;
        }

        private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
        }
        private void populateItemRows(ItemViewHolder viewHolder, int position) {
            String item= mItemList.get(position);
            viewHolder.tvItem.setText(item);
        }

}
