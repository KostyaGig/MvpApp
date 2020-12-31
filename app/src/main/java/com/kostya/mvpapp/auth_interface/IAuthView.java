package com.kostya.mvpapp.auth_interface;

import android.util.Log;

import com.kostya.mvpapp.model.User;

import java.util.List;

public interface IAuthView extends ILoginView {

    void successInsert(String message);

    void failureInsert(String message);


    void getUpdatedUsers(List<User> allUsers);

    void getUsers(List<User> allUsers);
}
