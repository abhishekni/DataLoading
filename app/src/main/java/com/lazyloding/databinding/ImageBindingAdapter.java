package com.lazyloding.databinding;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lazyloding.R;
import com.lazyloding.ui.home.HomeActivity;


/**
 * Created by Abhishekn on 28/06/2020.
 */

public final class ImageBindingAdapter {

    public static String str = "";
    @BindingAdapter(value = "url")
    public static void loadImageUrl(ImageView view, String url) {
        str = url;
        ((HomeActivity)(view.getContext())).setURL(url);
        Glide.with(view.getContext())
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(100, 100).priority(Priority.IMMEDIATE))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(view);
    }

}
