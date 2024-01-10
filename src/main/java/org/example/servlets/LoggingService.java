package org.example.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoggingService {

    public void logGoldChanges(long clanId, int previousGold, int newGold, String reason) {
        String sql = "INSERT INTO clan_gold_changes (clan_id, previous_gold, new_gold, reason) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, clanId);
            statement.setInt(2, previousGold);
            statement.setInt(3, newGold);
            statement.setString(4, reason);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
