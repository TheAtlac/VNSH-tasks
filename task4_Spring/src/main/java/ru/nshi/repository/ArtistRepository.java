package ru.nshi.repository;

import ru.nshi.model.Artist;
import ru.nshi.model.Song;

import java.util.List;

public interface ArtistRepository {
    List<Artist> getAllArtists();
    Artist getArtistById(Long id);
    List<Song> getSongsByArtistId(Long id);
    Artist addArtist(Artist artist);
    Artist updateArtist(Artist artist);
    Artist deleteArtist(Long id);
}

