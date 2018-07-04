package com.example.shubhamr.mino.HelperClasses;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.shubhamr.mino.ModelClasses.songModel;

import java.util.ArrayList;
import java.util.List;

public class songRetrievingClass {



    //Retrieve list of songs
    public static List<songModel> getSongList(Context context){

        List<songModel> songList = new ArrayList<songModel>();
        songModel song;
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = context.getContentResolver().query(musicUri, null,
                null, null, null);

        if(songCursor!=null&&songCursor.getCount()>0){

            while(songCursor.moveToNext()){

                //Getting song id, title, artist

                String id = songCursor.getString(songCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID));
                String songName = songCursor.getString(songCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE));
                String songArtist = songCursor.getString(songCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST));
                String albumID = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String imagePath = "";

                //Song Image is not a part of recent used uri so new cursor is created
                Cursor imageCursor = context.getContentResolver().query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                        new String[] {MediaStore.Audio.Albums.ALBUM_ART},
                        MediaStore.Audio.Albums._ID+ "=?",
                        new String[] {albumID},
                        null);
                if (imageCursor!=null&&imageCursor.moveToFirst()) {
                    imagePath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                    imageCursor.close();
                }

                //Putting data in object
                song = new songModel(id,songName,songArtist,imagePath);
                songList.add(song);
            }
            songCursor.close();
            return songList;
        }
        else{return null;}
    }








}
