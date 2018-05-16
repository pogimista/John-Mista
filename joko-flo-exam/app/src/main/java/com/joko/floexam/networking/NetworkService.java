package com.joko.floexam.networking;


import com.joko.floexam.main.model.ElementResponse;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by john.mista on 5/16/18.
 */
public interface NetworkService {

    @GET
    Observable<ElementResponse> getUsersField(@Url String url);

    @GET
    Observable<ResponseBody> getUsers(@Url String url);

}
