--liquibase formatted sql
--changeset TheAtlac:init_artist
CREATE TABLE artists (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(63) UNIQUE
);

--changeset TheAtlac:init_song
CREATE TABLE songs (
    id serial PRIMARY KEY,
    artist_id int,
    artist_name varchar(63),
    name varchar(127),
    auditions long
);

--changeset TheAtlac:init_artist_and_song_constraints
ALTER TABLE songs ADD CONSTRAINT fk_post_artists
FOREIGN KEY (artist_id)
REFERENCES artists (id);