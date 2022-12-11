package ru.nshi.service;

import ru.nshi.model.Song;

import java.util.List;

public interface SongService {
    List<Song> getSongs();

    Song getSongById(Integer id);

    Song save(Song song);

    Song updateSongById(Integer id, Song song);

    Song deleteSongById(Integer id);

//    Song doHandleSong(Song song);
}