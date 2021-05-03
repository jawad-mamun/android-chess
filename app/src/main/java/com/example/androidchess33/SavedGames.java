package com.example.androidchess33;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SavedGames extends AppCompatActivity{

    public static ArrayList<SavedGame> userSavedGames = new ArrayList<SavedGame>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_game);

    }

}

