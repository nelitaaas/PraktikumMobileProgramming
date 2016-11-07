package com.nelitaaas.tugas4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by amarullah87 on 06/11/16.
 */

public class LoginActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText txtUsername, txtPassword;
    Button btLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        dbHelper = new DBHelper(getApplicationContext());

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btLogin = (Button) findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                int row = dbHelper.cekLogin(username, password);
                if(row != 0){
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
