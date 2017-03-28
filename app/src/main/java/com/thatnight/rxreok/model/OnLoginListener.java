package com.thatnight.rxreok.model;

import com.thatnight.rxreok.bean.User;

/**
 * Time:2017.3.28 16:58
 * Created By:ThatNight
 */

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
