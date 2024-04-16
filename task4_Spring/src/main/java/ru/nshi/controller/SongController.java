package ru.nshi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nshi.model.ListenRequest;
import ru.nshi.model.Song;

import java.util.List;

//@RequestMapping(SongController.MAPPING)
public interface SongController {



    @GetMapping("/songs")
    public List<Song> getAllSongs();
    @GetMapping("/songs/listen")
    public List<Song> getSortedSongsByAuditions(@RequestParam(required = false, defaultValue = "5") Integer limit);

    @GetMapping("/songs/{id}")
    public Song getSongById(@PathVariable Long id);


    @GetMapping("/artists/{artistId}/songs")
    public List<Song> getArtistSongsById(@PathVariable Long artistId);

    @PostMapping("/songs")
    public ResponseEntity<Song> addSong(@RequestBody Song song);

    @PutMapping("/songs/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song);

    @PostMapping("songs/listen")
    public List<Song> listenSongByIds(@RequestBody ListenRequest listenRequest);

    @PostMapping("/songs/{id}/listen")
    public ResponseEntity<Song> listenSongById(@PathVariable Long id, @RequestBody ListenRequest listenRequest);

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable Long id);
}
