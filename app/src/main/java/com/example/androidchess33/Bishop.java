package com.example.androidchess33;

import java.lang.Math;

/**
 * Represents the Bishop pieces on the chess board, extends Piece abstract superclass.
 *
 * @author Jawad Mamun
 * @author Arnav Reddy
 * @version 1.0
 * @since 2021-03-24
 */
public class Bishop extends Piece{

    /**
     * Creates a new Bishop with the specified color.
     * @param isWhite The color of the Bishop.
     */
    public Bishop(boolean isWhite) {
        super(isWhite);
    }
    /**
     * Provides String representation of the Bishop piece which will be displayed on the terminal.
     * @return String "wB" or "bB", depending on whether the Bishop is a white piece or black piece.
     */
    @Override
    public String toString() {
        if(isWhite) {
            return "wB";
        }
        else {
            return "bB";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveLegal(int startRow, int startCol, int endRow, int endCol, boolean whiteTurn, Board board) {
        //Bishop pieces can only move on diagonals

        // check to make sure the piece isn't trying to replace a piece on its own "team"
        if(board.board[endRow][endCol]!=null && board.board[startRow][startCol].isWhite == board.board[endRow][endCol].isWhite) return false;

        int rowMove = Math.abs(endRow-startRow);
        int colMove = Math.abs(endCol-startCol);
        if(rowMove!=colMove) { // not diagonal
            return false;
        }
        // for loops check to make sure the spaces in between the start and end spots are clear
        else if(endRow>startRow && endCol>startCol) {//down right
            for(int i = 1; i<rowMove; i++) {
                if(board.board[startRow+i][startCol+i]!=null) {
                    return false;
                }
            }

        }
        else if(endRow>startRow && endCol<startCol) {//down left
            for(int i = 1; i<rowMove; i++) {
                if(board.board[startRow+i][startCol-i]!=null) {
                    return false;
                }
            }
        }
        else if(endRow<startRow && endCol>startCol) {//up right
            for(int i = 1; i<rowMove; i++) {
                if(board.board[startRow-i][startCol+i]!=null) {
                    return false;
                }
            }
        }
        else if(endRow<startRow && endCol<startCol) {//up left
            for(int i = 1; i<rowMove; i++) {
                if(board.board[startRow-i][startCol-i]!=null) {
                    return false;
                }
            }
        }
        return true;

    }

}

