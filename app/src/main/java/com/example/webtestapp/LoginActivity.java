package com.example.webtestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    View.OnClickListener onLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            new LoginHelper(LoginActivity.this) {
                @Override
                public void onLoginFailed(int code, String message) {
                    Toast.makeText(LoginActivity.this, "Error(code=" + code + "): " + message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLoginSuccess() {
                    Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            }.execute(user, pass);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etLoginUsername);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(onLoginClick);
    }
}
