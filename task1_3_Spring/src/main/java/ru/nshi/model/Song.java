package ru.nshi.model;

public class Song {
    private String artistName;

    private String name;

    private int auditions;

    private int id;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuditions() {
        return auditions;
    }

    public void setAuditions(int auditors) {
        this.auditions = auditors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void listen(int auditions) {this.auditions += auditions;}
}
