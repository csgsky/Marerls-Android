package com.gy.allen.marerls.libraries.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gy.allen.marerls.libraries.ImageConfig;
import com.gy.allen.marerls.libraries.imageload.IBaseImageStrategy;

import org.jetbrains.annotations.NotNull;

public class GlideImageLoaderStrategy implements IBaseImageStrategy {
    @Override
    public void display(@NotNull Context context, @NotNull ImageConfig imageConfig) {
        RequestOptions options = getOptions(context, imageConfig);
        Object url = getPath(imageConfig);
        if (!imageConfig.isAsBitmap()) {
            RequestBuilder<Drawable> requestBuilder = Glide.with(context).load(url).apply(options);
            if (!imageConfig.isRound() && imageConfig.getDuration() != 0) {
                requestBuilder = requestBuilder
                        .transition(new DrawableTransitionOptions().crossFade(imageConfig.getDuration()));
            }
            requestBuilder.into(imageConfig.getImageView());
        } else {
            RequestBuilder<Bitmap> requestBuilder = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(options);
            if (!imageConfig.isRound() &&  imageConfig.getDuration() != 0) {
                requestBuilder.transition(new BitmapTransitionOptions().crossFade(imageConfig.getDuration()));
            }
            requestBuilder.into(imageConfig.getTarget());
        }
    }

    @Override
    public void clean(@NotNull Context context, @NotNull ImageView imageView) {
        Glide.with(context).clear(imageView);
    }

    private RequestOptions getOptions(Context context, ImageConfig imageConfig) {
        RequestOptions options = new RequestOptions()
                .placeholder(imageConfig.getDefaultRes())
                .error(imageConfig.getErrorRes());
        if (imageConfig.isRound()) {
            if (imageConfig.getCornerSize() == 0) {
                options.transform(new GlideCornerTransformation(context));
            } else {
                options.transform(new GlideCornerTransformation(context, imageConfig.getCornerSize()));
            }
        }
        DiskCacheStrategy strategy = DiskCacheStrategy.RESOURCE;
        switch (imageConfig.getCacheStrategy()) {
            case ImageConfig.CACHE_NONE:
                strategy = DiskCacheStrategy.NONE;
                break;
            case ImageConfig.CACHE_ALL:
                strategy = DiskCacheStrategy.ALL;
                break;
            case ImageConfig.CACHE_SOURCE:
                strategy = DiskCacheStrategy.DATA;
                break;
            case ImageConfig.CACHE_RESULT:
                strategy = DiskCacheStrategy.RESOURCE;
                break;
            case ImageConfig.CACHE_AUTO:
                strategy = DiskCacheStrategy.AUTOMATIC;
                break;
        }
        options.diskCacheStrategy(strategy);
        options.skipMemoryCache(imageConfig.isSkipMemoryCache());
        return options;
    }

    private Object getPath(ImageConfig imageConfig) {return imageConfig.getUrl();}

}
