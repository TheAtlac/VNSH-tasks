package ru.nshi.model;

import liquibase.pro.packaged.L;

import java.util.List;

public class ListenRequest {
    private Integer auditions;

    private List<Long> songs;

    public Integer getAuditions() {
        return auditions;
    }

    public void setAuditions(Integer auditions) {
        this.auditions = auditions;
    }

    public List<Long> getSongs() {
        return songs;
    }

    public void setSongs(List<Long> songs) {
        this.songs = songs;
    }
}
