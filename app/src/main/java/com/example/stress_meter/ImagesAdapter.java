package com.example.stress_meter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagesAdapter extends BaseAdapter {
    private final Context context;
    private final int[] images;

    public ImagesAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.image_cell, null);
        }

        final ImageView imageView = view.findViewById(R.id.imageContainer);
        Drawable drawable = context.getApplicationContext().getDrawable(images[i]);
        imageView.setImageDrawable(drawable);

        return view;
    }
}
