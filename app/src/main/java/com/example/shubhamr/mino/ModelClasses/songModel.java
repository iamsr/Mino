package com.example.shubhamr.mino.ModelClasses;

public class songModel {

    private String id;
    private String songName;
    private String songArtist;
    private String imagePath;

    public songModel(){}

    public songModel(String id,String songName,String songArtist,String imagePath){
        this.id= id;
        this.songName=songName;
        this.songArtist=songArtist;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getSongName() {
        return songName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}


