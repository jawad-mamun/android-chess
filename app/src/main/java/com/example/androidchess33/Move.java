package com.example.androidchess33;

public class Move {
    int firstClickRow;
    int firstClickColumn;
    int firstClickID;
    int secondClickRow;
    int secondClickColumn;
    int secondClickID;
    boolean whiteTurn;
    // indicates whether enPassant will be possible AFTER this move
    boolean enPassantPossible;
    boolean enPassantMove;
    boolean castlingMove;
    boolean pawnPromotion;
    boolean firstMoveChanged;

    Piece capturedPiece;

    public Move(int firstClickRow, int firstClickColumn, int firstClickID, int secondClickRow,
                int secondClickColumn, int secondClickID, boolean whiteTurn, boolean enPassantPossible,
                boolean enPassantMove, boolean castlingMove, Piece capturedPiece, boolean pawnPromotion,
                boolean firstMoveChanged) {
        this.firstClickRow = firstClickRow;
        this.firstClickColumn = firstClickColumn;
        this.firstClickID = firstClickID;
        this.secondClickRow = secondClickRow;
        this.secondClickColumn = secondClickColumn;
        this.secondClickID = secondClickID;
        this.whiteTurn = whiteTurn;
        this.enPassantPossible = enPassantPossible;
        this.enPassantMove = enPassantMove;
        this.castlingMove = castlingMove;
        this.capturedPiece = capturedPiece;
        this.pawnPromotion = pawnPromotion;
        this.firstMoveChanged = firstMoveChanged;
    }

    public boolean isFirstMoveChanged() {
        return firstMoveChanged;
    }

    public boolean isPawnPromotion(){
        return pawnPromotion;
    }

    public Piece getCapturedPiece(){
        return capturedPiece;
    }

    public int getFirstClickRow() {
        return firstClickRow;
    }

    public int getFirstClickColumn() {
        return firstClickColumn;
    }

    public int getFirstClickID() {
        return firstClickID;
    }

    public int getSecondClickRow() {
        return secondClickRow;
    }

    public int getSecondClickColumn() {
        return secondClickColumn;
    }

    public int getSecondClickID() {
        return secondClickID;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public boolean isEnPassantPossible() {
        return enPassantPossible;
    }

    public boolean isEnPassantMove() {
        return enPassantMove;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public String toString(){
        return firstClickRow + ", " + firstClickColumn + " to " + secondClickRow + ", " + secondClickColumn;
    }
}
