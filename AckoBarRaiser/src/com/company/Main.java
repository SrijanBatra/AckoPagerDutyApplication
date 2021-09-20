package com.company;

import com.company.activity.CreateTeam;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Executing the program.. ");

        CreateTeam createTeam = new CreateTeam();
        //int result = createTeam.createNewTeam("{\"team\": {\"name\": \"teamBETA\"}}");
        int result = createTeam.createNewTeam("{\"team\": {\"name\": \"claims\"}, \"developers\": [{\"name\": \"someone\", \"phoneNumber\":\n" +
                "\"9999999999\"}, {\"name\": \"somebody\", \"phoneNumber\": \"9111111111\"}]}");

        System.out.println("Created: " + result + " teams");
    }
}
