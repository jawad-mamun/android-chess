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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class SavedGames extends AppCompatActivity implements Serializable {

    public static ArrayList<SavedGame> userSavedGames = new ArrayList<SavedGame>();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private Button sortName;
    private Button sortDate;
    private ArrayList<String> nameList;
    private ArrayList<Date> dateList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_game);
        try {
            ArrayList<SavedGame> savedGamesList = readFile(SavedGames.userSavedGames, getApplicationContext());

            sortName = (Button)findViewById(R.id.sortName);
            sortDate = (Button)findViewById(R.id.sortDate);
            listView = (ListView)findViewById(R.id.listView);
            arrayList = new ArrayList<String>();

            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
            nameList = new ArrayList<String>();
            dateList = new ArrayList<Date>();



            for(int i=0; i<savedGamesList.size(); i++) {
                arrayList.add(savedGamesList.get(i).toString());
                nameList.add(savedGamesList.get(i).getGameName());
                dateList.add(savedGamesList.get(i).getDatePlayed());

            }
            sortName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectionSort(nameList, arrayList, savedGamesList);
                    Log.d("First in list", ""+arrayList.get(0));
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listView.refreshDrawableState();
                }
            });
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(SavedGames.this, ReplayGame.class);
                    intent.putExtra("clickedSavedGame", savedGamesList.get(position));
                    startActivity(intent);
                    Log.d("Position",""+position);
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
    private ArrayList<SavedGame> readFile(ArrayList<SavedGame> userSavedGames, Context context) throws IOException, ClassNotFoundException {
        File file = new File(SavedGames.this.getFilesDir()+"/text/storedData");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);

        ArrayList<SavedGame> listGames = (ArrayList<SavedGame>) oi.readObject();
        return listGames;
        /*for(int i=0; i<listGames.size(); i++) {
            Log.d("Read in Games", listGames.get(i).toString());
            Toast toast = Toast.makeText(SavedGames.this,""+listGames.get(i).toString(),Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, i*25);
            toast.show();
        }
        Log.d("Array size", ""+listGames.size());



            Toast.makeText(SavedGames.this, "Read your text", Toast.LENGTH_LONG).show();*/

    }
    public static void selectionSort(ArrayList<String> nameList, ArrayList<String> adapterList, ArrayList<SavedGame> savedGamesList )
    {

        // Find the string reference that should go in each cell of
        // the array, from cell 0 to the end
        for ( int j=0; j < nameList.size()-1; j++ )
        {
            // Find min: the index of the string reference that should go into cell j.
            // Look through the unsorted strings (those at j or higher) for the one that is first in lexicographic order
            int min = j;
            for ( int k=j+1; k < nameList.size(); k++ )
                if ( nameList.get(k).toLowerCase().compareTo( nameList.get(min).toLowerCase()) < 0 ) min = k;

            // Swap the reference at j with the reference at min
            Collections.swap(nameList, j, min);
            Collections.swap(savedGamesList, j, min);
            Collections.swap(adapterList, j, min);

        }

    }

}

