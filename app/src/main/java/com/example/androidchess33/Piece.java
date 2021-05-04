package com.example.androidchess33;

import java.io.Serializable;

public abstract class Piece implements Serializable {
    /** Represents the color of the piece (white or not white, i.e. black).
     */
    public boolean isWhite;
    /** Indicates whether the piece has moved yet- important for castling, en passant, pawn movement.
     */
    public boolean firstMove = true;
    /**
     * Creates a new Piece with the specified color.
     * @param isWhite The color of the piece.
     */
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * This method is used to check that an attempted move on the chess piece is valid.
     *
     * @param startRow This is the row number (0-7 on the board 2D-array) of the moving piece.
     * @param startCol This is the column number (0-7 on the board 2D-array) of the moving piece.
     * @param endRow This is the row number (0-7 on the board 2D-array) of the desired location to move to.
     * @param endCol This is the column number (0-7 on the board 2D-array) of the desired location to move to.
     * @param whiteTurn This indicates whether the piece being moved is white, meaning it is white's turn; or black, meaning it is not white turn.
     * @param board This passes in the Board object on which the chess game is being played, and where all the piece objects are currently contained.
     * @return boolean Value indicating whether the move is valid.
     */
    public abstract boolean isMoveLegal(int startRow, int startCol, int endRow, int endCol,boolean whiteTurn, Board board);
}
