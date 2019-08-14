package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    public static final String PREFERENCE = "PREFERENCE";
    public static final String PREF_SKIP_LOGIN = "skip_login";
    private SharedPreferences mSharedPreferences;
    Button LogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        LogOut = findViewById(R.id.logoutBtn);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = mSharedPreferences.edit();
                edit.remove(PREF_SKIP_LOGIN);
                edit.apply();
                Intent intent = new Intent(Welcome.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
