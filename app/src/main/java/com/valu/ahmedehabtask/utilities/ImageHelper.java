package com.valu.ahmedehabtask.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.valu.ahmedehabtask.R;

public class ImageHelper {
    public static void loadImage(Context context , String url, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(imageView);
        }catch (Exception e){
            Log.d("crash", "Glide: "+e);
        }
    }
}
