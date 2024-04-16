package ru.nshi.controller;

import lombok.AllArgsConstructor;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.error.SongValidationException;
import ru.nshi.model.Artist;
import ru.nshi.model.ExcelFileExporter;
import ru.nshi.service.ArtistService;
import ru.nshi.service.FooService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

//@RestController
@AllArgsConstructor
public class ArtistControllerImpl implements ArtistController {

    @Autowired
    private final ArtistService artistService;

    @Autowired
    private final FooService fooService;

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
    @Override
    public ResponseEntity<Resource> download(String fileName) throws IOException {

        ByteArrayResource resource = fooService.export(fileName);

        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }


    @Override
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile();
        IOUtils.copy(stream, response.getOutputStream());
    }

}
