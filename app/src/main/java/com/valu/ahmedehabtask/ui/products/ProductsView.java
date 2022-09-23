package com.valu.ahmedehabtask.ui.products;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valu.ahmedehabtask.R;
import com.valu.ahmedehabtask.adapters.ProductsAdapter;
import com.valu.ahmedehabtask.databinding.FragmentProductsViewBinding;
import com.valu.ahmedehabtask.interfaces.ProductsListener;
import com.valu.ahmedehabtask.model.ProductsResponse;
import com.valu.ahmedehabtask.viewmodels.ProductsViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductsView extends Fragment implements ProductsListener {
    private FragmentProductsViewBinding binding;
    private ProductsViewModel viewModel;

    public ProductsView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsViewBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        init();
        return binding.getRoot();
    }

    private void init() {
        shimmerAnimation(true);
        viewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        getProducts();
        onErrorOccurred();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void getProducts() {
        viewModel.getProducts();
        viewModel.getProductsList().observe(this, new Observer<List<ProductsResponse>>() {
            @Override
            public void onChanged(List<ProductsResponse> productsResponses) {
                initProductsRec(productsResponses);
            }
        });
    }

    private void initProductsRec(List<ProductsResponse> model) {
        shimmerAnimation(false);
        ProductsAdapter adapter = new ProductsAdapter(model, getActivity(), this::onProductClicked);
        binding.productsRec.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        binding.productsRec.setItemAnimator(new DefaultItemAnimator());
        binding.productsRec.setNestedScrollingEnabled(true);
        binding.productsRec.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewModel.insertProduct(model);
    }

    @Override
    public void onProductClicked(ProductsResponse model) {
        Bundle args = new Bundle();
        args.putParcelable("product", model);
        Navigation.findNavController(requireView()).navigate(R.id.action_productsView_to_productDetails, args);
    }

    private void shimmerAnimation(boolean state) {
        if (state) {
            binding.sflMockContent.startShimmerAnimation();
            binding.sflMockContent.setVisibility(View.VISIBLE);
        } else binding.sflMockContent.setVisibility(View.GONE);
    }

    private void getProductsFromDB() {
        viewModel.getOldProducts();
        viewModel.getOldProductsList().observe(this, new Observer<List<ProductsResponse>>() {
            @Override
            public void onChanged(List<ProductsResponse> productsResponses) {
                initProductsRec(productsResponses);
            }
        });
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void onErrorOccurred() {
        viewModel.getErrorMassage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                getProductsFromDB();
                showAlert(message);
            }
        });
    }

    private void showAlert(String message) {
        shimmerAnimation(false);
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.error))
                .setMessage(getString(R.string.server_error))
                .setPositiveButton(getString(R.string.try_again), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        shimmerAnimation(true);
                        getProducts();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}