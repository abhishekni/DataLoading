package com.lazyloding.ui.fragments.allusers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lazyloding.R;
import com.lazyloding.data.remote.model.Row;
import com.lazyloding.databinding.RecyclerLazyLodingBinding;
import com.lazyloding.ui.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class LazyLoadingAdapter extends BaseAdapter<LazyLoadingAdapter.ItemsViewHolder, Row> implements Filterable {

    private List<Row> mItemsEntities;
    private List<Row> mItemsEntitiesFiltered;
    private LayoutInflater layoutInflater;
    private RecyclerLazyLodingBinding binding;

    public LazyLoadingAdapter(List<Row> mItemsEntities) {
        this.mItemsEntities = mItemsEntities;
        this.mItemsEntitiesFiltered=mItemsEntities;
    }
    public LazyLoadingAdapter() {
        this.mItemsEntities= new ArrayList<>();
        this.mItemsEntitiesFiltered=new ArrayList<>();
    }

    @Override
    public void setData(List<Row> itemsEntities) {
        this.mItemsEntities = itemsEntities;
        this.mItemsEntitiesFiltered = itemsEntities;
        notifyDataSetChanged();
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_lazy_loding, viewGroup, false);
        return new ItemsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder viewHolder, int i) {
        viewHolder.onBind(mItemsEntitiesFiltered.get(i));
    }

    @Override
    public int getItemCount() {
        return mItemsEntitiesFiltered.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mItemsEntitiesFiltered = mItemsEntities;
                } else {
                    List<Row> filteredList = new ArrayList<>();
                    for (Row row : mItemsEntities) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                    }
                    mItemsEntitiesFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mItemsEntitiesFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mItemsEntitiesFiltered = (ArrayList<Row>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class ItemsViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerLazyLodingBinding itemsbinding;

        public ItemsViewHolder(RecyclerLazyLodingBinding bindings) {
            super(bindings.getRoot());
            this.itemsbinding = bindings;
        }

        public void onBind(Row itementity) {
            itemsbinding.setRow(itementity);
            itemsbinding.executePendingBindings();
        }
    }
}
