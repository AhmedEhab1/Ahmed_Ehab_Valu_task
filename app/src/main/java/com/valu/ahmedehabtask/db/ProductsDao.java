package com.valu.ahmedehabtask.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.valu.ahmedehabtask.model.ProductsResponse;

import java.util.List;

@Dao
public interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertProduct(ProductsResponse product);

    @Query("select * from products_list")
    public LiveData<List<ProductsResponse>> getProducts();
}
