package com.thatnight.rxreok.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.thatnight.rxreok.R;
import com.thatnight.rxreok.bean.User;
import com.thatnight.rxreok.presenter.LoginPresenter;
import com.thatnight.rxreok.view.ILoginView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @InjectView(R.id.et_login_name)
    EditText mEtLoginName;
    @InjectView(R.id.et_login_pwd)
    EditText mEtLoginPwd;
    @InjectView(R.id.btn_login_login)
    Button mBtnLoginLogin;
    @InjectView(R.id.progressBar)
    ProgressBar mProgressBar;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        mLoginPresenter = new LoginPresenter(this);

    }

    @Override
    public void loginSuccess(User user) {
        setPbVisiable(View.INVISIBLE);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable("user",user);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "登录失败!", Toast.LENGTH_SHORT).show();
        setPbVisiable(View.INVISIBLE);
    }

    @Override
    public void setPbVisiable(int visiable) {
        mProgressBar.setVisibility(visiable);
        if (visiable == View.VISIBLE) {
            mBtnLoginLogin.setEnabled(false);
        } else {
            mBtnLoginLogin.setEnabled(true);
        }
    }

    @Override
    public void showText(String text) {
        Toast.makeText(this, text + " 登录成功!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserName() {
        return mEtLoginName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtLoginPwd.getText().toString();
    }

    @OnClick(R.id.btn_login_login)
    public void onClick() {
        mLoginPresenter.login();
    }
}
