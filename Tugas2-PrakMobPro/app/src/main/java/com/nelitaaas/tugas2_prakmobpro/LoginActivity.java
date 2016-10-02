package com.nelitaaas.tugas2_prakmobpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * Created by nelitaaas on 02/10/16.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText txtUsername, txtPassword;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        assert txtUsername != null;
        txtUsername.addTextChangedListener(new MyTextWatcher(txtUsername));

        txtPassword = (EditText) findViewById(R.id.txtPassword);
        assert txtPassword != null;
        txtPassword.addTextChangedListener(new MyTextWatcher(txtPassword));

        Button btRegister = (Button) findViewById(R.id.btRegister);
        assert btRegister !=null;
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(LoginActivity.this, Register.class);
                startActivity(iRegister);
            }
        });

        Button btLogin = (Button) findViewById(R.id.btLogin);
        assert btLogin != null;
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        Button btTwitter = (Button)findViewById(R.id.loginTwitter);
        assert btTwitter != null;
        btTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTwitter = new Intent(LoginActivity.this, TwitterLogin.class);
                startActivity(iTwitter);
            }
        });

        Button btGplus = (Button)findViewById(R.id.loginGplus);
        assert  btGplus !=null;
        btGplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGPlus = new Intent(LoginActivity.this, GPlusLogin.class);
                startActivity(iGPlus);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.loginButton);
        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login dibatalkan.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error Login.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void submitForm() {
        if(!validateUsername()){
            return;
        }

        if(!validatePassword()){
            return;
        }

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if (username.equals("admin")&& password.equals("admin")) {
            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;
        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch (view.getId()){
                case R.id.txtUsername:
                    validateUsername();
                    break;

                case R.id.txtPassword:
                    validatePassword();
                    break;
            }

        }
    }

    private boolean validatePassword() {
        if(txtPassword.getText().toString().trim().isEmpty()){
            txtPassword.setError(getString(R.string.err_msg_pass));
            requestFocus(txtPassword);
            return false;
        }else {
            return true;
        }
    }

    private boolean validateUsername() {
        if(txtUsername.getText().toString().trim().isEmpty()){
            txtUsername.setError(getString(R.string.err_msg_username));
            requestFocus(txtUsername);
            return false;
        }else {
            return true;
        }
    }

    private void requestFocus(View view) {
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
