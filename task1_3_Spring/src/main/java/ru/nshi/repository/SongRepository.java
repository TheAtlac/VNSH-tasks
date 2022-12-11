package ru.nshi.repository;

import ru.nshi.model.Song;

import java.util.List;

public interface SongRepository {
    List<Song> findAll();

    Song getSongById(Integer id);

    Song save(Song song);

    Song updateSongById(Integer id, Song song);

    Song deleteSongById(Integer id);
}