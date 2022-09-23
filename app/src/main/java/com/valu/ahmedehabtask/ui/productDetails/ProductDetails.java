package com.valu.ahmedehabtask.ui.productDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valu.ahmedehabtask.R;
import com.valu.ahmedehabtask.databinding.FragmentSplashViewBinding;
import com.valu.ahmedehabtask.databinding.ProductDetailsBinding;
import com.valu.ahmedehabtask.model.ProductsResponse;
import com.valu.ahmedehabtask.utilities.ImageHelper;


public class ProductDetails extends Fragment {
    private ProductDetailsBinding binding ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = ProductDetailsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        initView();
        return binding.getRoot();
    }

    private void initView(){
        binding.back.setOnClickListener(view -> back());
        //getting data from bundel and set view data
        setViewData((ProductsResponse) (getArguments() != null ? getArguments().getParcelable("product") : null));
    }

    private void setViewData(ProductsResponse model){
        binding.productTitle.setText(model.getTitle());
        binding.productDescription.setText(model.getDescription());
        binding.productRating.setRating(model.getRating().getRate().floatValue());
        binding.productPrice.setText(String.valueOf(model.getPrice()).concat(" EGP"));
        binding.totalRate.setText(model.getRating().getCount().toString());
        ImageHelper.loadImage(getActivity(), model.getImage(), binding.productImage);
    }

    private void back(){
        Navigation.findNavController(requireView()).popBackStack();
    }
}