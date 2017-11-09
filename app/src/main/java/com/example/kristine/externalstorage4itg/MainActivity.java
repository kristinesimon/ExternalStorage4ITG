package com.example.kristine.externalstorage4itg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etMessage, Filename;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMessage = (EditText) findViewById(R.id.etMessage);
        Filename = (EditText) findViewById(R.id.Filename);
        preferences = getSharedPreferences("myPref", MODE_PRIVATE);
    }

    public void SharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Filename.getText().toString(), etMessage.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved successfully in Shared Preferences.", Toast.LENGTH_LONG).show();
    }
    public void InternalStorage (View view) {
        String message = etMessage.getText().toString();

        try{
            fos = openFileOutput(Filename.getText().toString(), Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();
    }

    public void writeData(File file, String message) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveInternalCache(View view) {
        File folder = getCacheDir();
        File file = new File(folder, Filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to Internal Cache", Toast.LENGTH_LONG).show();
    }

    public void saveExternalCache(View view) {
        File folder = getExternalCacheDir();
        File file = new File(folder, Filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Cache", Toast.LENGTH_LONG).show();
    }

    public void saveExternalStorage(View view) {
        File folder = getExternalFilesDir("temp");
        File file = new File(folder, Filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Storage", Toast.LENGTH_LONG).show();
    }

    public void saveExternalPublicStorage(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, Filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Public Storage", Toast.LENGTH_LONG).show();
    }

    public void Next(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }

}
