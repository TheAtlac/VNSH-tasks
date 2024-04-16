package ru.nshi.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.nshi.error.ApiException;
import ru.nshi.model.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;

class ArtistMapper implements RowMapper<Artist> {
    @Override
    public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Artist artist = new Artist();
            artist.setId(rs.getLong("id"));
            artist.setName(rs.getString("name"));
            return artist;
        }
}