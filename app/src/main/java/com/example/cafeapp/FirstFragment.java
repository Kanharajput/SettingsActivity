package com.example.cafeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cafeapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    // binding is like R in normal activity we can get all view with id non id views are not accessibile by it
    // It is created automatically if in gradle.build file contains viewBinding enabled
    // Binding is done for each layout and it's name is according to layout name and have a suffix Binding at last

    private FragmentFirstBinding binding;
    private String order ;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast(getString(R.string.donut_order_message));
            }
        });

        binding.iceCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast(getString(R.string.ice_cream_order_message));
            }
        });

        binding.froyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast(getString(R.string.froyo_order_message));
            }
        });

        // sending data to secondFragment using safe args
        // we have add plugin and class path in build files and clsses like FirstFragmentDirections and others automatically added
        // see documentation pass data between destinations -> https://developer.android.com/guide/navigation/navigation-pass-data
        // we can also do this via Bundles
        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(order);
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // to display toast when user click on an image
    public void displayToast(String message) {
        // saving order to send it to another fragment
        order = message;
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}