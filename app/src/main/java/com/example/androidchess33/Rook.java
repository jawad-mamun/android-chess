package com.example.androidchess33;

public class Rook extends Piece{

    /**
     * Creates a new Rook with the specified color.
     * @param isWhite The color of the Rook.
     */
    public Rook(boolean isWhite) {
        super(isWhite);
    }
    /**
     * Provides String representation of the Rook piece which will be displayed on the terminal.
     * @return String "wR" or "bR", depending on whether the Rook is a white piece or black piece.
     */
    @Override
    public String toString() {
        if(isWhite) {
            return "wR";
        }
        else {
            return "bR";
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveLegal(int startRow, int startCol, int endRow, int endCol, boolean whiteTurn, Board board) {
        // check to make sure the piece isn't trying to replace a piece on its own "team"
        if(board.board[endRow][endCol]!=null && board.board[startRow][startCol].isWhite == board.board[endRow][endCol].isWhite) return false;

        int rowMove = Math.abs(endRow-startRow);
        int colMove = Math.abs(endCol-startCol);

        if(rowMove>0 && colMove>0) {//diagonal
            return false;
        }
        else if(rowMove==0 && colMove==0) {//no move
            return false;
        }
        else if(endRow>startRow) {//down
            for(int i=1; i<rowMove; i++) {
                if(board.board[startRow+i][startCol]!=null)
                    return false;
            }
        }
        else if(endRow<startRow) {//up
            for(int i=1; i<rowMove; i++) {
                if(board.board[startRow-i][startCol]!=null)
                    return false;
            }
        }
        else if(endCol>startCol) {//right
            for(int i=1; i<colMove; i++) {
                if(board.board[startRow][startCol+i]!=null)
                    return false;
            }
        }
        else if(endCol<startCol) {//left
            for(int i=1; i<colMove; i++) {
                if(board.board[startRow][startCol-i]!=null)
                    return false;
            }
        }

        firstMove = false;
        return true;
    }



}
