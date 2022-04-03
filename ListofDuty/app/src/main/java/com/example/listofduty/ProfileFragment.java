package com.example.listofduty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ProfileFragment extends Fragment {
    Button button;
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frgmnt_profile, container, false);
        editText = view.findViewById(R.id.textSetName);

        editText.setText(getArguments().getString("setOwnerName",""));
        return view;
    }

    public static ProfileFragment newInstance(String someString) {
        ProfileFragment profileFragment = new ProfileFragment();

        Bundle bundle = new Bundle();
        bundle.putString("setOwnerName", someString);
        profileFragment.setArguments(bundle);

        return profileFragment;
    }
}
