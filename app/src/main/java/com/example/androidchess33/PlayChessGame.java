package com.example.androidchess33;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class PlayChessGame extends AppCompatActivity {
    public HashMap<Integer, int[]> findSquares = new HashMap<Integer, int[]>();

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

        // eighth row inputted to HashMap
        findSquares.put(R.id.A1, new int[]{7,0});
        findSquares.put(R.id.B1, new int[]{7,1});
        findSquares.put(R.id.C1, new int[]{7,2});
        findSquares.put(R.id.D1, new int[]{7,3});
        findSquares.put(R.id.E1, new int[]{7,4});
        findSquares.put(R.id.F1, new int[]{7,5});
        findSquares.put(R.id.G1, new int[]{7,6});
        findSquares.put(R.id.H1, new int[]{7,7});

        // seventh row inputted to HashMap
        findSquares.put(R.id.A2, new int[]{6,0});
        findSquares.put(R.id.B2, new int[]{6,1});
        findSquares.put(R.id.C2, new int[]{6,2});
        findSquares.put(R.id.D2, new int[]{6,3});
        findSquares.put(R.id.E2, new int[]{6,4});
        findSquares.put(R.id.F2, new int[]{6,5});
        findSquares.put(R.id.G2, new int[]{6,6});
        findSquares.put(R.id.H2, new int[]{6,7});

        // sixth row inputted to HashMap
        findSquares.put(R.id.A3, new int[]{5,0});
        findSquares.put(R.id.B3, new int[]{5,1});
        findSquares.put(R.id.C3, new int[]{5,2});
        findSquares.put(R.id.D3, new int[]{5,3});
        findSquares.put(R.id.E3, new int[]{5,4});
        findSquares.put(R.id.F3, new int[]{5,5});
        findSquares.put(R.id.G3, new int[]{5,6});
        findSquares.put(R.id.H3, new int[]{5,7});

    }
}