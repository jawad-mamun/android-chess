package com.example.androidchess33;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayChessGame extends AppCompatActivity {
    public HashMap<Integer, int[]> findSquares = new HashMap<Integer, int[]>();
    private ArrayList<Integer> firstClick = new ArrayList<Integer>();
    private boolean gameOn = true;
    private boolean whiteTurn = true;
    private boolean printBoard = true;
    private boolean enPassantPossible = false;
    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_chess_game_layout);

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

            if(board.move(startRow, startCol, endRow, endCol, whiteTurn)) {
                if(whiteTurn)
                    whiteTurn=false;
                else
                    whiteTurn = true;
                if((startRow == endRow+2 || startRow == endRow-2) && startCol==endCol) {
                    enPassantPossible = true;
                }
                else {
                    enPassantPossible = false;
                    // System.out.println(enPassantPossible);
                }

                updateUserView(endRow, endCol, id);
                //need to handle promotion
            }
            else if(enPassantPossible && board.enPassantValid(startRow, startCol, endRow, endCol, whiteTurn)) {
                // System.out.print(whiteTurn+"HEY");
                if(whiteTurn)
                    whiteTurn=false;
                else {
                    whiteTurn = true;
                }
                printBoard = true;
                enPassantPossible=false;
                updateUserView(endRow, endCol, id);

            }
            else {

                Toast.makeText(PlayChessGame.this, "Illegal Move", Toast.LENGTH_LONG).show();
                printBoard = false;
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

        Log.d("Difference:", String.valueOf(Math.abs(firstClick.get(1)-endCol)));

        // handle castling first
        if(board.board[endRow][endCol] instanceof King && Math.abs(firstClick.get(1)-endCol)==2){
            Log.d("Position:", endRow + ", " + endCol);
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

        if(board.board[endRow][endCol] instanceof Pawn){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            if(whiteTurn)
                secondPiece.setImageResource(R.drawable.blackpawn);
            else
                secondPiece.setImageResource(R.drawable.whitepawn);

        }
        if(board.board[endRow][endCol] instanceof Knight){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            if(whiteTurn)
                secondPiece.setImageResource(R.drawable.blackknight);
            else
                secondPiece.setImageResource(R.drawable.whiteknight);
        }
        if(board.board[endRow][endCol] instanceof Bishop){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            if(whiteTurn)
                secondPiece.setImageResource(R.drawable.blackbishop);
            else
                secondPiece.setImageResource(R.drawable.whitebishop);
        }
        if(board.board[endRow][endCol] instanceof Rook){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            if(whiteTurn)
                secondPiece.setImageResource(R.drawable.blackrook);
            else
                secondPiece.setImageResource(R.drawable.whiterook);
        }
        if(board.board[endRow][endCol] instanceof Queen){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            if(whiteTurn)
                secondPiece.setImageResource(R.drawable.blackqueen);
            else
                secondPiece.setImageResource(R.drawable.whitequeen);
        }
        if(board.board[endRow][endCol] instanceof King){
            ImageButton secondPiece = (ImageButton)findViewById(id);
            if(whiteTurn)
                secondPiece.setImageResource(R.drawable.blackking);
            else
                secondPiece.setImageResource(R.drawable.whiteking);
        }

        if (board.isInCheckMate) {
            if (!whiteTurn) {
                //System.out.println("White wins");
                Toast.makeText(PlayChessGame.this,"CheckMate, White Wins", Toast.LENGTH_LONG).show();
            } else {
                //System.out.println("Black wins");
                Toast.makeText(PlayChessGame.this,"CheckMate, Black Wins", Toast.LENGTH_LONG).show();
            }
            //Add functionality
        }
    }

}