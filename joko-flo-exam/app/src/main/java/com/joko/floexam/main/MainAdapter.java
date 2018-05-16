package com.joko.floexam.main;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joko.floexam.R;
import com.joko.floexam.main.model.ElementField;
import com.joko.floexam.main.model.User;

import java.util.List;

public class MainAdapter {

    private List<User> users;
    private Activity activity;

    public MainAdapter(List<User> users, Activity activity){
        this.users = users;
        this.activity = activity;
    }

    public void setUpLayout(LinearLayout layout){
        for (User user : users){
            layout.addView(createAdapter(user));
        }
    }

    private View createAdapter(User user){
        View view = activity.getLayoutInflater().inflate(R.layout.adapter_user,null);
        LinearLayout userAdapter = (LinearLayout)view.findViewById(R.id.ll_user_adapter);

        for (ElementField field : user.getElementFields()){
            TextView textView = new TextView(activity);
            if (field.getType().equals("textView")){
                String fieldValue = "" + user.getUserValues().get(field.getId());
                if (TextUtils.isEmpty(fieldValue)){
                    textView.setVisibility(View.GONE);
                }else {
                    textView.setText(field.getLabel() + " : " + fieldValue);
                }
            }
            userAdapter.addView(textView);
        }
        return userAdapter;
    }
}
