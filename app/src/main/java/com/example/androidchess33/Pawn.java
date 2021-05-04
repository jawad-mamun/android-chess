package com.example.androidchess33;

public class Pawn extends Piece {

    /**
     * Creates a new Pawn with the specified color.
     * @param isWhite The color of the Pawn.
     */
    public Pawn(boolean isWhite) {
        super(isWhite);
    }
    /**
     * Provides String representation of the Pawn piece which will be displayed on the terminal.
     * @return String "wP" or "bP", depending on whether the Pawn is a white piece or black piece.
     */
    @Override
    public String toString() {
        if(isWhite) {
            return "wp";
        }
        else {
            return "bp";
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMoveLegal(int startRow, int startCol, int endRow, int endCol,boolean whiteTurn, Board board) {
        // first move of a pawn can be 2 spaces, all other moves are 1 space
        // pawn can attack diagonally

        // check to make sure the piece isn't trying to replace a piece on its own "team"
        if(board.board[endRow][endCol]!=null && board.board[startRow][startCol].isWhite == board.board[endRow][endCol].isWhite) return false;

        Piece endPiece = board.board[endRow][endCol];

        if(isWhite && whiteTurn) {//white piece
            if(firstMove) {//can move 1 or 2 spaces
                if(endCol==startCol && endPiece==null) {
                    if(endRow>=startRow) {
                        return false;
                    }
                    else if(endRow<startRow-2) {
                        return false;
                    }
                    else {
                        firstMove=false;
                        return true;
                    }
                }
                else if ((endCol==startCol+1 || endCol==startCol-1) && endPiece!=null) {//attack if conditions are met
                    if(endRow>=startRow) {
                        return false;
                    }
                    else if(endRow<startRow-1) {
                        return false;
                    }
                    else {
                        firstMove = false;
                        return true;
                    }
                }
                else {
                    return false;
                }

            }

            else {//not first move(can only move one space)
                if(endCol==startCol && endPiece==null) {
                    if(endRow>=startRow)
                        return false;
                    else if(endRow<startRow-1)
                        return false;
                    else
                        return true;
                }
                else if ((endCol==startCol+1 || endCol==startCol-1) && endPiece!=null) {//attack if conditions are met
                    if(endRow>=startRow)
                        return false;
                    else if(endRow<startRow-1)
                        return false;
                    else {
                        firstMove = false;
                        return true;
                    }
                }
                else {
                    return false;
                }
            }
        }

        else if(!isWhite && !whiteTurn){//black piece
            if(firstMove) {//can move 1 or 2 spaces
                if(endCol==startCol && endPiece==null) {
                    if(endRow<=startRow)
                        return false;
                    else if(endRow>startRow+2)
                        return false;
                    else {
                        firstMove=false;
                        return true;
                    }
                }
                else if ((endCol==startCol+1 || endCol==startCol-1) && endPiece!=null) {//attack if conditions are met
                    if(endRow<=startRow)
                        return false;
                    else if(endRow>startRow+1)
                        return false;
                    else {
                        firstMove = false;
                        return true;
                    }
                }
                else {
                    return false;
                }
            }

            else {//not first move(can only move one space
                if(endCol==startCol && endPiece==null) {
                    if(endRow<=startRow)
                        return false;
                    else if(endRow>startRow+1)
                        return false;
                    else
                        return true;
                }
                else if ((endCol==startCol+1 || endCol==startCol-1) && endPiece!=null) {//attack if conditions are met
                    if(endRow<=startRow)
                        return false;
                    else if(endRow>startRow+1)
                        return false;
                    else {
                        return true;
                    }
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }

}

