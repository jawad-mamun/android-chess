package com.example.androidchess33;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ReplayGame extends AppCompatActivity implements Serializable {

    public static ArrayList<SavedGame> userSavedGames = new ArrayList<SavedGame>();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    public HashMap<Integer, int[]> findSquares = new HashMap<Integer, int[]>();
    // holds details of User's first click: first element is row num, second element is col num,
    // this element is ID of the square that was clicked
    private ArrayList<Integer> firstClick = new ArrayList<Integer>();
    // stores all the moves in this game
    private ArrayList<Move> gameMoves = new ArrayList<Move>();
    // maps the toString representation of a Piece to its drawable
    private HashMap<String, Integer> pieceToDrawable = new HashMap<String, Integer>();
    private boolean gameOn = true;
    private boolean whiteTurn = true;
    private boolean printBoard = true;
    private boolean enPassantPossible = false;
    Board board = new Board();

    Button nextButton;
    int clickCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replay_game);
        //Toast.makeText(ReplayGame.this, "In ReplayGame Activity", Toast.LENGTH_LONG).show();
        SavedGame savedGame = (SavedGame) getIntent().getSerializableExtra("clickedSavedGame");
        Toast.makeText(ReplayGame.this, ""+savedGame.toString(), Toast.LENGTH_LONG).show();
        ArrayList<Move> moveList = savedGame.getGameMoves();
        boolean draw = savedGame.isDraw();
        boolean resign = savedGame.isResign();
        nextButton = (Button)findViewById(R.id.nextButton);


        // fills in the pieceToDrawable HashMap to allow for lookup
        pieceToDrawable.put("wp", R.drawable.whitepawn);
        pieceToDrawable.put("wR", R.drawable.whiterook);
        pieceToDrawable.put("wN", R.drawable.whiteknight);
        pieceToDrawable.put("wB", R.drawable.whitebishop);
        pieceToDrawable.put("wQ", R.drawable.whitequeen);
        pieceToDrawable.put("wK", R.drawable.whiteking);
        pieceToDrawable.put("bp", R.drawable.blackpawn);
        pieceToDrawable.put("bR", R.drawable.blackrook);
        pieceToDrawable.put("bN", R.drawable.blackknight);
        pieceToDrawable.put("bB", R.drawable.blackbishop);
        pieceToDrawable.put("bQ", R.drawable.blackqueen);
        pieceToDrawable.put("bK", R.drawable.blackking);


        // first row inputted to HashMap
        findSquares.put(R.id.A8, new int[]{0,0});
        findSquares.put(R.id.B8, new int[]{0,1});
        findSquares.put(R.id.C8, new int[]{0,2});
        findSquares.put(R.id.D8, new int[]{0,3});
        findSquares.put(R.id.E8, new int[]{0,4});
        findSquares.put(R.id.F8, new int[]{0,5});
        findSquares.put(R.id.G8, new int[]{0,6});
        findSquares.put(R.id.H8, new int[]{0,7});

        // second row inputted to HashMap
        findSquares.put(R.id.A7, new int[]{1,0});
        findSquares.put(R.id.B7, new int[]{1,1});
        findSquares.put(R.id.C7, new int[]{1,2});
        findSquares.put(R.id.D7, new int[]{1,3});
        findSquares.put(R.id.E7, new int[]{1,4});
        findSquares.put(R.id.F7, new int[]{1,5});
        findSquares.put(R.id.G7, new int[]{1,6});
        findSquares.put(R.id.H7, new int[]{1,7});

        // third row inputted to HashMap
        findSquares.put(R.id.A6, new int[]{2,0});
        findSquares.put(R.id.B6, new int[]{2,1});
        findSquares.put(R.id.C6, new int[]{2,2});
        findSquares.put(R.id.D6, new int[]{2,3});
        findSquares.put(R.id.E6, new int[]{2,4});
        findSquares.put(R.id.F6, new int[]{2,5});
        findSquares.put(R.id.G6, new int[]{2,6});
        findSquares.put(R.id.H6, new int[]{2,7});

        // fourth row inputted to HashMap
        findSquares.put(R.id.A5, new int[]{3,0});
        findSquares.put(R.id.B5, new int[]{3,1});
        findSquares.put(R.id.C5, new int[]{3,2});
        findSquares.put(R.id.D5, new int[]{3,3});
        findSquares.put(R.id.E5, new int[]{3,4});
        findSquares.put(R.id.F5, new int[]{3,5});
        findSquares.put(R.id.G5, new int[]{3,6});
        findSquares.put(R.id.H5, new int[]{3,7});

        // fifth row inputted to HashMap
        findSquares.put(R.id.A4, new int[]{4,0});
        findSquares.put(R.id.B4, new int[]{4,1});
        findSquares.put(R.id.C4, new int[]{4,2});
        findSquares.put(R.id.D4, new int[]{4,3});
        findSquares.put(R.id.E4, new int[]{4,4});
        findSquares.put(R.id.F4, new int[]{4,5});
        findSquares.put(R.id.G4, new int[]{4,6});
        findSquares.put(R.id.H4, new int[]{4,7});

        // sixth row inputted to HashMap
        findSquares.put(R.id.A3, new int[]{5,0});
        findSquares.put(R.id.B3, new int[]{5,1});
        findSquares.put(R.id.C3, new int[]{5,2});
        findSquares.put(R.id.D3, new int[]{5,3});
        findSquares.put(R.id.E3, new int[]{5,4});
        findSquares.put(R.id.F3, new int[]{5,5});
        findSquares.put(R.id.G3, new int[]{5,6});
        findSquares.put(R.id.H3, new int[]{5,7});

        // seventh row inputted to HashMap
        findSquares.put(R.id.A2, new int[]{6,0});
        findSquares.put(R.id.B2, new int[]{6,1});
        findSquares.put(R.id.C2, new int[]{6,2});
        findSquares.put(R.id.D2, new int[]{6,3});
        findSquares.put(R.id.E2, new int[]{6,4});
        findSquares.put(R.id.F2, new int[]{6,5});
        findSquares.put(R.id.G2, new int[]{6,6});
        findSquares.put(R.id.H2, new int[]{6,7});

        // eighth row inputted to HashMap
        findSquares.put(R.id.A1, new int[]{7,0});
        findSquares.put(R.id.B1, new int[]{7,1});
        findSquares.put(R.id.C1, new int[]{7,2});
        findSquares.put(R.id.D1, new int[]{7,3});
        findSquares.put(R.id.E1, new int[]{7,4});
        findSquares.put(R.id.F1, new int[]{7,5});
        findSquares.put(R.id.G1, new int[]{7,6});
        findSquares.put(R.id.H1, new int[]{7,7});

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MoveMethod", "We in");
                if(clickCount<moveList.size()) {

                    makeMove(moveList.get(clickCount));
                    clickCount++;
                }
                if(clickCount==moveList.size()){
                    if(draw){
                        Log.d("Draw", "True");
                        Toast.makeText(ReplayGame.this, "The game ends in a draw.", Toast.LENGTH_LONG).show();
                    }
                    if(resign){
                        if(!moveList.get(moveList.size()-1).isWhiteTurn()) {
                            Toast.makeText(ReplayGame.this, "White resigns, black wins!", Toast.LENGTH_LONG).show();
                        }
                        else if(moveList.get(moveList.size()-1).isWhiteTurn()) {
                            Toast.makeText(ReplayGame.this, "Black resigns, white wins!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });



    }
    public void makeMove(Move move){

            //Log.e("MoveMethod", "We in makeMove");


            int startRow = move.getFirstClickRow();
            int startCol = move.getFirstClickColumn();
            int endCol = move.getSecondClickColumn();
            int endRow = move.getSecondClickRow();
            boolean whiteTurn = move.isWhiteTurn();
            boolean enPassantPossible = move.isEnPassantPossible();
            boolean enPassantMove = move.isEnPassantMove();
            boolean castlingMove = move.isCastlingMove();
            boolean firstMoveChanged = move.isFirstMoveChanged();
            int id = move.getSecondClickID();
            int firstClickID = move.getFirstClickID();

            ArrayList<Piece> capturedPiece = new ArrayList<Piece>();

            // stores Pieces's current firstMove value
            boolean firstMove = false;
            if(board.board[startRow][startCol]!=null){
                firstMove = board.board[startRow][startCol].firstMove;
            }

            if(board.move(startRow, startCol, endRow, endCol, whiteTurn, capturedPiece)) {
                if(whiteTurn)
                    whiteTurn=false;
                else
                    whiteTurn = true;
                if((startRow == endRow+2 || startRow == endRow-2) && startCol==endCol && board.board[endRow][endCol] instanceof Pawn) {
                    enPassantPossible = true;
                }
                else {
                    enPassantPossible = false;
                }

                // simulates promotion
               if(board.board[endRow][endCol] instanceof Pawn && (endRow==0 || endRow==7)){


                       board.board[endRow][endCol] = move.getPromotedTo();

                       Piece pieceCaptured = null;
                       if(capturedPiece.size()>0) pieceCaptured = capturedPiece.get(0);

                       updateUserView(startRow, startCol, firstClickID, endRow, endCol, id);

                       return;
                }



                Piece pieceCaptured = null;
                if(capturedPiece.size()>0) pieceCaptured = capturedPiece.get(0);

                castlingMove = false;
                if(Math.abs(startCol-endCol)==2 && board.board[endRow][endCol] instanceof King){
                    castlingMove = true;
                }

                // records whether a piece's first move was changed
                firstMoveChanged = firstMove != board.board[endRow][endCol].firstMove;

                //add this move to the list of moves (this currently assumes no pawn promotion)
                /*gameMoves.add(new Move(startRow, startCol, firstClick.get(2), endRow, endCol, id,
                        !whiteTurn, enPassantPossible, false, castlingMove,
                        pieceCaptured, false, firstMoveChanged));*/

                updateUserView(startRow, startCol, firstClickID, endRow, endCol, id);
            }

            else if(enPassantPossible && board.enPassantValid(startRow, startCol, endRow, endCol, whiteTurn)) {
                if(whiteTurn)
                    whiteTurn=false;
                else {
                    whiteTurn = true;
                }
                enPassantPossible=false;

                updateUserView(startRow, startCol, firstClickID, endRow, endCol, id);

                // add this move to the list of moves
                gameMoves.add(new Move(startRow, startCol, firstClick.get(2), endRow, endCol, id,
                        !whiteTurn, enPassantPossible, true, false,
                        null, false, false));

//                 handle the visuals of the pawn piece that was captured
//                 handle en passants along row 6 (on the chessboard)
                if(endRow==2 && endCol==0){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.A5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==1){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.B5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==2){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.C5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==3){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.D5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==4){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.E5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==5){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.F5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==6){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.G5);
                    capturedPawn.setImageResource(0);
                } else if(endRow==2 && endCol==7){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.H5);
                    capturedPawn.setImageResource(0);
                }
                // handle en passants along row 3 (on the chessboard)
                if(endRow==5 && endCol==0){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.A4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==1){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.B4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==2){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.C4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==3){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.D4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==4){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.E4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==5){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.F4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==6){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.G4);
                    capturedPawn.setImageResource(0);
                } else if(endRow==5 && endCol==7){
                    ImageButton capturedPawn = (ImageButton) findViewById(R.id.H4);
                    capturedPawn.setImageResource(0);
                }

            }
            else {

                Toast.makeText(ReplayGame.this, "Illegal Move", Toast.LENGTH_LONG).show();
            }
            firstClick.clear();


    }

    public void updateUserView(int startRow, int startCol, int firstClickId, int endRow, int endCol, int id){

        ImageButton firstPiece = (ImageButton)findViewById(firstClickId);
        firstPiece.setImageResource(0);


        // handle castling first
        if(board.board[endRow][endCol] instanceof King && Math.abs(startCol-endCol)==2){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            // castling black E8 --> C8
            if(endCol==2 && endRow==0){
                secondPiece.setImageResource(R.drawable.blackking);
                ImageButton movingRook = (ImageButton)findViewById(R.id.A8);
                movingRook.setImageResource(0);
                ImageButton movingRookDestination = (ImageButton)findViewById(R.id.D8);
                movingRookDestination.setImageResource(R.drawable.blackrook);
            }
            // castling black E8 --> G8
            if(endCol==6 && endRow==0){
                secondPiece.setImageResource(R.drawable.blackking);
                ImageButton movingRook = (ImageButton)findViewById(R.id.H8);
                movingRook.setImageResource(0);
                ImageButton movingRookDestination = (ImageButton)findViewById(R.id.F8);
                movingRookDestination.setImageResource(R.drawable.blackrook);
            }
            // castling white E1 --> G1
            if(endCol==6 && endRow==7){
                secondPiece.setImageResource(R.drawable.whiteking);
                ImageButton movingRook = (ImageButton)findViewById(R.id.H1);
                movingRook.setImageResource(0);
                ImageButton movingRookDestination = (ImageButton)findViewById(R.id.F1);
                movingRookDestination.setImageResource(R.drawable.whiterook);
            }
            // castling white E1 --> C1
            if(endCol==2 && endRow==7){
                secondPiece.setImageResource(R.drawable.whiteking);
                ImageButton movingRook = (ImageButton)findViewById(R.id.A1);
                movingRook.setImageResource(0);
                ImageButton movingRookDestination = (ImageButton)findViewById(R.id.D1);
                movingRookDestination.setImageResource(R.drawable.whiterook);
            }
            return;
        }

        // updates virtual board's appearance using pieceToKey hashmap
        ImageButton secondPiece = (ImageButton)findViewById(id);
        secondPiece.setImageResource(pieceToDrawable.get(board.board[endRow][endCol].toString()));

        if (board.isInCheckMate) {
            if (!whiteTurn) {
                Toast.makeText(ReplayGame.this,"CheckMate, White Wins", Toast.LENGTH_LONG).show();
            } else {
                //System.out.println("Black wins");
                Toast.makeText(ReplayGame.this,"CheckMate, Black Wins", Toast.LENGTH_LONG).show();
            }
            //offerSaveGameDialog(PlayChessGame.this);
        }
    }



}


