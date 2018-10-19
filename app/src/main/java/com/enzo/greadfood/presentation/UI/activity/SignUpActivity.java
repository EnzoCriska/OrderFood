package com.enzo.greadfood.presentation.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.enzo.greadfood.R;
import com.enzo.greadfood.presentation.presenter.SignupPresenter;
import com.enzo.greadfood.util.Injection;

public class SignUpActivity extends AppCompatActivity implements SignupPresenter.View {

    private EditText user, name, pass;
    private CardView signupBtn;
    private SignupPresenter mainPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();

    }

    public void init(){
        user = findViewById(R.id.userID);
        name = findViewById(R.id.userName);
        pass = findViewById(R.id.userPass);
        signupBtn = findViewById(R.id.btnSignup);
        progressDialog = new ProgressDialog(this);
        mainPresenter = Injection.getInstance().getSignupPresenter(this);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onClickEventSignup(user.getText().toString(), name.getText().toString(), pass.getText().toString());
            }
        });
    }
    @Override
    public void showSignup() {
        Toast.makeText(this, "Sign up Successfuly", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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
        name.setText("");
        pass.setText("");
    }
}
