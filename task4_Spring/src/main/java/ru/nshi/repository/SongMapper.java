package ru.nshi.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.nshi.error.ApiException;
import ru.nshi.model.Song;

import java.sql.ResultSet;
import java.sql.SQLException;

class SongMapper implements RowMapper<Song> {
    @Override
        public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
            Song song = new Song();
            song.setId(rs.getLong("id"));
            song.setArtistId(rs.getLong("artist_id"));
            song.setArtistName(rs.getString("artist_name"));
            song.setName(rs.getString("name"));
            song.setAuditions(rs.getInt("auditions"));
            return song;
        }
}