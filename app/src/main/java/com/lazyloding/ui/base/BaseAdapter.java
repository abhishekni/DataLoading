package com.lazyloding.ui.base;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Abhishekn on 28/06/2020.
 */

public abstract class BaseAdapter<Type extends RecyclerView.ViewHolder, Data> extends RecyclerView.Adapter<Type>{

    public abstract void setData(List<Data> data);
}
