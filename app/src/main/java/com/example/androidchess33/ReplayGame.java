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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replay_game);
        //Toast.makeText(ReplayGame.this, "In ReplayGame Activity", Toast.LENGTH_LONG).show();
        SavedGame savedGame = (SavedGame) getIntent().getSerializableExtra("clickedSavedGame");
        Toast.makeText(ReplayGame.this, ""+savedGame.toString(), Toast.LENGTH_LONG).show();
        ArrayList<Move> moveList = savedGame.getGameMoves();




    }


}


