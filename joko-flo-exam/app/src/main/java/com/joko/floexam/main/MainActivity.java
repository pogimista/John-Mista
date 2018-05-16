package com.joko.floexam.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joko.floexam.BaseApp;
import com.joko.floexam.R;
import com.joko.floexam.main.model.ElementField;
import com.joko.floexam.main.model.User;
import com.joko.floexam.networking.Service;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseApp implements MainView {

    @Inject
    public  Service service;

    private MainPresenter presenter;

    private LinearLayout llUsers;
    private RelativeLayout rlTap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        presenter = new MainPresenter(service, this);
        presenter.getUserInfo();
    }

    public  void renderView(){
        setContentView(R.layout.activity_home);
        llUsers = findViewById(R.id.ll_user);
        rlTap = findViewById(R.id.rel_tap_to_refresh);
    }

    public void init(){
        rlTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlTap.setVisibility(View.GONE);
                presenter.getUserInfo();
            }
        });
    }

    @Override
    public void showWait() {
        showLoading(getResources().getString(R.string.loading_get_info_list));
    }

    @Override
    public void removeWait() {
        hideLoading();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        rlTap.setVisibility(View.VISIBLE);
        showAlertDialog(getResources().getString(R.string.error_title),
                getResources().getString(R.string.error_generic));
    }

    @Override
    public void getUserInfoSuccess(List<User> users) {
        MainAdapter adapter = new MainAdapter(users,this);
        adapter.setUpLayout(llUsers);
    }

    @Override
    public void getTitleSuccess(String title) {
        getSupportActionBar().setTitle(title);
    }
}
