package com.example.badmintonapi.domain;

public class MatchRound {
    private int id;
    private int round;
    private String text;
    private int matchId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
