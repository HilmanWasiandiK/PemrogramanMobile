package com.example.listofduty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton FAB;
    private RecyclerView recyclerView;
    private ArrayList<Model> models;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frgmnt_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        FAB =  view.findViewById(R.id.fab);

        models = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(getActivity(),models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        FAB.setOnClickListener(view1 -> {
            BottomSheetDialog bottomSheet = new BottomSheetDialog();
            bottomSheet.show(getParentFragmentManager(), "modalBottomSheet");

            getParentFragmentManager().setFragmentResultListener("getDataTask", this, (requestKey, bundle) -> {
                Model model = bundle.getParcelable("setDataTask");

                models.add(new Model(model.getTitle(),model.getDescription(), model.getDeadline(), model.isCheckbox()));
                adapter.notifyItemInserted(models.size()-1);
            });
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Toast.makeText(getActivity(), "Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getActivity(), "Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();

        Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();

        Toast.makeText(getActivity(), "Stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        Toast.makeText(getActivity(), "Destroyed", Toast.LENGTH_SHORT).show();
    }


}
