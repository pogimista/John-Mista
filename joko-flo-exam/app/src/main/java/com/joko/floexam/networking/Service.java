package com.joko.floexam.networking;


import com.joko.floexam.main.model.ElementResponse;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by john.mista on 5/16/18.
 */
public class Service {

    private final String BASE_URL = "http://www.mocky.io/v2/5afaa7cd2e00005000279004";
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }


    public Subscription getUserInfoList(final userInfoListCallback callback) {

        return networkService.getUsersField(BASE_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ElementResponse>>() {
                    @Override
                    public Observable<? extends ElementResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ElementResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ElementResponse response) {
                        callback.onSuccess(response);

                    }
                });
    }
    public interface userInfoListCallback{
        void onSuccess(ElementResponse response);

        void onError(NetworkError networkError);
    }

    public Subscription getUsers(final usersCallback callback,String userURL) {

        return networkService.getUsers(userURL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ResponseBody>>() {
                    @Override
                    public Observable<? extends ResponseBody> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(ResponseBody response) {
                        callback.onSuccess(response);

                    }
                });
    }
    public interface usersCallback{
        void onSuccess(ResponseBody response);

        void onError(NetworkError networkError);
    }
}
