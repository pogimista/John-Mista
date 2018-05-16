package com.joko.floexam.deps;


import com.joko.floexam.main.MainActivity;
import com.joko.floexam.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by john.mista on 5/16/18.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(MainActivity mainActivity);
}
