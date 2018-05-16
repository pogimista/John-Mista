package com.joko.floexam.main;

import android.text.TextUtils;

import com.joko.floexam.main.model.Element;
import com.joko.floexam.main.model.ElementField;
import com.joko.floexam.main.model.ElementResponse;
import com.joko.floexam.main.model.User;
import com.joko.floexam.networking.JSONUtils;
import com.joko.floexam.networking.NetworkError;
import com.joko.floexam.networking.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by john.mista on 5/16/18.
 */
public class MainPresenter {
    private final Service service;
    private final MainView view;
    private CompositeSubscription subscriptions;

    public MainPresenter(Service service, MainView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }


    public void getUserInfo() {
        view.showWait();

        Subscription subscription = service.getUserInfoList(new Service.userInfoListCallback() {
            @Override
            public void onSuccess(ElementResponse response) {
                if (!TextUtils.isEmpty(response.getActivityTitle())){
                    view.getTitleSuccess(response.getActivityTitle());
                }
                if (response.getElements()!=null){
                    for (Element element : response.getElements()){
                        if (element.getId().equals("userInfoList")){
                            getUsers(element);
                        }
                    }
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }
        });

        subscriptions.add(subscription);
    }

   private void getUsers(final Element element) {
        view.showWait();

        Subscription subscription = service.getUsers(new Service.usersCallback() {
            @Override
            public void onSuccess(ResponseBody response) {
                List<User> users =  createUsers(element.getFields(),extractUsersFromResponseBody(response));
                view.removeWait();
                view.getUserInfoSuccess(users);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }
        },element.getData());
        subscriptions.add(subscription);
    }

    private JSONArray extractUsersFromResponseBody(ResponseBody responseBody){
        String body;
        JSONObject bodyObject;
        JSONArray userArray = null;
        try {
            body = responseBody.string();
            bodyObject = new JSONObject(body);
            userArray = bodyObject.getJSONArray("users");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return userArray;
    }

    private List<User> createUsers(List<ElementField> fields, JSONArray array) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i<array.length(); i++){
            try {
                User user = new User();
                JSONObject object = array.getJSONObject(i);

                user.setElementFields(fields);
                user.setUserValues(JSONUtils.toMap(object));
                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }
}
