package com.example.androidchess33;

public class Knight extends Piece{

    /**
     * Creates a new Knight with the specified color.
     * @param isWhite The color of the Knight.
     */
    public Knight(boolean isWhite) {
        super(isWhite);
    }
    /**
     * Provides String representation of the Knight piece which will be displayed on the terminal.
     * @return String "wN" or "bN", depending on whether the Knight is a white piece or black piece.
     */
    @Override
    public String toString() {
        if(isWhite) {
            return "wN";
        }
        else {
            return "bN";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveLegal(int startRow, int startCol, int endRow, int endCol, boolean whiteTurn, Board board) {
        // check to make sure the piece isn't trying to replace a piece on its own "team"
        if(board.board[endRow][endCol]!=null && board.board[startRow][startCol].isWhite == board.board[endRow][endCol].isWhite) return false;

        int rowMove = Math.abs(startRow-endRow);
        int colMove = Math.abs(startCol-endCol);

        // ensures Knight's movement is L-shaped
        if(rowMove > 2 || colMove > 2 || rowMove==0 || colMove==0)
            return false;
        else if(rowMove==2 && colMove==2)
            return false;
        else if(rowMove==1 && colMove==1)
            return false;
        return true;
    }


}

