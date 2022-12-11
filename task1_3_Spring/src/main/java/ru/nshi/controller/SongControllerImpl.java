package ru.nshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.error.SongException;
import ru.nshi.error.SongNotFoundException;
import ru.nshi.error.SongValidationException;
import ru.nshi.model.Error;
import ru.nshi.model.Song;
import ru.nshi.service.SongService;

import java.util.Comparator;
import java.util.List;

@RestController
public class SongControllerImpl implements SongController {
    @Autowired
    private SongService service;

    @Override
    public List<Song> getSongs() {
        return service.getSongs();
    }

    @Override
    public Song getSongById(Integer id) {
        checkId(id);
        return service.getSongById(id);
    }

    @Override
    public Song createSong(Song song) {
        checkSong(song);
        return service.save(song);
    }

    @Override
    public Song updateSongById(Integer id, Song song) {
        checkId(id);
        checkSong(song);
        return service.updateSongById(id, song);
    }

    @Override
    public Song deleteSongById(Integer id) {
        checkId(id);
        return service.deleteSongById(id);
    }

    @Override
    public List<Song> getSortedSongsByAuditions() {
        List<Song> songs = service.getSongs();
        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song song1, Song song2) {
                return song2.getAuditors() - song1.getAuditors();
            }
        });
        return songs;
    }

    @ExceptionHandler(SongValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleValidationException(SongValidationException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(SongNotFoundException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(SongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleNotFoundException(SongException ex) {
        return new Error(ex.getMessage());
    }

    void checkId(Integer id) {
        if (id == null) {
            throw new SongValidationException("song id cannot be null");
        }
        if (id < 1) {
            throw new SongValidationException("song id cannot be less than 1");
        }
    }

    void checkSong(Song song) {
        if (song == null || song.getName() == null || song.getName().isEmpty()
                || song.getArtistName() == null || song.getArtistName().isEmpty()) {
            throw new SongValidationException("song or song name or artist name cannot be null");
        }
        int auditors = song.getAuditors();
        if (auditors < 0) {
            throw new SongValidationException("count of auditors cannot less than 0");
        }
    }
}