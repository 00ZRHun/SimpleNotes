package com.example.simplenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.SimpleNotes.MESSAGE";
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CODE from HERE
        // add the list of notes to the listView in the main activity
        // => getting a list of files and adding them to an Array
        // => converting the array to an arrayAdapter with all filenames
        // => adding the adapter to the listView
        File files = getFilesDir();
        String[] array = files.list();
        ArrayList<String> arrayList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        for (String filename : array) {
            filename = filename.replace(".txt", "");
            System.out.println(filename);
            adapter.add(filename);
        }

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        //public void onClick(){
        /* public void onClick(View v) {
            EditText editTextHeading = (EditText) findViewById(R.id.editTextTextPersonName);
            EditText editTextContent = (EditText) findViewById(R.id.contentField);
            String heading = editTextHeading.getText().toString().trim();
            String content = editTextContent.getText().toString().trim();

            if (!heading.isEmpty()) {
                if(!content.isEmpty()) {
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(heading + ".txt", Context.MODE_PRIVATE); // heading will be the filename
                        fileOutputStream.write(content.getBytes());
                        fileOutputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    adapter.add(heading);
                    listView.setAdapter(adapter);
                } else {
                    editTextContent.setError("Content can't be empty!");
                }
            } else {
                editTextHeading.setError("Heading can't be empty!");
            }
        } */

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editTextHeading = (EditText) findViewById(R.id.editTextTextPersonName);
                EditText editTextContent = (EditText) findViewById(R.id.contentField);
                String heading = editTextHeading.getText().toString().trim();
                String content = editTextContent.getText().toString().trim();
                editTextHeading.setText("");
                editTextContent.setText("");


                if (!heading.isEmpty()) {
                    if(!content.isEmpty()) {
                        try {
                            FileOutputStream fileOutputStream = openFileOutput(heading + ".txt", Context.MODE_PRIVATE); // heading will be the filename
                            fileOutputStream.write(content.getBytes());
                            fileOutputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        adapter.add(heading);
                        listView.setAdapter(adapter);


                    } else {
                        editTextContent.setError("Content can't be empty!");
                    }
                } else {
                    editTextHeading.setError("Heading can't be empty!");
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = listView.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplication(), Note.class);
                intent.putExtra(EXTRA_MESSAGE, item);
                startActivity(intent);
            }
        });
    }





}