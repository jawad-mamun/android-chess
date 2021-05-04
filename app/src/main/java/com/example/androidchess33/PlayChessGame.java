package com.example.androidchess33;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PlayChessGame extends AppCompatActivity implements Serializable {
    // maps the ID of a square on the chessboard to its position in the matrix
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_chess_game_layout);

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

        Button undoButton = (Button) findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                undoMove();
            }
        });

        Button resignButton = (Button) findViewById(R.id.resignButton);
        resignButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(whiteTurn){
                    Toast.makeText(PlayChessGame.this, "White resigns, black wins!", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(PlayChessGame.this, "Black resigns, white wins!", Toast.LENGTH_LONG).show();
                }
                offerSaveGameDialog(PlayChessGame.this);
            }
        });

        Button drawButton = (Button) findViewById(R.id.drawButton);
        drawButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(PlayChessGame.this, "The game ends in a draw.", Toast.LENGTH_LONG).show();
                offerSaveGameDialog(PlayChessGame.this);
            }
        });

        //Column A ImageButtons
        ImageButton a1 = (ImageButton) findViewById(R.id.A1);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a1.getId())[0], findSquares.get(a1.getId())[1], a1.getId());
            }
        });

        ImageButton a2 = (ImageButton) findViewById(R.id.A2);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a2.getId())[0], findSquares.get(a2.getId())[1], a2.getId());

            }
        });

        ImageButton a3 = (ImageButton) findViewById(R.id.A3);
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a3.getId())[0], findSquares.get(a3.getId())[1], a3.getId());
            }
        });
        ImageButton a4 = (ImageButton) findViewById(R.id.A4);
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a4.getId())[0], findSquares.get(a4.getId())[1], a4.getId());
            }
        });
        ImageButton a5 = (ImageButton) findViewById(R.id.A5);
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a5.getId())[0], findSquares.get(a5.getId())[1], a5.getId());
            }
        });
        ImageButton a6 = (ImageButton) findViewById(R.id.A6);
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a6.getId())[0], findSquares.get(a6.getId())[1], a6.getId());
            }
        });
        ImageButton a7 = (ImageButton) findViewById(R.id.A7);
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a7.getId())[0], findSquares.get(a7.getId())[1], a7.getId());
            }
        });
        ImageButton a8 = (ImageButton) findViewById(R.id.A8);
        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(a8.getId())[0], findSquares.get(a8.getId())[1], a8.getId());
            }
        });

        //Column B ImageButtons
        ImageButton b1 = (ImageButton) findViewById(R.id.B1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b1.getId())[0], findSquares.get(b1.getId())[1], b1.getId());
            }
        });

        ImageButton b2 = (ImageButton) findViewById(R.id.B2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b2.getId())[0], findSquares.get(b2.getId())[1], b2.getId());
            }
        });

        ImageButton b3 = (ImageButton) findViewById(R.id.B3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b3.getId())[0], findSquares.get(b3.getId())[1], b3.getId());
            }
        });
        ImageButton b4 = (ImageButton) findViewById(R.id.B4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b4.getId())[0], findSquares.get(b4.getId())[1], b4.getId());
            }
        });
        ImageButton b5 = (ImageButton) findViewById(R.id.B5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b5.getId())[0], findSquares.get(b5.getId())[1], b5.getId());
            }
        });
        ImageButton b6 = (ImageButton) findViewById(R.id.B6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b6.getId())[0], findSquares.get(b6.getId())[1], b6.getId());
            }
        });
        ImageButton b7 = (ImageButton) findViewById(R.id.B7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b7.getId())[0], findSquares.get(b7.getId())[1], b7.getId());
            }
        });
        ImageButton b8 = (ImageButton) findViewById(R.id.B8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(b8.getId())[0], findSquares.get(b8.getId())[1], b8.getId());
            }
        });

        //Column C ImageButtons
        ImageButton c1 = (ImageButton) findViewById(R.id.C1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c1.getId())[0], findSquares.get(c1.getId())[1], c1.getId());
            }
        });

        ImageButton c2 = (ImageButton) findViewById(R.id.C2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c2.getId())[0], findSquares.get(c2.getId())[1], c2.getId());
            }
        });

        ImageButton c3 = (ImageButton) findViewById(R.id.C3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c3.getId())[0], findSquares.get(c3.getId())[1], c3.getId());
            }
        });
        ImageButton c4 = (ImageButton) findViewById(R.id.C4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c4.getId())[0], findSquares.get(c4.getId())[1], c4.getId());
            }
        });
        ImageButton c5 = (ImageButton) findViewById(R.id.C5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c5.getId())[0], findSquares.get(c5.getId())[1], c5.getId());
            }
        });
        ImageButton c6 = (ImageButton) findViewById(R.id.C6);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c6.getId())[0], findSquares.get(c6.getId())[1], c6.getId());
            }
        });
        ImageButton c7 = (ImageButton) findViewById(R.id.C7);
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c7.getId())[0], findSquares.get(c7.getId())[1], c7.getId());
            }
        });
        ImageButton c8 = (ImageButton) findViewById(R.id.C8);
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(c8.getId())[0], findSquares.get(c8.getId())[1], c8.getId());
            }
        });

        //Column D ImageButtons
        ImageButton d1 = (ImageButton) findViewById(R.id.D1);
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d1.getId())[0], findSquares.get(d1.getId())[1], d1.getId());
            }
        });

        ImageButton d2 = (ImageButton) findViewById(R.id.D2);
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d2.getId())[0], findSquares.get(d2.getId())[1], d2.getId());
            }
        });

        ImageButton d3 = (ImageButton) findViewById(R.id.D3);
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d3.getId())[0], findSquares.get(d3.getId())[1], d3.getId());
            }
        });
        ImageButton d4 = (ImageButton) findViewById(R.id.D4);
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d4.getId())[0], findSquares.get(d4.getId())[1], d4.getId());
            }
        });
        ImageButton d5 = (ImageButton) findViewById(R.id.D5);
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d5.getId())[0], findSquares.get(d5.getId())[1], d5.getId());
            }
        });
        ImageButton d6 = (ImageButton) findViewById(R.id.D6);
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d6.getId())[0], findSquares.get(d6.getId())[1], d6.getId());
            }
        });
        ImageButton d7 = (ImageButton) findViewById(R.id.D7);
        d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d7.getId())[0], findSquares.get(d7.getId())[1], d7.getId());
            }
        });
        ImageButton d8 = (ImageButton) findViewById(R.id.D8);
        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(d8.getId())[0], findSquares.get(d8.getId())[1], d8.getId());
            }
        });

        //Column E

        ImageButton e1 = (ImageButton) findViewById(R.id.E1);
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e1.getId())[0], findSquares.get(e1.getId())[1], e1.getId());
            }
        });

        ImageButton e2 = (ImageButton) findViewById(R.id.E2);
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e2.getId())[0], findSquares.get(e2.getId())[1], e2.getId());
            }
        });

        ImageButton e3 = (ImageButton) findViewById(R.id.E3);
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e3.getId())[0], findSquares.get(e3.getId())[1], e3.getId());
            }
        });
        ImageButton e4 = (ImageButton) findViewById(R.id.E4);
        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e4.getId())[0], findSquares.get(e4.getId())[1], e4.getId());
            }
        });
        ImageButton e5 = (ImageButton) findViewById(R.id.E5);
        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e5.getId())[0], findSquares.get(e5.getId())[1], e5.getId());
            }
        });
        ImageButton e6 = (ImageButton) findViewById(R.id.E6);
        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e6.getId())[0], findSquares.get(e6.getId())[1], e6.getId());
            }
        });
        ImageButton e7 = (ImageButton) findViewById(R.id.E7);
        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e7.getId())[0], findSquares.get(e7.getId())[1], e7.getId());
            }
        });
        ImageButton e8 = (ImageButton) findViewById(R.id.E8);
        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(e8.getId())[0], findSquares.get(e8.getId())[1], e8.getId());
            }
        });
        ImageButton f1 = (ImageButton) findViewById(R.id.F1);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f1.getId())[0], findSquares.get(f1.getId())[1], f1.getId());
            }
        });

        ImageButton f2 = (ImageButton) findViewById(R.id.F2);
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f2.getId())[0], findSquares.get(f2.getId())[1], f2.getId());
            }
        });

        ImageButton f3 = (ImageButton) findViewById(R.id.F3);
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f3.getId())[0], findSquares.get(f3.getId())[1], f3.getId());
            }
        });
        ImageButton f4 = (ImageButton) findViewById(R.id.F4);
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f4.getId())[0], findSquares.get(f4.getId())[1], f4.getId());
            }
        });
        ImageButton f5 = (ImageButton) findViewById(R.id.F5);
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f5.getId())[0], findSquares.get(f5.getId())[1], f5.getId());
            }
        });
        ImageButton f6 = (ImageButton) findViewById(R.id.F6);
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f6.getId())[0], findSquares.get(f6.getId())[1], f6.getId());
            }
        });
        ImageButton f7 = (ImageButton) findViewById(R.id.F7);
        f7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f7.getId())[0], findSquares.get(f7.getId())[1], f7.getId());
            }
        });
        ImageButton f8 = (ImageButton) findViewById(R.id.F8);
        f8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(f8.getId())[0], findSquares.get(f8.getId())[1], f8.getId());
            }
        });

        ImageButton g1 = (ImageButton) findViewById(R.id.G1);
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g1.getId())[0], findSquares.get(g1.getId())[1], g1.getId());
            }
        });

        ImageButton g2 = (ImageButton) findViewById(R.id.G2);
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g2.getId())[0], findSquares.get(g2.getId())[1], g2.getId());
            }
        });

        ImageButton g3 = (ImageButton) findViewById(R.id.G3);
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g3.getId())[0], findSquares.get(g3.getId())[1], g3.getId());
            }
        });
        ImageButton g4 = (ImageButton) findViewById(R.id.G4);
        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g4.getId())[0], findSquares.get(g4.getId())[1], g4.getId());
            }
        });
        ImageButton g5 = (ImageButton) findViewById(R.id.G5);
        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g5.getId())[0], findSquares.get(g5.getId())[1], g5.getId());
            }
        });
        ImageButton g6 = (ImageButton) findViewById(R.id.G6);
        g6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g6.getId())[0], findSquares.get(g6.getId())[1], g6.getId());
            }
        });
        ImageButton g7 = (ImageButton) findViewById(R.id.G7);
        g7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g7.getId())[0], findSquares.get(g7.getId())[1], g7.getId());
            }
        });
        ImageButton g8 = (ImageButton) findViewById(R.id.G8);
        g8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(g8.getId())[0], findSquares.get(g8.getId())[1], g8.getId());
            }
        });

        ImageButton h1 = (ImageButton) findViewById(R.id.H1);
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h1.getId())[0], findSquares.get(h1.getId())[1], h1.getId());
            }
        });

        ImageButton h2 = (ImageButton) findViewById(R.id.H2);
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h2.getId())[0], findSquares.get(h2.getId())[1], h2.getId());
            }
        });

        ImageButton h3 = (ImageButton) findViewById(R.id.H3);
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h3.getId())[0], findSquares.get(h3.getId())[1], h3.getId());
            }
        });
        ImageButton h4 = (ImageButton) findViewById(R.id.H4);
        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h4.getId())[0], findSquares.get(h4.getId())[1], h4.getId());
            }
        });
        ImageButton h5 = (ImageButton) findViewById(R.id.H5);
        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h5.getId())[0], findSquares.get(h5.getId())[1], h5.getId());
            }
        });
        ImageButton h6 = (ImageButton) findViewById(R.id.H6);
        h6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h6.getId())[0], findSquares.get(h6.getId())[1], h6.getId());
            }
        });
        ImageButton h7 = (ImageButton) findViewById(R.id.H7);
        h7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h7.getId())[0], findSquares.get(h7.getId())[1], h7.getId());
            }
        });
        ImageButton h8 = (ImageButton) findViewById(R.id.H8);
        h8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(findSquares.get(h8.getId())[0], findSquares.get(h8.getId())[1], h8.getId());
            }
        });
        /*while(gameOn) {
            if (true) {
                System.out.println();
                //board.printBoard();
                //System.out.println();
                if (board.isInCheckMate) {
                    if (!whiteTurn) {
                        //System.out.println("White wins");
                    } else {
                        //System.out.println("Black wins");
                    }
                    gameOn = false;
                    break;
                }
                //System.out.println();
            }


            if (whiteTurn) {
                //System.out.print("White's move: ");
            } else {
                //System.out.print("Black's move: ");
            }




        }*/

    }

    public void makeMove(int row, int col, int id){
        if(firstClick.size()>0){

            int startRow = firstClick.get(0);
            int startCol = firstClick.get(1);



            int endCol = col;
            int endRow = row;

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

                // opens dialog for user to handle promotion when applicable (and handles the rest of promotion there)
                if(board.board[endRow][endCol] instanceof Pawn && (endRow==0 || endRow==7)){
                    promotionDialog(PlayChessGame.this, startRow, startCol, endRow, endCol, !whiteTurn, capturedPiece, id, firstMove);
                    return;
                }


                Piece pieceCaptured = null;
                if(capturedPiece.size()>0) pieceCaptured = capturedPiece.get(0);

                boolean castlingMove = false;
                if(Math.abs(startCol-endCol)==2 && board.board[endRow][endCol] instanceof King){
                    castlingMove = true;
                }

                // records whether a piece's first move was changed
                boolean firstMoveChanged = firstMove != board.board[endRow][endCol].firstMove;

                //add this move to the list of moves (this currently assumes no pawn promotion)
                gameMoves.add(new Move(startRow, startCol, firstClick.get(2), endRow, endCol, id,
                        !whiteTurn, enPassantPossible, false, castlingMove,
                        pieceCaptured, false, firstMoveChanged));

                updateUserView(endRow, endCol, id);
            }
            else if(enPassantPossible && board.enPassantValid(startRow, startCol, endRow, endCol, whiteTurn)) {
                if(whiteTurn)
                    whiteTurn=false;
                else {
                    whiteTurn = true;
                }
                enPassantPossible=false;

                updateUserView(endRow, endCol, id);

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

                Toast.makeText(PlayChessGame.this, "Illegal Move", Toast.LENGTH_LONG).show();
            }
            firstClick.clear();
        }
        else{
            firstClick.add(row);
            firstClick.add(col);
            firstClick.add(id);

        }
    }
    public void updateUserView(int endRow, int endCol, int id){

        ImageButton firstPiece = (ImageButton)findViewById(firstClick.get(2));
        firstPiece.setImageResource(0);


        // handle castling first
        if(board.board[endRow][endCol] instanceof King && Math.abs(firstClick.get(1)-endCol)==2){
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
                Toast.makeText(PlayChessGame.this,"CheckMate, White Wins", Toast.LENGTH_LONG).show();
            } else {
                //System.out.println("Black wins");
                Toast.makeText(PlayChessGame.this,"CheckMate, Black Wins", Toast.LENGTH_LONG).show();
            }
            offerSaveGameDialog(PlayChessGame.this);
        }
    }

    private void undoMove(){
        if(gameMoves.size()==0){
            Toast.makeText(PlayChessGame.this,"There are no moves to undo", Toast.LENGTH_LONG).show();
            return;
        }
        Move lastMove = gameMoves.get(gameMoves.size()-1);
        gameMoves.remove(gameMoves.size()-1);

        //handles undoing an enPassantMove
        if(lastMove.isEnPassantMove()){
            undoEnPassantMove(lastMove);
            return;
        }

        //handles undoing a castling move
        if(lastMove.isCastlingMove()){
            undoCastlingMove(lastMove);
            return;
        }


        Piece startPiece = board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()];
        // changes start piece back to a pawn if the move was a promotion
        if(lastMove.isPawnPromotion()){
            startPiece = new Pawn(!whiteTurn);
            startPiece.firstMove = false;
        }
        board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()] = startPiece;
        board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()] = lastMove.getCapturedPiece();
        enPassantPossible = false;
        // sets enPassantPossible to the previous value, if that value exists
        if(gameMoves.size()>0){
            enPassantPossible = gameMoves.get(gameMoves.size()-1).isEnPassantPossible();
        }
        whiteTurn = lastMove.isWhiteTurn();

        // if the move was a piece moving for the first time, resets that piece's firstMove boolean
        if(lastMove.isFirstMoveChanged()){
            board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()].firstMove = true;
        }


        // initially makes start Position blank, then fills in a piece there if one exists
        ImageButton startPosition = (ImageButton)findViewById(lastMove.getFirstClickID());
        startPosition.setImageResource(0);
        if(board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()]!=null){
            startPosition.setImageResource(pieceToDrawable.get(board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()].toString()));
        }

        // initially makes end Position blank, then fills in a piece there if one exists
        ImageButton endPosition = (ImageButton)findViewById(lastMove.getSecondClickID());
        endPosition.setImageResource(0);
        if(board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()]!=null){
            endPosition.setImageResource(pieceToDrawable.get(board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()].toString()));
        }
    }



    private void undoCastlingMove(Move lastMove){
        ImageButton kingsPreviousPosition = null;
        ImageButton rooksPreviousPosition = null;
        ImageButton restoreRookPosition = null;
        ImageButton restoreKingPosition = null;
        //castling top
        if(lastMove.getSecondClickRow()==0){
            //castling right
            if(lastMove.getSecondClickColumn()==6){
                board.board[0][6] = null;
                kingsPreviousPosition = (ImageButton) findViewById(R.id.G8);
                board.board[0][5] = null;
                rooksPreviousPosition = (ImageButton) findViewById(R.id.F8);
                board.board[0][7] = new Rook(false);
                restoreRookPosition = (ImageButton) findViewById(R.id.H8);
            }
            //castling left
            if(lastMove.getSecondClickColumn()==2){
                board.board[0][2] = null;
                kingsPreviousPosition = (ImageButton) findViewById(R.id.C8);
                board.board[0][3] = null;
                rooksPreviousPosition = (ImageButton) findViewById(R.id.D8);
                board.board[0][0] = new Rook(false);
                restoreRookPosition = (ImageButton) findViewById(R.id.A8);
            }
            board.board[0][4] = new King(false);
            restoreKingPosition = (ImageButton) findViewById(R.id.E8);
        }
        //castling bottom
        else if(lastMove.getSecondClickRow()==7){
            //castling right
            if(lastMove.getSecondClickColumn()==6){
                board.board[7][6] = null;
                kingsPreviousPosition = (ImageButton) findViewById(R.id.G1);
                board.board[7][5] = null;
                rooksPreviousPosition = (ImageButton) findViewById(R.id.F1);
                board.board[7][7] = new Rook(true);
                restoreRookPosition = (ImageButton) findViewById(R.id.H1);
            }
            //castling left
            if(lastMove.getSecondClickColumn()==2){
                board.board[7][2] = null;
                kingsPreviousPosition = (ImageButton) findViewById(R.id.C1);
                board.board[7][3] = null;
                rooksPreviousPosition = (ImageButton) findViewById(R.id.D1);
                board.board[7][0] = new Rook(true);
                restoreRookPosition = (ImageButton) findViewById(R.id.A1);
            }
            board.board[7][4] = new King(true);
            restoreKingPosition = (ImageButton) findViewById(R.id.E1);
        }
        kingsPreviousPosition.setImageResource(0);
        rooksPreviousPosition.setImageResource(0);
        restoreRookPosition.setImageResource(pieceToDrawable.get((new Rook(lastMove.whiteTurn).toString())));
        restoreKingPosition.setImageResource(pieceToDrawable.get((new King(lastMove.whiteTurn).toString())));

        enPassantPossible = false;
        // sets enPassantPossible to the previous value, if that value exists
        if(gameMoves.size()>0){
            enPassantPossible = gameMoves.get(gameMoves.size()-1).isEnPassantPossible();
        }
        whiteTurn = lastMove.isWhiteTurn();
    }

    private void undoEnPassantMove(Move lastMove){
        Piece movedPawn = board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()];
        board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()] = movedPawn;
        board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()] = null;
        Pawn capturedPawn = new Pawn(!movedPawn.isWhite);
        capturedPawn.firstMove = false;
        board.board[lastMove.getFirstClickRow()][lastMove.getSecondClickColumn()] = capturedPawn;
        enPassantPossible = false;
        // sets enPassantPossible to the previous value, if that value exists
        if(gameMoves.size()>0){
            enPassantPossible = gameMoves.get(gameMoves.size()-1).isEnPassantPossible();
        }
        whiteTurn = lastMove.isWhiteTurn();

        //resets visualization
        ImageButton startPosition = (ImageButton)findViewById(lastMove.getFirstClickID());
        startPosition.setImageResource(0);
        if(board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()]!=null){
            startPosition.setImageResource(pieceToDrawable.get(board.board[lastMove.getFirstClickRow()][lastMove.getFirstClickColumn()].toString()));
        }
        ImageButton endPosition = (ImageButton)findViewById(lastMove.getSecondClickID());
        endPosition.setImageResource(0);
        if(board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()]!=null){
            endPosition.setImageResource(pieceToDrawable.get(board.board[lastMove.getSecondClickRow()][lastMove.getSecondClickColumn()].toString()));
        }
        // resets captured pawn visualization
        if(lastMove.getFirstClickRow()==3){
            ImageButton capturedPawnPosition = null;
            if(lastMove.getSecondClickColumn()==0){
                capturedPawnPosition = (ImageButton) findViewById(R.id.A5);
            } else if(lastMove.getSecondClickColumn()==1){
                capturedPawnPosition = (ImageButton) findViewById(R.id.B5);
            } else if(lastMove.getSecondClickColumn()==2){
                capturedPawnPosition = (ImageButton) findViewById(R.id.C5);
            } else if(lastMove.getSecondClickColumn()==3){
                capturedPawnPosition = (ImageButton) findViewById(R.id.D5);
            } else if(lastMove.getSecondClickColumn()==4){
                capturedPawnPosition = (ImageButton) findViewById(R.id.E5);
            } else if(lastMove.getSecondClickColumn()==5){
                capturedPawnPosition = (ImageButton) findViewById(R.id.F5);
            } else if(lastMove.getSecondClickColumn()==6){
                capturedPawnPosition = (ImageButton) findViewById(R.id.G5);
            } else if(lastMove.getSecondClickColumn()==7){
                capturedPawnPosition = (ImageButton) findViewById(R.id.H5);
            }
            capturedPawnPosition.setImageResource(R.drawable.blackpawn);
        } else if(lastMove.getFirstClickRow()==4){
            ImageButton capturedPawnPosition = null;
            if(lastMove.getSecondClickColumn()==0){
                capturedPawnPosition = (ImageButton) findViewById(R.id.A4);
            } else if(lastMove.getSecondClickColumn()==1){
                capturedPawnPosition = (ImageButton) findViewById(R.id.B4);
            } else if(lastMove.getSecondClickColumn()==2){
                capturedPawnPosition = (ImageButton) findViewById(R.id.C4);
            } else if(lastMove.getSecondClickColumn()==3){
                capturedPawnPosition = (ImageButton) findViewById(R.id.D4);
            } else if(lastMove.getSecondClickColumn()==4){
                capturedPawnPosition = (ImageButton) findViewById(R.id.E4);
            } else if(lastMove.getSecondClickColumn()==5){
                capturedPawnPosition = (ImageButton) findViewById(R.id.F4);
            } else if(lastMove.getSecondClickColumn()==6){
                capturedPawnPosition = (ImageButton) findViewById(R.id.G4);
            } else if(lastMove.getSecondClickColumn()==7){
                capturedPawnPosition = (ImageButton) findViewById(R.id.H4);
            }
            capturedPawnPosition.setImageResource(R.drawable.whitepawn);
        }
    }

    private void offerSaveGameDialog(Context c) {
        try {
            ArrayList<SavedGame> savedGameArrayList = readFile(getApplicationContext());

            final EditText taskEditText = new EditText(c);
            AlertDialog dialog = new AlertDialog.Builder(c)
                    .setTitle("Save this Game")
                    .setMessage("Would you like to save this game? If so, enter a name:")
                    .setView(taskEditText)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String gameName = String.valueOf(taskEditText.getText());
                            Date gameDate = Calendar.getInstance().getTime();

                            // implement this code later if you want to make sure game names are unique
//                        if(gameName.equals("taken")){
//                            Toast.makeText(PlayChessGame.this,
//                                    "Your game could not be saved because you chose an already existing name." +
//                                            " Please try saving again.",
//                                    Toast.LENGTH_LONG).show();
//                            return;
//                        }
                            savedGameArrayList.add(new SavedGame(gameMoves, gameDate, gameName));
                            Log.d("savedGames: ", SavedGames.userSavedGames.toString());
                            writeToFile(savedGameArrayList, getApplicationContext());

                            SavedGames.userSavedGames.add(new SavedGame(gameMoves, gameDate, gameName));
                            startActivity(new Intent(getApplicationContext(), OriginalMenu.class));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(getApplicationContext(), OriginalMenu.class));


                        }
                    })
                    .create();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.show();

            dialog.getWindow().setAttributes(lp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        private void promotionDialog(Context c, int startRow, int startCol, int endRow, int endCol, boolean whiteTurn,
                                 ArrayList<Piece> capturedPiece, int id, boolean firstMove){
        AlertDialog.Builder buildPromotionList = new AlertDialog.Builder(c);
        buildPromotionList.setTitle("Promote pawn to one of the following pieces:");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(c, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Queen");
        arrayAdapter.add("Rook");
        arrayAdapter.add("Bishop");
        arrayAdapter.add("Knight");
        buildPromotionList.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);

                if(strName.equals("Queen")){
                    board.board[endRow][endCol] = new Queen(whiteTurn);
                } else if(strName.equals("Rook")){
                    board.board[endRow][endCol] = new Rook(whiteTurn);
                } else if(strName.equals("Bishop")){
                    board.board[endRow][endCol] = new Bishop(whiteTurn);
                } else if(strName.equals("Knight")){
                    board.board[endRow][endCol] = new Knight(whiteTurn);
                }

                board.isInCheck = board.checkCheck((whiteTurn));
                board.isInCheckMate = board.checkCheckMate(whiteTurn);

                Piece pieceCaptured = null;
                if(capturedPiece.size()>0) pieceCaptured = capturedPiece.get(0);

                boolean castlingMove = false;

                // records whether a piece's first move was changed
                boolean firstMoveChanged = firstMove != board.board[endRow][endCol].firstMove;

                //add this move to the list of moves (this currently assumes no pawn promotion)
                gameMoves.add(new Move(startRow, startCol, firstClick.get(2), endRow, endCol, id,
                        !whiteTurn, enPassantPossible, false, castlingMove,
                        pieceCaptured, true, firstMoveChanged));

                updateUserView(endRow, endCol, id);

                firstClick.clear();
            }
        });
        Dialog d = buildPromotionList.setView(new View(c)).create();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        d.show();
        d.getWindow().setAttributes(lp);
        //Log.d("savedGames: ", SavedGames.userSavedGames.toString());
    }

    private void writeToFile(ArrayList<SavedGame> userSavedGames, Context context){

            File file = new File(PlayChessGame.this.getFilesDir(),"text");
            if(!file.exists()){
                file.mkdir();
                Log.d("Message", "File does not exist");
            }
            try {
            File gpxfile = new File(file,"storedData");
            FileOutputStream fos = new FileOutputStream(gpxfile);
            ObjectOutputStream o = new ObjectOutputStream(fos);
            o.writeObject(userSavedGames);
            /*
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(userSavedGames.get(0).getGameName());
            writer.flush();
            writer.close();*/

            Toast.makeText(PlayChessGame.this, "Saved your text", Toast.LENGTH_LONG).show();
        } catch (Exception e) { }
            /*
            File mfile = context.getExternalFilesDir(null);
            ObjectOutput out;
            try {
                File outFile = new File(mfile,
                        "someRandom.txt");
                out = new ObjectOutputStream(new FileOutputStream(outFile));
                out.writeObject(userSavedGames);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
            FileOutputStream fos = context.openFileOutput("config.txt", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(userSavedGames);
            os.close();
            fos.close();*/

    }
    private ArrayList<SavedGame> readFile(Context context) throws IOException, ClassNotFoundException {
        try{
            File file = new File(PlayChessGame.this.getFilesDir()+"/text/storedData");
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);

            ArrayList<SavedGame> listGames = (ArrayList<SavedGame>) oi.readObject();
            return listGames;
        }
        catch(Exception e){
            return new ArrayList<SavedGame>();
        }

       /* for(int i=0; i<listGames.size(); i++) {
            Log.d("Read in Games", listGames.get(i).toString());
        }
        Log.d("Array size", ""+listGames.size());*/



        //Toast.makeText(PlayChessGame.this, "Read your text", Toast.LENGTH_LONG).show();

    }



}