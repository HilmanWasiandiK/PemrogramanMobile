package com.example.listofduty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
//import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recview_ListTask;
    private List<Model> models;
//    private TextView text_Name;
    private MyAdapter adapter;
    private FloatingActionButton fab_AddNewTask;
    private MyViewModel viewModel;
    private ImageButton imbutton_DeleteAll;
    private CheckBox cbox_Task;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frgmnt_home, container, false);

        recview_ListTask = view.findViewById(R.id.recviewListTask);
        fab_AddNewTask =  view.findViewById(R.id.fabAddNewTask);
        imbutton_DeleteAll = view.findViewById(R.id.imbuttonDeleteAll);
        cbox_Task = view.findViewById(R.id.cboxTask);
        models = new ArrayList<>();

        fab_AddNewTask.setOnClickListener(view1 -> {
            BottomSheetDialog bottomSheet = new BottomSheetDialog();
            bottomSheet.show(getParentFragmentManager(), "modalBottomSheet");

            getParentFragmentManager().setFragmentResultListener("getDataTask", this, (requestKey, bundle) -> {
                Model model = bundle.getParcelable("setDataTask");

//                models.add(new Model(model.getTitle(),model.getDescription(), model.getDeadline(), model.isCheckbox()));
//                adapter.notifyDataSetChanged();
                viewModel.addTask(model);
            });
        });

        buildRecyclerView();

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getAllTask().observe(getViewLifecycleOwner(), new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                adapter.setModels(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteTask(adapter.getModelAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity().getApplicationContext(),"Task Deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recview_ListTask);

        imbutton_DeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_deleteall_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.popupSetNotification:
                                Intent intent = new Intent(getActivity(), SetNotificationActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.popupDeleteAll:
                                viewModel.deleteAllTask();
                                Toast.makeText(getActivity().getApplicationContext(),"All Tasks Deleted!",Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.popupDeleteDone:
                                viewModel.deleteDoneTask();
                                Toast.makeText(getActivity().getApplicationContext(),"Done Tasks Deleted!",Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        return view;
    }

    private void buildRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new MyAdapter(getActivity(),models);
        recview_ListTask.setLayoutManager(linearLayoutManager);
        recview_ListTask.setHasFixedSize(true);
        recview_ListTask.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model model) {
                Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
                intent.putExtra("task details", model);
                startActivity(intent);
            }
            @Override
            public void onCboxClick(Model model) {
                if(!model.isCheckbox()) {
                    model.setCheckbox(true);
                } else {
                    model.setCheckbox(false);
                }
                viewModel.updateTask(model);
            }
        });
    }

    public static HomeFragment newInstance(String someString) {
        HomeFragment homeFragment = new HomeFragment();

        Bundle bundle = new Bundle();
        bundle.putString("getOwnerName", someString);
        homeFragment.setArguments(bundle);

        return homeFragment;
    }
}
