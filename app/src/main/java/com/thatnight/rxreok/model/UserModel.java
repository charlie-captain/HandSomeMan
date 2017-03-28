package com.thatnight.rxreok.model;

import com.thatnight.rxreok.bean.User;

/**
 * Time:2017.3.28 16:51
 * Created By:ThatNight
 */

public class UserModel {

    public void login(final String userName, final String password, final OnLoginListener onLoginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (true) {
                    User user = new User(userName, password);
                    onLoginListener.loginSuccess(user);
                } else {
                    onLoginListener.loginFailed();
                }
            }
        }).start();
    }
}
