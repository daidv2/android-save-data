package com.example.android_save_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

public class HomeActivity extends AppCompatActivity {
    TextView getEmail, getPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getEmail = (TextView) findViewById(R.id.getEmail);
        getPass = (TextView) findViewById(R.id.getPass);
    }

    public void loadMe(View view) {
        try {
            FileInputStream fileInputStream = openFileInput("Code.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1) {
                buffer.append((char) read);
            }
            Log.d("Code", buffer.toString());
            String name = buffer.substring(0, buffer.indexOf(" "));
            String pass = buffer.substring(buffer.indexOf(" ") + 1);
            getEmail.setText(name);
            getPass.setText(pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
    }

    public void logOut(View view) {
        new PrefManager(getApplicationContext()).logOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
