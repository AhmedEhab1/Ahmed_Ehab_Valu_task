package com.valu.ahmedehabtask.ui.splash;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valu.ahmedehabtask.R;
import com.valu.ahmedehabtask.databinding.FragmentSplashViewBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashView extends Fragment {
    private FragmentSplashViewBinding binding;

    @Inject
    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashViewBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        handler.postDelayed(this::startProductsView, 1000);
        return binding.getRoot();
    }

    private void startProductsView() {
        Navigation.findNavController(requireView()).navigate(R.id.action_splashView_to_productsView);
    }
}