package com.valu.ahmedehabtask.di;

import android.app.Application;

import androidx.room.Room;

import com.valu.ahmedehabtask.db.ProductsDao;
import com.valu.ahmedehabtask.db.ProductsDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static ProductsDataBase provideDB(Application application) {
        return Room.databaseBuilder(application ,ProductsDataBase.class,"products_list" )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static ProductsDao productsDao(ProductsDataBase productsDataBase){
        return productsDataBase.productsDao();
    }
}
