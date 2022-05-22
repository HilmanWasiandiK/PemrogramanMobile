package com.example.listofduty;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import androidx.lifecycle.Observer;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import java.util.List;

public class HomeFragment extends Fragment {
    private ActivityResultLauncher<Intent> resultLauncher;
    private SharedPreferences sharedpreferences_Username;
    private FloatingActionButton fab_AddNewTask;
    private RecyclerView recyclerview_TaskList;
    private ImageButton imagebutton_PopupMenu;
    private TextView textview_Username;
    private MyViewModel viewModel;
    private List<Model> models;
    private MyAdapter adapter;

    public static final String SHARED_USERNAME = "SharedUsername";
    public static final String TEXT = "There!";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frgmnt_home, container, false);

        sharedpreferences_Username = this.getActivity().getSharedPreferences(SHARED_USERNAME, Context.MODE_PRIVATE);
        imagebutton_PopupMenu = view.findViewById(R.id.imagebuttonPopupMenu);
        recyclerview_TaskList = view.findViewById(R.id.recyclerviewTaskList);
        textview_Username = view.findViewById(R.id.textviewUsername);
        fab_AddNewTask =  view.findViewById(R.id.fabAddNewTask);
        models = new ArrayList<>();

        textview_Username.setText(sharedpreferences_Username.getString(TEXT, "There!")+"!");
        fab_AddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getParentFragmentManager(), "modalBottomSheet");

                getParentFragmentManager().setFragmentResultListener("getDataTask", getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Model model = result.getParcelable("setDataTask");
                        viewModel.addTask(model);
                    }
                });
            }
        });

        buildRecyclerView();

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getAllTask().observe(getViewLifecycleOwner(), new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                adapter.setModels(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteTask(adapter.getModelAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity().getApplicationContext(),"Task Deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerview_TaskList);

        imagebutton_PopupMenu.setOnClickListener(new View.OnClickListener() {
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

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == -1) {
                    Intent intent = result.getData();

                    long id = intent.getLongExtra("aja", -1);
                    Toast.makeText(getContext(), "id"+id, Toast.LENGTH_SHORT).show();

                    if (id == -1) {
                        Toast.makeText(getContext(), "Note can't be updated", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(intent != null) {
                        Model modelelel = intent.getExtras().getParcelable("apa");
                        modelelel.setId(id);
                        viewModel.updateTask(modelelel);
                    }
                }
            }
        });
        return view;
    }

    private void buildRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new MyAdapter(getActivity(),models);
        recyclerview_TaskList.setLayoutManager(linearLayoutManager);
        recyclerview_TaskList.setHasFixedSize(true);
        recyclerview_TaskList.setAdapter(adapter);

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Model model) {
                Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
                intent.putExtra("task id", model.getId());
                intent.putExtra("task details", model);
                resultLauncher.launch(intent);
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
}

//                models.add(new Model(model.getTitle(),model.getDescription(), model.getDeadline(), model.isCheckbox()));
//                adapter.notifyDataSetChanged();
