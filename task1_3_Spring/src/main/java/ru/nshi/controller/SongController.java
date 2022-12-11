package ru.nshi.controller;

import org.springframework.web.bind.annotation.*;
import ru.nshi.model.Song;

import java.util.List;

@RequestMapping(SongController.MAPPING)
public interface SongController {
    String MAPPING  = "/songs";

    @GetMapping(value = "", produces = "application/json")
    List<Song> getSongs();

    @GetMapping(value = "/{id}", produces = "application/json")
    Song getSongById(@PathVariable Integer id);

    @PostMapping(value = "", produces = "application/json")
    Song createSong(@RequestBody Song song);

    @PutMapping(value = "/{id}", produces = "application/json")
    Song updateSongById(@PathVariable Integer id, @RequestBody Song song);

    @DeleteMapping(value = "/{id}", produces = "application/json")
    Song deleteSongById(@PathVariable Integer id);

    @GetMapping(value = "/listen", produces = "application/json")
    List<Song> getSortedSongsByAuditions();
}