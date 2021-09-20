package com.company.database;

import com.company.entities.Developers;
import com.company.entities.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class executes queries from the class SQLQueries, using the connection object of the class MySQLConnector.
 */
public class QueryExecutor {
    public int fetchMaxTeamId(Connection mySQLConnection) {
        try {
            PreparedStatement preparedStatement = mySQLConnection.prepareStatement(SQLQueries.FETCH_MAX_TEAM_ID);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                int maxId = rs.getInt(1);
                System.out.println("Fetched the max id: " + maxId);
                return maxId;
            }
            else {
                System.out.println("Result set empty");
                return 0;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    /**
     * Creates database entries for team table.
     * @param team
     * @param teamId
     * @param mySQLConnection
     * @return Number of teams created.
     */
    public int createTeam(Team team, int teamId, Connection mySQLConnection) {
        try {
            PreparedStatement preparedStatement = mySQLConnection.prepareStatement(SQLQueries.INSERT_TEAM);
            addTeamParametersToQuery(preparedStatement, team, teamId);
            int i = preparedStatement.executeUpdate();
            System.out.println("Inserted: " + i + " teams to the database in the team table.");
            return i;
        }
        catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public int createDevelopers(Developers[] developers, int teamId, Connection mySQLConnection) {
        try {
            PreparedStatement preparedStatement = mySQLConnection.prepareStatement(SQLQueries.INSERT_DEVELOPER);
            AtomicInteger i = new AtomicInteger();
            Arrays.stream(developers).forEach(developer -> {
                try {
                    addDeveloperParametersToQuery(preparedStatement, developer);
                    preparedStatement.setInt(3, teamId);
                    i.addAndGet(preparedStatement.executeUpdate());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Inserted " + i.get() + " developers into the developers table.");
            return i.get();
        }
        catch (Exception ex) {
            System.out.println(ex);
            return 0;
        }
    }

    private void addDeveloperParametersToQuery(PreparedStatement preparedStatement, Developers developer) throws SQLException {
        preparedStatement.setString(1, developer.getPhoneNumber());
        preparedStatement.setString(2, developer.getName());
    }

    private void addTeamParametersToQuery(PreparedStatement preparedStatement, Team team, int teamId) throws SQLException {
        preparedStatement.setInt(1, teamId);
        preparedStatement.setString(2, team.getName());
    }
}
