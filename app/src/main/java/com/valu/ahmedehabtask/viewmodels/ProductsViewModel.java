package com.valu.ahmedehabtask.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.valu.ahmedehabtask.model.ProductsResponse;
import com.valu.ahmedehabtask.repository.Repository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductsViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<ProductsResponse>> productsList = new MutableLiveData<>();
    private LiveData<List<ProductsResponse>> oldProductsList = null;
    private MutableLiveData<String> errorMassage = new MutableLiveData<>();

    @ViewModelInject
    public ProductsViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<ProductsResponse>> getOldProductsList() {
        return oldProductsList;
    }

    public MutableLiveData<List<ProductsResponse>> getProductsList() {
        return productsList;
    }

    public MutableLiveData<String> getErrorMassage() {
        return errorMassage;
    }

    // getting products from repository using RXjava to call api in background thread
    public void getProducts() {
        repository.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productsResponses -> productsList.setValue(productsResponses),
                        error -> getErrorMessage(error.getMessage()));
    }

    // show error alert dialog
    private void getErrorMessage(String message){
        Log.e("viewModel", message);
        errorMassage.setValue(message);
    }

    // insert product in room database
    public void insertProduct(List<ProductsResponse> productsResponse) {
        for (int i = 0; i < productsResponse.size(); i++) {
            repository.insertProduct(productsResponse.get(i));
        }
    }

    //  getting products from repository
    public void getOldProducts() {
        oldProductsList = repository.getOldProducts();
    }
}
