package com.example.listofduty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private TextView text_Name;
    private ArrayList<Model> models;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frgmnt_home, container, false);
        FloatingActionButton fab_AddNewTask;
        RecyclerView recview_ListTask;

        recview_ListTask = view.findViewById(R.id.recviewListTask);
        fab_AddNewTask =  view.findViewById(R.id.fabAddNewTask);

        models = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(getActivity(),models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recview_ListTask.setLayoutManager(linearLayoutManager);
        recview_ListTask.setAdapter(adapter);

        fab_AddNewTask.setOnClickListener(view1 -> {
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
}
