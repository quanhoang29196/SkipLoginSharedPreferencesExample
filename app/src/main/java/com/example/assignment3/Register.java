package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText reg_name, reg_password;
    String regname, regpassword;
    Button regBtn;

    public static final String PREFERENCE= "PREFERENCE";
    public static final String PREF_NAME = "PREF_NAME";
    public static final String PREF_PASSWD = "PREF_PASSWD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_name = findViewById(R.id.reg_username);
        reg_password = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreference.edit();
                    mEditor.putString(PREF_NAME,regname);
                    mEditor.putString(PREF_PASSWD,regpassword);
                    mEditor.apply();
                    finish();
                }
            }
        });
    }

    private boolean  validation() {
        regname = reg_name.getText().toString().trim();
        regpassword = reg_password.getText().toString().trim();
        return !(regname.isEmpty() || regpassword.isEmpty());
    }
}
