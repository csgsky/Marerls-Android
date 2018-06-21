package com.gy.allen.marerls.libraries;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.request.target.Target;

public class ImageConfig {

    private Object url;
    private ImageView imageView;
    private int defaultRes;
    private int errorRes;
    private boolean isRound = true;
    private int cornerSize;
    private Target<Bitmap> target;
    private boolean isAsBitmap;
    private int duration;//Glide的crossFade时长
    private int cacheStrategy;
    private boolean isSkipMemoryCache;

    public static final int CACHE_NONE = 1;
    public static final int CACHE_RESULT = 2;
    public static final int CACHE_ALL = 3;
    public static final int CACHE_SOURCE = 4;
    public static final int CACHE_AUTO = 5;

    public ImageConfig(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
        this.defaultRes = builder.defaultRes;
        this.errorRes = builder.errorRes;
        this.isRound = builder.isRound;
        this.cornerSize = builder.cornerSize;
        this.target = builder.target;
        this.isAsBitmap = builder.isAsBitmap;
        this.duration = builder.duration;
        this.cacheStrategy = builder.cacheStrategy;
        this.isSkipMemoryCache = builder.isSkipMemoryCache;
    }

    public boolean isSkipMemoryCache() {
        return isSkipMemoryCache;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public int getDuration() {
        return duration;
    }

    public Target<Bitmap> getTarget() {
        return target;
    }

    public boolean isAsBitmap() {
        return isAsBitmap;
    }

    public boolean isRound() {
        return isRound;
    }

    public int getCornerSize() {
        return cornerSize;
    }

    public Object getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getDefaultRes() {
        return defaultRes;
    }

    public int getErrorRes() {
        return errorRes;
    }

    public static class Builder {
        private Object url;
        private ImageView imageView;
        private int defaultRes;
        private int errorRes;
        private boolean isRound = true;
        private int cornerSize;
        private boolean isAsBitmap;
        private Target<Bitmap> target;
        private int duration;
        private int cacheStrategy;
        private boolean isSkipMemoryCache;

        public Builder skipMemoryCache(boolean isSkipMemoryCache) {
            this.isSkipMemoryCache = isSkipMemoryCache;
            return this;
        }

        public Builder cacheStrategy(int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder crossFade(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder asBitmap(boolean isAsBitmap) {
            this.isAsBitmap = isAsBitmap;
            return this;
        }

        public Builder intoTarget(Target<Bitmap> target) {
            this.target = target;
            return this;
        }

        public Builder corner(int cornerSize) {
            this.cornerSize = cornerSize;
            return this;
        }

        public Builder url(Object url) {
            this.url = url;
            return this;
        }

        public Builder into(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder placeholder(int placeholderRes) {
            this.defaultRes = placeholderRes;
            return this;
        }

        public Builder error(int errorRes) {
            this.errorRes = errorRes;
            return this;
        }

        public Builder isRound(boolean isRound) {
            this.isRound = isRound;
            return this;
        }

        public ImageConfig build() {
            return new ImageConfig(this);
        }
    }
}
