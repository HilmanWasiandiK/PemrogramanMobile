package com.example.listofduty;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.util.Calendar;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    Button button_DatePicker;
    MaterialButton button_Add;
    TextView text_Title, text_Desc, text_DatePicked;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.BottomSheetTheme);
        View v = inflater.inflate(R.layout.frgmnt_bottom_sheet,
                container, false);

        text_Title = v.findViewById(R.id.edtextTask);
        text_Desc = v.findViewById(R.id.edtextDesc);
        text_DatePicked = v.findViewById(R.id.textDatePicker);
        button_DatePicker = v.findViewById(R.id.buttonDatePicker);
        button_Add = v.findViewById(R.id.buttonSubmit);

        text_Title.addTextChangedListener(addTaskTextWatcher);

        button_DatePicker.setOnClickListener(view -> {
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

                text_DatePicked.setText(currentDateString);
            }, year, month, day);
            datePickerDialog.show();
        });

        button_Add.setOnClickListener(view -> {
            Model model = new Model(text_Title.getText().toString(), text_Desc.getText().toString(), text_DatePicked.getText().toString(), false);

            Bundle bundle = new Bundle();
            bundle.putParcelable("setDataTask",model);
            getParentFragmentManager().setFragmentResult("getDataTask",bundle);

            HomeFragment homeFragment = new HomeFragment();
            getParentFragmentManager().beginTransaction().add(homeFragment,"home").commit();
            dismiss();
        });
        return v;
    }

    private final TextWatcher addTaskTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String titleTask = text_Title.getText().toString().trim();

            button_Add.setEnabled(!(titleTask.isEmpty()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}

