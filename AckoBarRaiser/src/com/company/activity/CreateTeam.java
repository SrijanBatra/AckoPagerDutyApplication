package com.company.activity;

import com.company.database.MySQLConnector;
import com.company.database.QueryExecutor;
import com.company.entities.CreateTeamRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;

/**
 * This is the create team API.
 * It takes input of the following format:
 * {"team": {"name": "claims"}, "developers": [{"name": "someone", "phone_number":
 * "9999999999"}, {"name": "somebody", "phoneNumber": "9111111111"}]}
 *
 * It creates corresponding entries in the tables: team and developers.
 * The team and developer entities are defined in the entities package.
 */
public class CreateTeam {
    public int createNewTeam(String jsonRequest) {
        try {
            CreateTeamRequest createTeamRequest = mapJsonRequestToCreateTeamRequest(jsonRequest);
            QueryExecutor queryExecutor = new QueryExecutor();
            MySQLConnector mySQLConnector = new MySQLConnector();
            Connection databaseConnection = mySQLConnector.createConnection();

            // Generate team id here
            int teamId = queryExecutor.fetchMaxTeamId(databaseConnection);
            teamId = teamId + 1;

            int result = queryExecutor.createTeam(createTeamRequest.getTeam(), teamId, databaseConnection);
            System.out.println("Created " + result + " teams.");

            // Create developers
            result = queryExecutor.createDevelopers(createTeamRequest.getDevelopers(), teamId, databaseConnection);
            System.out.println("Created " + result + " developers for teamId: " + teamId);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        return 0;
    }

    private CreateTeamRequest mapJsonRequestToCreateTeamRequest(String jsonRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonRequest, CreateTeamRequest.class);
    }
}
