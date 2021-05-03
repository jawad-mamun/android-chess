package com.example.androidchess33;

import java.util.ArrayList;

public class Board {

    /**
     * Represents the board with all the chess pieces inside of it.
     */
    Piece[][] board;
    /**
     * Represents an empty board that indicates which squares are white (empty) and black (hashtag).
     */
    boolean [][] isHashtag;
    /**
     * Represents whether either black or white is currently in check.
     */
    boolean isInCheck;
    /**
     * Represents whether there is a Checkmate in the game.
     */
    boolean isInCheckMate;

    /**
     * Initializes and colors the board, marking the appropriate squares as white (empty) or black (hashtag).
     */
    public Board() {
        board = new Piece[8][8];
        setBoard();

        //array where hashtags are
        isHashtag = new boolean[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(i%2==0){
                    if(j%2==0) {
                        isHashtag[i][j] = false;
                    }
                    else {
                        isHashtag[i][j] = true;
                    }
                }
                else {
                    if(j%2 == 0) {
                        isHashtag[i][j] = true;
                    }
                    else {
                        isHashtag[i][j] = false;
                    }
                }
            }
        }
    }
    /**
     * Places all the starting chess pieces in their correct positions on the board to start.
     */
    public void setBoard() {

        for(int i = 0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                board[i][j] = null;
            }
        }

        //White Pieces
        board[7][0] = new Rook(true);
        board[7][1] = new Knight(true);
        board[7][2] = new Bishop(true);
        board[7][3] = new Queen(true);
        board[7][4] = new King(true);
        board[7][5] = new Bishop(true);
        board[7][6] = new Knight(true);
        board[7][7] = new Rook(true);

        for(int i = 0; i<board.length; i++) {
            board[6][i] = new Pawn(true);
        }

        //Black Pieces
        board[0][0] = new Rook(false);
        board[0][1] = new Knight(false);
        board[0][2] = new Bishop(false);
        board[0][3] = new Queen(false);
        board[0][4] = new King(false);
        board[0][5] = new Bishop(false);
        board[0][6] = new Knight(false);
        board[0][7] = new Rook(false);

        for(int i = 0; i<board.length; i++) {
            board[1][i] = new Pawn(false);
        }

    }
    /**
     * Prints the current status of the chess board to the terminal.
     */

    /**
     * Determines whether the completed move by either white or black puts the other team in check.
     * @param whiteTurn Indicates whether it was white's turn (whiteTurn==true) or black's turn (whiteTurn==false)
     * @return boolean Returns true if the other team is in check, otherwise false
     */
    public boolean checkCheck(boolean whiteTurn) {
        boolean piecesFirstMoveValue = true;
        int rowPosKing = 0;
        int colPosKing = 0;
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                if(board[i][j] instanceof King && !board[i][j].isWhite==whiteTurn) {
                    rowPosKing = i;
                    colPosKing = j;
                }
            }
        }
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                if (board[i][j]!=null) piecesFirstMoveValue = board[i][j].firstMove;
                if(board[i][j]!=null && board[i][j].isWhite == whiteTurn && board[i][j].isMoveLegal(i, j, rowPosKing, colPosKing, whiteTurn, this)) {
                    board[i][j].firstMove = piecesFirstMoveValue;
                    return true;
                }
                if (board[i][j]!=null) board[i][j].firstMove = piecesFirstMoveValue;
            }
        }
        return false;
    }
    /**
     * Determines whether the completed move by either white or black puts the other team in checkmate.
     * @param whiteTurn Indicates whether it was white's turn (whiteTurn==true) or black's turn (whiteTurn==false)
     * @return boolean Returns true if the other team is in checkmate, otherwise false
     */
    public boolean checkCheckMate(boolean whiteTurn) {
        // finds !whiteTurn pieces and moves those !whiteTurn pieces to all possible positions to see if check is avoidable
        boolean piecesFirstMoveValue = true;
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                if(board[i][j]!=null && !board[i][j].isWhite == whiteTurn) {
                    for(int r = 0; r<board.length; r++) {
                        for(int k = 0; k<board[r].length; k++) {
                            if (board[i][j]!=null) piecesFirstMoveValue = board[i][j].firstMove;
                            if(board[i][j].isMoveLegal(i, j, r, k, board[i][j].isWhite, this)) {
                                board[i][j].firstMove = piecesFirstMoveValue;
                                Piece startPiece = board[i][j];
                                Piece endPiece = board[r][k];
                                board[r][k] = startPiece;
                                board[i][j] = null;
                                boolean checkMate = checkCheck(whiteTurn);
                                board[i][j] = startPiece;
                                board[r][k] = endPiece;
                                if(checkMate==false) {
                                    return false;
                                }
                            }
                            if (board[i][j]!=null) board[i][j].firstMove = piecesFirstMoveValue;
                        }
                    }
                }
            }
        }
        return true;
    }
    /**
     * Checks if moving a piece a certain way will put that piece's King in danger.
     * @param startX The row number of the piece that will be moved.
     * @param startY The column number of the piece that will be moved.
     * @param endX The row number of the position where the piece will move.
     * @param endY The column number of the position where the piece will move.
     * @return boolean Returns true if moving the piece will endanger its own king, otherwise false.
     */
    public boolean puttingKingInDanger(int startX, int startY, int endX, int endY) {
        Piece startPiece = board[startX][startY];
        Piece endPiece = board[endX][endY];
        board[endX][endY] = startPiece;
        board[startX][startY] = null;
        boolean kingInDanger = checkCheck(!startPiece.isWhite);
        board[startX][startY] = startPiece;
        board[endX][endY] = endPiece;
        return kingInDanger;
    }
    /**
     * Moves a piece from one position to another on the chess board. Will perform checks to ensure
     * the desired move is valid.
     * @param startX The row number of the piece that will be moved.
     * @param startY The column number of the piece that will be moved.
     * @param endX The row number of the position where the piece will move.
     * @param endY The column number of the position where the piece will move.
     * @param whiteTurn The color of the piece that will be moved, whiteTurn==true indicates a white piece, whiteTurn==false indicates a black piece.
     * @return boolean Returns true if the move is valid and the piece is successfully moved, otherwise false.
     */
    public boolean move(int startX, int startY, int endX, int endY, boolean whiteTurn, ArrayList<Piece> capturedPiece) {
        if(board[startX][startY]==null) return false;
        Piece piece = board[startX][startY];
        if(whiteTurn) {
            if(!board[startX][startY].isWhite) {
                return false;
            }
        }
        else {
            if(board[startX][startY].isWhite) {
                return false;
            }
        }

        // specific castling example
        if(piece instanceof King && Math.abs(endY-startY)==2 && piece.isMoveLegal(startX, startY, endX, endY, whiteTurn, this)) {
            // left castling
            if(endY<startY) {
                board[startX][startY-2] = board[startX][startY];
                board[startX][startY] = null;
                board[startX][3] = board[startX][0];
                board[startX][0] = null;
            }
            // right castling
            else {
                board[startX][startY+2] = board[startX][startY];
                board[startX][startY] = null;
                board[startX][5] = board[startX][7];
                board[startX][7] = null;
            }
            isInCheck = checkCheck(whiteTurn);
            isInCheckMate = checkCheckMate(whiteTurn);
            return true;
        }

        boolean pieceFirstMoveValue;

        //if end location has a piece check if it is of the same color. if it is same, then false
        if(board[startX][startY]!=null && board[endX][endY]!=null) {
            if( (board[startX][startY].isWhite && !board[endX][endY].isWhite) || (!board[startX][startY].isWhite && board[endX][endY].isWhite) ) {
                pieceFirstMoveValue = piece.firstMove;
                if(piece.isMoveLegal(startX, startY,  endX,  endY, whiteTurn, this)) {
                    if(puttingKingInDanger(startX, startY, endX, endY)) {
                        piece.firstMove = pieceFirstMoveValue;
                        return false;
                    }
                    capturedPiece.add(board[endX][endY]);
                    board[endX][endY] = board[startX][startY];
                    board[startX][startY] = null;
                    isInCheck = checkCheck(whiteTurn);
                    isInCheckMate = checkCheckMate(whiteTurn);
                    return true;
                }
            }
            else {//end location is the same color
                return false;
            }
        }
        else if(board[startX][startY]!=null) {
            pieceFirstMoveValue = piece.firstMove;
            if(piece.isMoveLegal(startX, startY,  endX,  endY, whiteTurn, this)) {
                if(puttingKingInDanger(startX, startY, endX, endY)) {
                    piece.firstMove = pieceFirstMoveValue;
                    return false;
                }
                board[endX][endY] = board[startX][startY];
                board[startX][startY] = null;
                isInCheck = checkCheck(whiteTurn);
                isInCheckMate = checkCheckMate(whiteTurn);
                return true;
            }
        }
        return false;
    }
    /**
     * Handles the special case of an enPassant. Moves the pawn according to enPassant rules, checking if enPassant is valid.
     * @param startRow The row number of the piece that will be moved.
     * @param startCol The column number of the piece that will be moved.
     * @param endRow The row number of the position where the piece will move.
     * @param endCol The column number of the position where the piece will move.
     * @param whiteTurn The color of the piece that will be moved, whiteTurn==true indicates a white piece, whiteTurn==false indicates a black piece.
     * @return boolean Returns true if the move is valid and the piece is successfully moved, otherwise false.
     */
    public boolean enPassantValid(int startRow, int startCol, int endRow, int endCol, boolean whiteTurn) {
        if(whiteTurn) {
            if(startRow == 3 && endRow == 2 && board[startRow][startCol] instanceof Pawn && (startCol==endCol-1 || startCol==endCol+1)) {
                if(endCol>startCol) {//check right
                    if(!(board[startRow][startCol+1] instanceof Pawn))
                        return false;
                    else {
                        if(puttingKingInDanger(startRow, startCol, endRow, endCol)) return false;
                        board[endRow][endCol] = board[startRow][startCol];
                        board[startRow][startCol] = null;
                        board[startRow][startCol+1] = null;
                        isInCheck = checkCheck(whiteTurn);
                        isInCheckMate = checkCheckMate(whiteTurn);
                        return true;
                    }
                }
                else if(endCol<startCol ) {//check left
                    if(!(board[startRow][startCol-1] instanceof Pawn))
                        return false;
                    else {
                        if(puttingKingInDanger(startRow, startCol, endRow, endCol)) return false;
                        board[endRow][endCol] = board[startRow][startCol];
                        board[startRow][startCol] = null;
                        board[startRow][startCol-1] = null;
                        isInCheck = checkCheck(whiteTurn);
                        isInCheckMate = checkCheckMate(whiteTurn);
                        return true;
                    }
                }

            }
        }//end white turn

        else {//black turn
            if(startRow == 4 && endRow == 5 && board[startRow][startCol] instanceof Pawn && (startCol==endCol-1 || startCol==endCol+1)) {
                if(endCol>startCol) {//check right
                    if(!(board[startRow][startCol+1] instanceof Pawn))
                        return false;
                    else {
                        if(puttingKingInDanger(startRow, startCol, endRow, endCol)) return false;
                        board[endRow][endCol] = board[startRow][startCol];
                        board[startRow][startCol] = null;
                        board[startRow][startCol+1] = null;
                        isInCheck = checkCheck(whiteTurn);
                        isInCheckMate = checkCheckMate(whiteTurn);
                        return true;
                    }
                }
                else if(endCol<startCol ) {//check left
                    if(!(board[startRow][startCol-1] instanceof Pawn))
                        return false;
                    else {
                        if(puttingKingInDanger(startRow, startCol, endRow, endCol)) return false;
                        board[endRow][endCol] = board[startRow][startCol];
                        board[startRow][startCol] = null;
                        board[startRow][startCol-1] = null;
                        isInCheck = checkCheck(whiteTurn);
                        isInCheckMate = checkCheckMate(whiteTurn);
                        return true;
                    }
                }

            }

        }
        return false;
    }

}