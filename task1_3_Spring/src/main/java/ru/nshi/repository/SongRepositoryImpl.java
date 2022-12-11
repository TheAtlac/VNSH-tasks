package ru.nshi.repository;

import org.springframework.stereotype.Repository;
import ru.nshi.error.SongNotFoundException;
import ru.nshi.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SongRepositoryImpl implements SongRepository {
    private final Map<Integer, Song> data = new ConcurrentHashMap<>();
    private final AtomicInteger autoId = new AtomicInteger(0);

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Song getSongById(Integer id) {
        Song result = data.get(id);
        if (result == null) {
            throw new SongNotFoundException("song not found");
        }
        return result;
    }

    @Override
    public Song save(Song song) {
        int id = autoId.incrementAndGet();
        song.setId(id);
        data.put(id, song);
        return song;
    }

    @Override
    public Song updateSongById(Integer id, Song song) {
        Song oldValue = getSongById(id);
        song.setId(id);
        data.put(id, song);
        return song;
    }

    @Override
    public Song deleteSongById(Integer id) {
        Song result = data.remove(id);
        if (result == null) {
            throw new SongNotFoundException("song not found");
        }
        return result;
    }
}