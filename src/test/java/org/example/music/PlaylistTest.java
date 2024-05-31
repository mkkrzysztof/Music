package org.example.music;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PlaylistTest {
    @Test
    public void isEmpty(){
        PlayList playlist = new PlayList();
        assertTrue(playlist.isEmpty());
    }
    @Test
    public void hasOne(){
        PlayList playlist = new PlayList();
        playlist.add(new Song("Heavy Young Heathens", "Being Evil Has a Price", 194));
        assertEquals(1,playlist.size());
    }
    @Test
    public void correctlyAdded(){
        Song song = new Song("Heavy Young Heathens", "Being Evil Has a Price", 194);
        PlayList playlist = new PlayList();
        playlist.add(song);
        assertEquals(song,playlist.get(0));
    }
    @Test
    public void correctlyAdded2(){
        Song song = new Song("Heavy Young Heathens", "Being Evil Has a Price", 194);
        PlayList playlist = new PlayList();
        playlist.add(new Song("Heavy Young Heathens", "Being Evil Has a Price", 194));
        assertEquals(song,playlist.get(0));
    }
    @Test
    public void addSecondTest(){
        Song song1 = new Song("Heavy Young Heathens", "Being Evil Has a Price", 203);
        Song song2 = new Song("The Forest Rangers", "John The Revelator", 334);
        Song song3 = new Song("Lee DeWyze", "Blackbird Song", 254);

        PlayList playlist = new PlayList();
        playlist.add(song1);
        playlist.add(song2);
        playlist.add(song3);

        assertEquals(song2,playlist.atSecond(350));
        assertEquals(song1,playlist.atSecond(203));
        assertEquals(song3,playlist.atSecond(600));
    }
    @Test
    public void IndexOutOfBoundExceptionTestNegative(){
        Song song1 = new Song("Heavy Young Heathens", "Being Evil Has a Price", 203);
        Song song2 = new Song("The Forest Rangers", "John The Revelator", 334);
        Song song3 = new Song("Lee DeWyze", "Blackbird Song", 254);
        PlayList playlist = new PlayList();
        playlist.add(song1);
        playlist.add(song2);
        playlist.add(song3);

        IndexOutOfBoundsException exception =  assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(-200));
        assertEquals("Negative Time: -200",exception.getMessage());
    }
    @Test
    public void IndexOutOfBoundExceptionTestExcceded(){
        Song song1 = new Song("Heavy Young Heathens", "Being Evil Has a Price", 203);
        Song song2 = new Song("The Forest Rangers", "John The Revelator", 334);
        Song song3 = new Song("Lee DeWyze", "Blackbird Song", 254);
        PlayList playlist = new PlayList();
        playlist.add(song1);
        playlist.add(song2);
        playlist.add(song3);

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(20000));
        assertEquals("Time exceeded: 20000",exception.getMessage());
    }
    @ParameterizedTest
    @MethodSource("provideIndexesAndExpectedSongs")
    public void testReadParameterized(int index, Song expectedSong) throws SQLException {
        Optional<Song> song = Song.Persistence.read(index);
        assertEquals(expectedSong, song.orElse(null));
    }

    static Stream<Arguments> provideIndexesAndExpectedSongs() {
        return Stream.of(
                arguments(3, new Song("Led Zeppelin", "Stairway to Heaven", 482)),
                arguments(47, new Song("The Doors", "Riders on the Storm", 434)),
                arguments(-1, null),
                arguments(100, null)
        );
    }
}
