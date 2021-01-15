package com.example.kryptonote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    CipherModel obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onEncrypt(View v) {

        EditText keyView = (EditText) findViewById(R.id.key);
        String keyS = keyView.getText().toString();
        EditText dataView = (EditText) findViewById(R.id.data);
        String dataViewS = dataView.getText().toString();
        try {
            obj = new CipherModel(keyS);

            ((TextView) findViewById(R.id.data)).setText(obj.encrypt(dataViewS));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void onDecrypt(View v) {
        EditText keyView = (EditText) findViewById(R.id.key);
        String keyS = keyView.getText().toString();
        EditText dataView = (EditText) findViewById(R.id.data);
        String dataViewS = dataView.getText().toString();
        try {
            obj = new CipherModel(keyS);

            ((TextView) findViewById(R.id.data)).setText(obj.decrypt(dataViewS));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    public void onSave(View v) {
        try {
            String name = ((EditText) findViewById(R.id.file1)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast.makeText(this, "Note Saved.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();


        }
    }


    public void onLoad(View v) {
        try {
            String name = ((EditText) findViewById(R.id.file1)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read()) {
                show += (char) c;

            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
            Toast.makeText(this, "Note has been loaded.", Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}
