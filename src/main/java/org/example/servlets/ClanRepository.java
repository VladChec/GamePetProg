package org.example.servlets;

import java.sql.*;

public class ClanRepository {

    public void createClan(String name) {
        String sql = "INSERT INTO clans (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Clan getClanById(long clanId) {
        String sql = "SELECT * FROM clans WHERE id = ?";
        Clan clan = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, clanId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                clan = new Clan();
                clan.setId(resultSet.getLong("id"));
                clan.setName(resultSet.getString("name"));
                clan.setGold(resultSet.getInt("gold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clan;
    }

    public void updateClanGold(long clanId, int newGold) {
        String sql = "UPDATE clans SET gold = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newGold);
            statement.setLong(2, clanId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
