package com.example.listofduty;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.util.Calendar;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    Button btnDatePicker;
    MaterialButton btnAdd;
    TextView txtTitle, txtDesc, txtDatePicked;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BottomSheetTheme);
        View v = inflater.inflate(R.layout.frgmnt_bottom_sheet,
                container, false);

        txtTitle = v.findViewById(R.id.edtextTask);
        txtDesc = v.findViewById(R.id.edtextDesc);
        txtDatePicked = v.findViewById(R.id.textDatePicker);
        btnDatePicker = v.findViewById(R.id.btnDatePicker);

        btnDatePicker.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year1, month1, day1) -> {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year1);
                calendar1.set(Calendar.MONTH, month1);
                calendar1.set(Calendar.DAY_OF_MONTH, day1);
                String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar1.getTime());

                txtDatePicked.setText(currentDateString);
            }, year, month, day);
            datePickerDialog.show();
        });

        btnAdd = v.findViewById(R.id.btnSubmit);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model = new Model(txtTitle.getText().toString(), txtDesc.getText().toString(), txtDatePicked.getText().toString(), false);

                Bundle bundle = new Bundle();
                bundle.putParcelable("setDataTask",model);

                getParentFragmentManager().setFragmentResult("getDataTask",bundle);


////                navController.navigate(R.id.home_id);
//                dismiss();
                HomeFragment homeFragment = new HomeFragment();
                getParentFragmentManager().beginTransaction().add(homeFragment,"home").commit();
                dismiss();
            }
        });
        return v;
    }
}

