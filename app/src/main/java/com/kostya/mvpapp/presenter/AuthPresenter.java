package com.kostya.mvpapp.presenter;

import android.content.Context;
import android.util.Log;

import com.kostya.mvpapp.auth_interface.IAuthView;
import com.kostya.mvpapp.data.DbHelper;
import com.kostya.mvpapp.model.User;

import java.util.List;

public class AuthPresenter {

    private IAuthView iAuthView;
    private Context context;
    private DbHelper helper;

    public AuthPresenter(Context context,IAuthView iAuthView) {
        this.context = context;
        this.iAuthView = iAuthView;
        helper = new DbHelper(context);
    }

    public void insertUser(String email,String password){
        iAuthView.showProgress();

        User user = new User(email,password);
        if (user.validateData()) {

            int insertResult = helper.insertData(email, password);

            if (insertResult == -1) {
                iAuthView.failureInsert("Вставка произошла неудачно id = " + insertResult);
            } else {
                iAuthView.successInsert("Вставка прошла удачно!");
                iAuthView.getUpdatedUsers(helper.getAllUsers());
            }
        } else {
            iAuthView.failureInsert("Заполните пустые поля");
        }

        iAuthView.hideProgress();
    }

    public void getUsers(){
        iAuthView.showProgress();

        List<User> allUsers = helper.getAllUsers();

        iAuthView.getUsers(allUsers);
        iAuthView.hideProgress();
    }


}
