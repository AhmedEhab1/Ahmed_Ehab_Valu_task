package com.valu.ahmedehabtask.db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.valu.ahmedehabtask.model.ProductsResponse;

@Database(entities = ProductsResponse.class , version = 1, exportSchema = false)
public abstract class ProductsDataBase extends RoomDatabase {
    public abstract ProductsDao productsDao() ;
}
