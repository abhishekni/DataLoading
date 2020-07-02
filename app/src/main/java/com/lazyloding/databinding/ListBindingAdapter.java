package com.lazyloding.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.lazyloding.data.Resource;
import com.lazyloding.ui.base.BaseAdapter;

import java.util.List;

/**
 * Created by Abhishekn on 28/06/2020.
 */

public final class ListBindingAdapter{
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }
}
