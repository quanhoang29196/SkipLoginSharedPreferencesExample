package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCE = "PREFERENCE";
    public static final String PREF_NAME = "PREF_NAME";
    public static final String PREF_PASSWD = "PREF_PASSWD";
    public static final String PREF_SKIP_LOGIN = "skip_login";

    EditText username, password;
    String name, passwd;
    Button loginBtn, registerBtn, deleteBtn;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findID();

        if (mSharedPreferences.contains(PREF_SKIP_LOGIN)) {
            Intent intent = new Intent(MainActivity.this, Welcome.class);
            startActivity(intent);
            finish();
        } else {

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validData()) {
                        if (mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_PASSWD)) {
                            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                            mEditor.putString(PREF_SKIP_LOGIN,"skip");
                            mEditor.apply();
                            Intent intent = new Intent(MainActivity.this, Welcome.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please Register !!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter Data !!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //register new account
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        //delete all accounts in preference
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor pref = getApplicationContext().getSharedPreferences("PREFERENCE", 0).edit();
                pref.clear();
                pref.apply();
                Toast.makeText(MainActivity.this, "DELETED", Toast.LENGTH_LONG).show();
            }
        });

    }

    //findViewById
    private void findID() {
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
    }

    //checking input
    private boolean validData() {
        name = username.getText().toString().trim();
        passwd = password.getText().toString().trim();
        return !(name.isEmpty() || passwd.isEmpty());
    }
}
