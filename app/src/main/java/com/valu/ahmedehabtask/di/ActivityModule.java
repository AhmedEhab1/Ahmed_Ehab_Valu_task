package com.valu.ahmedehabtask.di;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import androidx.appcompat.app.AlertDialog;

import com.valu.ahmedehabtask.utilities.Loading;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {

    @Provides
    public Handler providerHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    public Loading loading(){return new Loading();}

}
