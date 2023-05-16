package ru.nshi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import ru.nshi.error.SongException;
import ru.nshi.error.SongNotFoundException;
import ru.nshi.error.SongValidationException;
import ru.nshi.model.ListenRequest;
import ru.nshi.model.Song;
import ru.nshi.service.SongService;

import java.util.List;

@RestController
@AllArgsConstructor
public class SongControllerImpl implements SongController {

    @Autowired
    private final SongService songService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Song> getAllSongs() {
        return songService.getAllSongs();

    }

    @Override
    public Song getSongById(@PathVariable Long id) {
        try {
            checkId(id);
            Song song = songService.getSongById(id);
            song.getId();
            return song;
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }
    }

    @Override
    public List<Song> getSortedSongsByAuditions(@RequestParam(required = false, defaultValue = "5") Integer limit) {
        return songService.getSortedSongsByAuditions(limit);

    }

    @Override
    public List<Song> getArtistSongsById(@PathVariable Long artistId) {
        try {
            return songService.getArtistSongsById(artistId);
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }

    }

    @Override
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        checkSong(song);
        try {
            return new ResponseEntity<>(songService.addSong(song), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }
    }

    @Override
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        checkSong(song);
        checkId(id);
        try {
            return songService.updateSong(id, song);
        } catch (Exception e) {
            throw new SongValidationException("error.message");
//            return "{\"errorMessage\": \"error.message\"}";
        }
    }

    @Override
    public List<Song> listenSongByIds(@RequestBody ListenRequest listenRequest) {
        List<Long> ids = listenRequest.getSongs();
        for (Long id : ids) {
            checkId(id);
        }
        checkAuditions(listenRequest.getAuditions());
        try {
            return songService.listenSongByIds(listenRequest);
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }
    }

    @Override
    public ResponseEntity<Song> listenSongById(@PathVariable Long id, ListenRequest listenRequest) {
        checkAuditions(listenRequest.getAuditions());
        try {
            return ResponseEntity.ok(songService.listenSongById(id, listenRequest));
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }
    }


    @Override
    public ResponseEntity<Song> deleteSong(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(songService.deleteSong(id));
        } catch (Exception e) {
            throw new SongValidationException("error.message");
        }
    }


    @ExceptionHandler(SongValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleValidationException(SongValidationException ex) {
        throw new SongValidationException("error.message");
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(SongNotFoundException ex) {
        throw new SongValidationException("error.message");
    }

    @ExceptionHandler(SongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleNotFoundException(SongException ex) {
        throw new SongValidationException("error.message");
    }

    void checkId(Long id) {
        if (id == null || id < 1) {
            throw new SongValidationException("error.message");
        }
    }

    void checkAuditions(Integer auditions) {
        if (auditions == null || auditions <= 0) {
            throw new SongValidationException("error.message");
        }
    }


    void checkSong(Song song) {
        if (song == null || song.getName() == null || song.getName().isBlank()
                || song.getArtistName() == null || song.getArtistName().isBlank()) {
            throw new SongValidationException("error.message");
        }
        checkAuditions(song.getAuditions());
    }
}
