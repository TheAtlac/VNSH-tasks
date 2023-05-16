package ru.nshi.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.nshi.model.ListenRequest;
import ru.nshi.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class SongRepositoryImpl implements SongRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Song> getAllSongs() {
        String sql = "SELECT * FROM songs";
        return jdbcTemplate.query(sql, new SongMapper());
    }

    @Override
    public Song getSongById(Long id) {
        String sql = "SELECT * FROM songs WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SongMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Song addSong(Song song) {

        String sql1 = "INSERT INTO artists (name) SELECT ? WHERE NOT EXISTS (SELECT * FROM artists WHERE name = ?)";
        jdbcTemplate.update(sql1, song.getArtistName(), song.getArtistName());

        String sql3 = "SELECT id FROM artists WHERE name = ?";
        Long artistId = jdbcTemplate.queryForObject(sql3, Long.class, song.getArtistName());

//        String sql4 = "SELECT id FROM songs WHERE name = ?";
//        Long id = jdbcTemplate.queryForObject(sql4, Long.class, song.getName());
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("songs")
                .usingGeneratedKeyColumns("id");
        Number id = jdbcInsert.executeAndReturnKey(
                Map.of("name", song.getName(), "artist_id", artistId,
                        "artist_name", song.getArtistName(), "auditions", song.getAuditions()));

        song.setArtistId(artistId);
        song.setId(id.longValue());
        return song;
    }

    @Override
    public Song updateSong(Long id, Song song) {
        String sql1 = "INSERT INTO artists (name) SELECT ? WHERE NOT EXISTS (SELECT * FROM artists WHERE name = ?)";
        jdbcTemplate.update(sql1, song.getArtistName(), song.getArtistName());

        String sql3 = "SELECT id FROM artists WHERE name = ?";
        Long artistId = jdbcTemplate.queryForObject(sql3, Long.class, song.getArtistName());

        String sql = "UPDATE songs SET artist_id=?, artist_name=?, name=?, auditions=? WHERE id=?";
        song.setArtistId(artistId);
        song.setId(id);
        jdbcTemplate.update(sql, artistId, song.getArtistName(), song.getName(), song.getAuditions(), id);
        return song;
    }

    @Override
    public Song deleteSong(Long id) {
        Song song = getSongById(id);
        String sql = "DELETE FROM songs WHERE id=?";
        jdbcTemplate.update(sql, id);
        return song;
    }

    @Override
    public List<Song> getSortedSongsByAuditions(Integer limit) {
        String sql = "SELECT * FROM songs ORDER BY auditions DESC LIMIT " + limit;
        return jdbcTemplate.query(sql, new SongMapper());
//        String sql = "SELECT * FROM songs";
//        return jdbcTemplate.query(sql, new SongRowMapper());
    }

    @Override
    public List<Song> listenSongByIds(ListenRequest listenRequest) {
        List<Song> songs = new ArrayList<Song>();
        List<Long> ids = listenRequest.getSongs();
        Integer auditions = listenRequest.getAuditions();
        for (Long songId : ids) {
            listenSongById(songId, listenRequest);
            songs.add(getSongById(songId));
        }
        return songs;
    }

    @Override
    public Song listenSongById(Long id, ListenRequest listenRequest) {
        Song song = getSongById(id);
        Integer auditions = listenRequest.getAuditions();
        String sql = "UPDATE songs SET auditions = " + (song.getAuditions() + listenRequest.getAuditions()) +
                " WHERE id=?";
        jdbcTemplate.update(sql, id);

        return getSongById(id);
    }

    @Override
    public List<Song> getArtistSongsById(Long artistId) {
        String sql = "SELECT * FROM songs WHERE artist_id=?";
        return jdbcTemplate.query(sql, new Object[]{artistId}, new SongMapper());
    }

}