package com.projects;

import java.util.Date;

public class Match {
    private FootBallClub teamA;
    private FootBallClub teamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;

    public FootBallClub getTeamA() {
        return teamA;
    }

    public FootBallClub getTeamB() {
        return teamB;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public Date getDate() {
        return date;
    }

    public void setTeamA(FootBallClub teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(FootBallClub teamB) {
        this.teamB = teamB;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
