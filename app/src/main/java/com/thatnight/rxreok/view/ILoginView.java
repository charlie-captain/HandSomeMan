package com.thatnight.rxreok.view;

import com.thatnight.rxreok.bean.User;

/**
 * Time:2017.3.28 16:52
 * Created By:ThatNight
 */

public interface ILoginView {
    void loginSuccess(User user);

    void loginFailed();

    void setPbVisiable(int visiable);

    void showText(String text);

    String getUserName();

    String getPassword();

}
