package com.valu.ahmedehabtask.repository;

import androidx.lifecycle.LiveData;

import com.valu.ahmedehabtask.db.ProductsDao;
import com.valu.ahmedehabtask.model.ProductsResponse;
import com.valu.ahmedehabtask.network.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private ApiService apiService ;
    private ProductsDao productsDao ;

    @Inject
    public Repository(ApiService apiService, ProductsDao productsDao) {
        this.apiService = apiService;
        this.productsDao = productsDao;
    }

    public Observable<List<ProductsResponse>> getProducts(){
        return apiService.getProducts();
    }

    public void insertProduct(ProductsResponse productsResponse){
        productsDao.insertProduct(productsResponse);
    }

    public LiveData<List<ProductsResponse>> getOldProducts(){
       return productsDao.getProducts();
    }
}
