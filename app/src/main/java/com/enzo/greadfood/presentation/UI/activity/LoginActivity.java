package com.enzo.greadfood.presentation.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.enzo.greadfood.R;
import com.enzo.greadfood.domain.model.User;
import com.enzo.greadfood.presentation.presenter.LoginPresenter;
import com.enzo.greadfood.util.Common;
import com.enzo.greadfood.util.Injection;


public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {
    private EditText user, pass;
    private CardView loginBtn;
    private TextView register;
    private LoginPresenter mainPresenter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        progressDialog = new ProgressDialog(this);
        user = findViewById(R.id.userID);
        pass = findViewById(R.id.userPass);
        loginBtn = findViewById(R.id.btnLogin);
        mainPresenter = Injection.getInstance().getLoginPresenter(this);
        register = findViewById(R.id.rigisterUrl);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onClickEventLogin(user.getText().toString(), pass.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLogin(Boolean isLogin) {
        Toast.makeText(this, "Is Login " +isLogin, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        Common.currentUser = new User(user.getText().toString(), pass.getText().toString());
        startActivity(intent);
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        user.setText("");
        pass.setText("");
        onResume();
    }
}
