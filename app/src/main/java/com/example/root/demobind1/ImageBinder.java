package com.example.root.demobind1;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by root on 10/10/17.
 */

public final class ImageBinder {

        private ImageBinder() {
            //NO-OP
        }

        @BindingAdapter("imageUrl")
        public static void setImageUrl(ImageView imageView, String url) {
            Context context = imageView.getContext();
            Picasso.with(context).load(url).into(imageView);

        }
    }

