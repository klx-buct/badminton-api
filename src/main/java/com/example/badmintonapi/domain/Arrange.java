package com.example.badmintonapi.domain;

public class Arrange {
    private int id;
    private int matchId;
    private String name;
    private int allNum;
    private int nowNum;
    private int round;
    private int status;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
