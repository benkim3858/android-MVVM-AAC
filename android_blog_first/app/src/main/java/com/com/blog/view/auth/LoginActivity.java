package com.com.blog.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.com.blog.R;
import com.com.blog.service.dto.LoginDto;
import com.com.blog.view.InitActivity;
import com.com.blog.view.post.PostListActivity;
import com.com.blog.viewModel.auth.AuthViewModel;
import com.com.blog.viewModel.auth.LoginViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity implements InitActivity {

    private final LoginActivity mContext = LoginActivity.this;

    private TextInputEditText tfUsername, tfPassword;
    private MaterialButton btnLogin;
    private TextView tvLinkJoin;

    // 뷰모델
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        initLr();
        initViewModel();
        initSetting();
    }

    @Override
    public void init() {
        authViewModel = new AuthViewModel();
        tfUsername = findViewById(R.id.tfUsername);
        tfPassword = findViewById(R.id.tfPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvLinkJoin = findViewById(R.id.tvLinkJoin);
    }

    @Override
    public void initLr() {
        btnLogin.setOnClickListener(view -> {
            String username = tfUsername.getText().toString().trim();
            String password = tfPassword.getText().toString().trim();
            LoginDto loginDto = (new LoginDto(username,password));
            authViewModel.login(loginDto);
        });
    }

    @Override
    public void initViewModel() {
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        // 옵저버 관찰
        authViewModel.getLogin().observe(this,login->{
            Intent intent = new Intent(
                    mContext,
                    PostListActivity.class
            );
            startActivity(intent);
        });
    }

    @Override
    public void initSetting() {

    }
}