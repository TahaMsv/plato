package com.ApSpring.plato.Games;

public class Player {
    private String score;
    private String username;
    private String rank;

    public Player(String score, String username, String rank) {
        this.score = score;
        this.username = username;
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public String getRank() {
        return rank;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
