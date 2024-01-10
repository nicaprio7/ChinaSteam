package com.xin.bean;

/**
 * @author wudixin
 * @date 2024/1/8 14:02
 */
public class Game {
    private int gameId;
    private String gameName;
    private int gamePrice;
    private int gameAmount;
    private String gameDescribe;

    public Game(int gameId, String gameName, int gamePrice, int gameAmount, String gameDescribe) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
        this.gameAmount = gameAmount;
        this.gameDescribe = gameDescribe;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(int gamePrice) {
        this.gamePrice = gamePrice;
    }

    public int getGameAmount() {
        return gameAmount;
    }

    public void setGameAmount(int gameAmount) {
        this.gameAmount = gameAmount;
    }

    public String getGameDescribe() {
        return gameDescribe;
    }

    public void setGameDescribe(String gameDescribe) {
        this.gameDescribe = gameDescribe;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", gameName='" + gameName + '\'' +
                ", gamePrice=" + gamePrice +
                ", gameAmount=" + gameAmount +
                ", gameDescribe='" + gameDescribe + '\'' +
                '}';
    }
}
