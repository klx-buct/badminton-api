package com.example.badmintonapi.domain;

public class Team {
    private int id;
    private int matchId;
    private String name;
    private String people;
    private int caption;
    private String grade;
    private String victor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public int getCaption() {
        return caption;
    }

    public void setCaption(int caption) {
        this.caption = caption;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getVictor() {
        return victor;
    }

    public void setVictor(String victor) {
        this.victor = victor;
    }
}
