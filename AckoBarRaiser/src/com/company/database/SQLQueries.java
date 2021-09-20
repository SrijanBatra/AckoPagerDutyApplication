package com.company.database;

public class SQLQueries {
    public static String INSERT_TEAM = "INSERT INTO team (id, name) VALUES (?, ?);";
    public static String INSERT_DEVELOPER = "INSERT INTO developer (phone_number, name, team_id)  VALUES (?, ?, ?);";
    public static String FETCH_MAX_TEAM_ID = "SELECT MAX(id) FROM team";
}
