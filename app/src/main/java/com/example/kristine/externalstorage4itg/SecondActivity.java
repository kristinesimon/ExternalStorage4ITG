package com.example.kristine.externalstorage4itg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    TextView display;
    FileInputStream fis;
    EditText File;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        display = (TextView) findViewById(R.id.display);
        File = (EditText) findViewById(R.id.Filename);
        preferences = getSharedPreferences("myPref",MODE_PRIVATE);
    }

    public void Buff (File file) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = new FileInputStream(file);
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        display.setText(buffer.toString());
    }

    public void SharedPreferences(View view){
        String username = preferences.getString(File.getText().toString(), "");
        display.setText( username );
    }

    public void InternalStorage (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput(File.getText().toString());
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        display.setText(buffer.toString());
    }

    public void loadInternalCache(View view){
        File folder = getCacheDir();
        File file = new File(folder, "internalcache.txt");
        Buff(file);
    }

    public void loadExternalCache(View view){
        File folder = getExternalCacheDir();
        File file = new File(folder, "externalcache.txt");
        Buff(file);
    }

    public void loadExternalStorage(View view){
        File folder = getExternalFilesDir("temp");
        File file = new File(folder, "externalstorage.txt");
        Buff(file);
    }
    public void loadExternalPublicStorage(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "externalpublic.txt");
        Buff(file);
    }
    public void previous(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
