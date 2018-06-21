package com.gy.allen.marerls.libraries;

import android.content.Context;
import android.widget.ImageView;

import com.gy.allen.marerls.libraries.glide.GlideImageLoaderStrategy;
import com.gy.allen.marerls.libraries.imageload.IBaseImageStrategy;

import org.jetbrains.annotations.NotNull;

public class ImageLoader implements IBaseImageStrategy{
    private static ImageLoader INSTANCE;
    private IBaseImageStrategy mImageStrategy;

    {
        mImageStrategy = new GlideImageLoaderStrategy();
    }

    public static ImageLoader getInstance() {
        if (INSTANCE == null) {
            synchronized (ImageLoader.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ImageLoader();
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public void display(@NotNull Context context, @NotNull ImageConfig imageConfig) {
        mImageStrategy.display(context, imageConfig);
    }

    @Override
    public void clean(@NotNull Context context, @NotNull ImageView imageView) {
        mImageStrategy.clean(context, imageView);
    }
}
