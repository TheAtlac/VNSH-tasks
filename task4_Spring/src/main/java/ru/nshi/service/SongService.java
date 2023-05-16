package ru.nshi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nshi.model.ListenRequest;
import ru.nshi.model.Song;
import ru.nshi.repository.SongRepositoryImpl;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {
    @Autowired
    private final SongRepositoryImpl songRepositoryImpl;

    public List<Song> getAllSongs() {
        return songRepositoryImpl.getAllSongs();
    }

    public Song getSongById(Long id) {
        return songRepositoryImpl.getSongById(id);
    }

    public Song addSong (Song song) {
        return songRepositoryImpl.addSong(song);
    }

    public Song updateSong(Long id, Song song) {
        return songRepositoryImpl.updateSong(id, song);
    }

    public Song deleteSong(Long id) {
        return songRepositoryImpl.deleteSong(id);
    }

    public List<Song> getSortedSongsByAuditions(Integer limit) {
        return songRepositoryImpl.getSortedSongsByAuditions(limit);
    }

    public List<Song> listenSongByIds(ListenRequest listenRequest) {
        return songRepositoryImpl.listenSongByIds(listenRequest);
    }

    public Song listenSongById(Long songId, ListenRequest listenRequest) {
        return songRepositoryImpl.listenSongById(songId, listenRequest);
    }

    public List<Song> getArtistSongsById(Long artistId) {
        return songRepositoryImpl.getArtistSongsById(artistId);
    }
}
