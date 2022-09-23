package com.valu.ahmedehabtask.network;

import com.valu.ahmedehabtask.model.ProductsResponse;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products")
    Observable<List<ProductsResponse>> getProducts();
}
