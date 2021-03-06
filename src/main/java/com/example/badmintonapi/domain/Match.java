package com.example.badmintonapi.domain;

public class Match {
    private int id;

    private String name;

    private String introduce;

    private String address;

    private String prize;

    private String begTime;

    private String endTime;

    private int limit;

    private String limitPeople;

    private int actualPlayer;

    private int player;

    private int referee;

    private int actualReferee;

    private int hintCount;

    private int status;

    private String time;

    private int isTeamUp;

    private int teamUpLimit;

    private String enterId;

    private String refereeId;

    private int signNum;

    private int isPrize;

    private int[] matchType;

    public int[] getMatchType() {
        return matchType;
    }

    public void setMatchType(int[] matchType) {
        this.matchType = matchType;
    }

    public int getIsPrize() {
        return isPrize;
    }

    public void setIsPrize(int isPrize) {
        this.isPrize = isPrize;
    }

    public int getSignNum() {
        return signNum;
    }

    public void setSignNum(int signNum) {
        this.signNum = signNum;
    }

    public String getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public int getIsTeamUp() {
        return isTeamUp;
    }

    public void setIsTeamUp(int isTeamUp) {
        this.isTeamUp = isTeamUp;
    }

    public int getTeamUpLimit() {
        return teamUpLimit;
    }

    public void setTeamUpLimit(int teamUpLimit) {
        this.teamUpLimit = teamUpLimit;
    }

    public String getEnterId() {
        return enterId;
    }

    public void setEnterId(String enterId) {
        this.enterId = enterId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getBegTime() {
        return begTime;
    }

    public void setBegTime(String begTime) {
        this.begTime = begTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLimitPeople() {
        return limitPeople;
    }

    public void setLimitPeople(String limitPeople) {
        this.limitPeople = limitPeople;
    }

    public int getActualPlayer() {
        return actualPlayer;
    }

    public void setActualPlayer(int actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getReferee() {
        return referee;
    }

    public void setReferee(int referee) {
        this.referee = referee;
    }

    public int getActualReferee() {
        return actualReferee;
    }

    public void setActualReferee(int actualReferee) {
        this.actualReferee = actualReferee;
    }

    public int getHintCount() {
        return hintCount;
    }

    public void setHintCount(int hintCount) {
        this.hintCount = hintCount;
    }
}
