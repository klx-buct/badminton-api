package com.example.badmintonapi.domain;

public class MatchResult {
    private int id;
    private String grade;
    private int matchId;
    private String contestant;

    private String team1;

    private String team2;

    private String address;

    private int referee;

    private String refereeName;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getReferee() {
        return referee;
    }

    public void setReferee(int referee) {
        this.referee = referee;
    }

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getContestant() {
        return contestant;
    }

    public void setContestant(String contestant) {
        this.contestant = contestant;
    }
}
