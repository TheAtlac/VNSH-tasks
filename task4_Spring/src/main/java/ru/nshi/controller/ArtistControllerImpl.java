package ru.nshi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.error.SongValidationException;
import ru.nshi.model.Artist;
import ru.nshi.service.ArtistService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArtistControllerImpl implements ArtistController {

    @Autowired
    private final ArtistService artistService;


    @Override
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @Override
    public Artist getArtistById(@PathVariable Long id) {
        try {
            Artist artist = artistService.getArtistById(id);
            artist.getId();
            return artist;
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }
    }


}
