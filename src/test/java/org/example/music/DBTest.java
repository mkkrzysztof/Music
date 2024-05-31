package org.example.music;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBTest {
    @BeforeAll
    static void openDB(){
        DatabaseConnection.connect("songs.db","connection");
    }
    @Test
    public void songRead() {
        Optional<Song> song;
        try {
            song = Song.Persistence.read(2);
            assertTrue(song.isPresent());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void closeDB(){
        DatabaseConnection.disconnect("connection");
    }
}
