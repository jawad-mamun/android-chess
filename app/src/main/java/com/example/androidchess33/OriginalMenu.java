package com.example.androidchess33;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class OriginalMenu extends AppCompatActivity{

       private Button newGame;
       private Button playSavedGame;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.original_menu);

            newGame = findViewById(R.id.startNewGame);
            playSavedGame = findViewById(R.id.playSavedGame);

            newGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), PlayChessGame.class));
                }
            });

            playSavedGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SavedGames.class ));
                }
            });

        }

}
