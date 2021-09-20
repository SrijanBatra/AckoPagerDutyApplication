package com.company.entities;

public class CreateTeamRequest {
    Team team;
    Developers[] developers;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Developers[] getDevelopers() {
        return developers;
    }

    public void setDevelopers(Developers[] developers) {
        this.developers = developers;
    }
}
