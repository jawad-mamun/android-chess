package com.example.androidchess33;

public class King extends Piece{

    /**
     * Creates a new King with the specified color.
     * @param isWhite The color of the King.
     */
    public King(boolean isWhite) {
        super(isWhite);
    }
    /**
     * Provides String representation of the King piece which will be displayed on the terminal.
     * @return String "wK" or "bK", depending on whether the King is a white piece or black piece.
     */
    @Override
    public String toString() {
        if(isWhite) {
            return "wK";
        }
        else {
            return "bK";
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

        // castling check
        if(colMove==2 && startRow==endRow && firstMove && !board.isInCheck) {
            // castling to the right
            if(endCol>startCol && board.board[startRow][7]!=null && board.board[startRow][7] instanceof Rook && board.board[startRow][7].firstMove
                    && board.board[startRow][6]==null && board.board[startRow][5]==null) {

                // ensuring castling doesn't put king in danger
                Piece startPiece1 = board.board[startRow][startCol];
                Piece endPiece1 = board.board[startRow][startCol+2];
                board.board[startRow][startCol+2] = startPiece1;
                board.board[startRow][startCol] = null;
                Piece startPiece2 = board.board[startRow][7];
                Piece endPiece2 = board.board[startRow][5];
                board.board[startRow][5] = startPiece2;
                board.board[startRow][7] = null;
                boolean kingInDanger = board.checkCheck(!startPiece1.isWhite);
                board.board[startRow][startCol] = startPiece1;
                board.board[startRow][startCol+2] = endPiece1;
                board.board[startRow][5] = endPiece2;
                board.board[startRow][7] = startPiece2;
                if(kingInDanger==true) return false;

                firstMove = false;
                return true;
            }
            // castling to the left
            if(endCol<startCol && board.board[startRow][0]!=null && board.board[startRow][0] instanceof Rook && board.board[startRow][0].firstMove
                    && board.board[startRow][1]==null && board.board[startRow][2]==null && board.board[startRow][3]==null) {

                // ensuring castling doesn't put king in danger
                Piece startPiece1 = board.board[startRow][startCol];
                Piece endPiece1 = board.board[startRow][startCol-2];
                board.board[startRow][startCol-2] = startPiece1;
                board.board[startRow][startCol] = null;
                Piece startPiece2 = board.board[startRow][0];
                Piece endPiece2 = board.board[startRow][3];
                board.board[startRow][3] = startPiece2;
                board.board[startRow][0] = null;
                boolean kingInDanger = board.checkCheck(!startPiece1.isWhite);
                board.board[startRow][startCol] = startPiece1;
                board.board[startRow][startCol-2] = endPiece1;
                board.board[startRow][3] = endPiece2;
                board.board[startRow][0] = startPiece2;
                if(kingInDanger==true) return false;

                firstMove = false;
                return true;
            }
        }

        // checks if more than one space is being moved, or if the king isn't being moved at all
        if(rowMove>1 || colMove>1 || (rowMove==0 && colMove==0)) return false;

        firstMove = false;
        return true;
    }



}

