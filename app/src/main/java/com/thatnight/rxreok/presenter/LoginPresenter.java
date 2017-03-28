package com.thatnight.rxreok.presenter;

import android.os.Handler;
import android.view.View;

import com.thatnight.rxreok.bean.User;
import com.thatnight.rxreok.model.OnLoginListener;
import com.thatnight.rxreok.model.UserModel;
import com.thatnight.rxreok.view.ILoginView;

/**
 * Time:2017.3.28 16:51
 * Created By:ThatNight
 */

public class LoginPresenter {
    private ILoginView mILoginView;
    private UserModel mUserModel;
    private Handler mHandler = new Handler();

    public LoginPresenter(ILoginView ILoginView) {
        mILoginView = ILoginView;
        mUserModel = new UserModel();
    }


    public void login() {
        mILoginView.setPbVisiable(View.VISIBLE);
        mUserModel.login(mILoginView.getUserName(), mILoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mILoginView.showText(user.getUserName());
                        mILoginView.loginSuccess(user);
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mILoginView.loginFailed();
                    }
                });
            }
        });
    }
}
