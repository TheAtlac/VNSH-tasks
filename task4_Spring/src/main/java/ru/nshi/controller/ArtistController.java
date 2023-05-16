package ru.nshi.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nshi.model.Artist;

import java.util.List;

@RestController
public interface ArtistController {

    @GetMapping("/artists")
    public List<Artist> getAllArtists();

    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable Long id);
}
