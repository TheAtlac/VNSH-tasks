package ru.nshi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nshi.model.Artist;
import ru.nshi.model.Song;
import ru.nshi.repository.ArtistRepositoryImpl;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {
    @Autowired
    private final ArtistRepositoryImpl artistRepositoryImpl;

    public List<Artist> getAllArtists() {
        return artistRepositoryImpl.getAllArtists();
    }

    public Artist getArtistById(Long id) {
        return artistRepositoryImpl.getArtistById(id);
    }

    public List<Song> getSongsByArtistId(Long id) {
        return artistRepositoryImpl.getSongsByArtistId(id);
    }

    public Artist addArtist(Artist artist) {
        return artistRepositoryImpl.addArtist(artist);
    }

    public Artist updateArtist(Artist artist) {
        return artistRepositoryImpl.updateArtist(artist);
    }

    public void deleteArtist(Long id) {
        artistRepositoryImpl.deleteArtist(id);
    }
}
