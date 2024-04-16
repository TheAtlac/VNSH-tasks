package ru.nshi.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nshi.model.Artist;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@RestController
public interface ArtistController {

    @GetMapping("/artists")
    public List<Artist> getAllArtists();

    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable Long id);
    @RequestMapping(path = "/export", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String fileName) throws IOException;

    @GetMapping("/d")
    public void downloadCsv(HttpServletResponse response) throws IOException;
}
