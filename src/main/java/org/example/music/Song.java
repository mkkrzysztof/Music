package org.example.music;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public record Song(String artist, String title, int length) {
    public static class Persistence{

        public static Optional<Song> read(int index) throws SQLException {
            String query = "SELECT artist, title, duration FROM song WHERE id = ?";
            PreparedStatement statement = DatabaseConnection.getConnection("connection").prepareStatement(query);
            statement.setInt(1, index);
            ResultSet results = statement.getResultSet();
            if(results.next()) {
                return Optional.of(new Song(
                        results.getString("artist"),
                        results.getString("title"),
                        results.getInt("length")
                ));
            }
            return Optional.empty();
        }
    }
}

