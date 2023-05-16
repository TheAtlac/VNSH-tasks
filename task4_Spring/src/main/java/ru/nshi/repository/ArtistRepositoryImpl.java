package ru.nshi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.nshi.model.Artist;
import ru.nshi.model.Song;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ArtistRepositoryImpl implements ArtistRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Artist> getAllArtists() {
        String sql = "SELECT * FROM artists";
        return jdbcTemplate.query(sql, new ArtistMapper());
    }

    @Override
    public Artist getArtistById(Long id) {
        String sql = "SELECT * FROM artists WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ArtistMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Song> getSongsByArtistId(Long id) {
        String sql = "SELECT * FROM songs WHERE artist_id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, new SongMapper());
    }

    @Override
    public Artist addArtist(Artist artist) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("artists")
                .usingGeneratedKeyColumns("id");
        Number id = jdbcInsert.executeAndReturnKey(
                Map.of("name", artist.getName()));
//        String sql = "INSERT INTO artists (name) VALUES (?)";
//        jdbcTemplate.update(sql, artist.getName());
        artist.setId(id.longValue());
        return artist;
    }

    @Override
    public Artist updateArtist(Artist artist) {
        String sql = "UPDATE artists SET name=? WHERE id=?";
        jdbcTemplate.update(sql, artist.getName(), artist.getId());
        return artist;
    }

    @Override
    public Artist deleteArtist(Long id) {
        String sql = "DELETE FROM artists WHERE id=?";
        jdbcTemplate.update(sql, id);
        return null;
    }

//    private static class ArtistRowMapper implements RowMapper<Artist> {
//        @Override
//        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Artist artist = new Artist();
//            artist.setId(rs.getLong("id"));
//            artist.setName(rs.getString("name"));
//            return artist;
//        }
//    }

//    static class SongRowMapper implements RowMapper<Song> {
//        @Override
//        public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Song song = new Song();
//            song.setId(rs.getLong("id"));
//            song.setArtistId(rs.getLong("artist_id"));
//            song.setArtistName(rs.getString("artist_name"));
//            song.setName(rs.getString("name"));
//            song.setAuditions(rs.getInt("auditions"));
//            return song;
//        }
//    }
}
