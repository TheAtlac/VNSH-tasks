package ru.nshi.model;

public class Song {

    public static final String TABLE = "song";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    public static final String ARTIST_ID_COLUMN = "artist_id";

    public static final String ARTIST_NAME_COLUMN = "artist_name";


    public static final String AUDITIONS_COLUMN = "auditions";

    private String artistName;
    private Long artistId;
    private String name;
    private int auditions;
    private Long id;

    public Song(Long id, String name, int auditions, Long artistId, String artistName) {
        this.artistName = artistName;
        this.name = name;
        this.auditions = auditions;
        this.artistId = artistId;
        this.id = id;
    }

    public Song() {
        this.artistName = null;
        this.name = null;
        this.auditions = 0;
        this.artistId = 0L;
        this.id = 0L;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuditions() {
        return auditions;
    }

    public void setAuditions(int auditors) {
        this.auditions = auditors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtistId() { return artistId; }

    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public void listen(Long auditions) {this.auditions += auditions;}

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", auditions=" + auditions +
                ", artistName='" + artistName + '\'' +
                ", artistId='" + artistId + '\'' +
                '}';
    }
}
