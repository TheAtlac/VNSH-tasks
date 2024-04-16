package ru.nshi.repository;

import ru.nshi.model.ListenRequest;
import ru.nshi.model.Song;

import java.util.List;

public interface SongRepository {
    List<Song> getAllSongs();
    Song getSongById(Long id);
    Song addSong(Song song);
    Song updateSong(Long id, Song song);
    Song deleteSong(Long id);
    List<Song> getSortedSongsByAuditions(Integer limit);
    List<Song> listenSongByIds(ListenRequest listenRequest);
    Song listenSongById(Long id, ListenRequest listenRequest);
    List<Song> getArtistSongsById(Long artistId);
}
