package com.example.badmintonapi.domain;

public class MatchType {
    private int id;
    private int type;
    private String text;
    private int num;
    private int matchId;

    public int getId() {
        return id;
    }

    public MatchType() {
    }

    public MatchType(int type, String text, int num, int matchId) {
        this.type = type;
        this.text = text;
        this.num = num;
        this.matchId = matchId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
