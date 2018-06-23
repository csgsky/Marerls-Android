package com.gy.allen.model.local;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class LocalUtil {
    public static Uri getCoverUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }

    public static String getCoverUri(Context context, long albumId) {
        String albumCoverUri = null;
        String uriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{MediaStore.Audio.AlbumColumns.ALBUM_ART};
        Cursor cursor = context.getContentResolver().query(
                Uri.parse(uriAlbums + "/" + albumId),
                projection, null, null, null);
        if (cursor != null && cursor.getCount() > 0 && cursor.getColumnCount() > 0) {
            cursor.moveToNext();
            albumCoverUri = cursor.getString(0);
            cursor.close();
        }
        return albumCoverUri;
    }

    public static String formatTime(long time) {
        if (time > 0) {
            String min = time / (1000 * 60) + "";
            String sec = time % (1000 * 60) + "";
            if (min.length() < 2) {
                min = "0" + time / (1000 * 60) + "";
            } else {
                min = time / (1000 * 60) + "";
            }
            if (sec.length() == 4) {
                sec = "0" + (time % (1000 * 60)) + "";
            } else if (sec.length() == 3) {
                sec = "00" + (time % (1000 * 60)) + "";
            } else if (sec.length() == 2) {
                sec = "000" + (time % (1000 * 60)) + "";
            } else if (sec.length() == 1) {
                sec = "0000" + (time % (1000 * 60)) + "";
            }
            return min + ":" + sec.trim().substring(0, 2);
        }
        return "00:00";
    }

    public static String formatSecondTime(long time) {
        if (time > 0) {
            String min = time / 60 + "";
            String sec = time % 60 + "";
            if (min.length() < 2) {
                min = "0" + time / 60 + "";
            } else {
                min = time / 60 + "";
            }
            if (sec.length() == 2) {
                sec = (time % 60) + "";
            } else if (sec.length() == 1) {
                sec = "0" + (time % 60) + "";
            }
            return min + ":" + sec.trim().substring(0, 2);
        }
        return "00:00";
    }
}
