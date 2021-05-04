package com.example.androidchess33;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SavedGame implements Serializable {
    ArrayList<Move> gameMoves;
    Date datePlayed;
    String gameName;
    boolean draw;
    boolean resign;

    public SavedGame(ArrayList<Move> gameMoves, Date datePlayed, String gameName, boolean draw, boolean resign) {
        this.gameMoves = gameMoves;
        this.datePlayed = datePlayed;
        this.gameName = gameName;
        this.draw=draw;
        this.resign = resign;
    }

    public boolean isDraw() {
        return draw;
    }

    public boolean isResign() {
        return resign;
    }

    public ArrayList<Move> getGameMoves() {
        return gameMoves;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public String getGameName() {
        return gameName;
    }

    public String toString(){
        return gameName + ", Played on: " + String.valueOf(datePlayed);
    }
}
