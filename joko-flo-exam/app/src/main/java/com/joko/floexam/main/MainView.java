package com.joko.floexam.main;

import com.joko.floexam.main.model.User;

import java.util.List;

/**
 * Created by john.mista on 5/16/18.
 */
public interface MainView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getUserInfoSuccess(List<User> users);

    void getTitleSuccess(String title);
}
