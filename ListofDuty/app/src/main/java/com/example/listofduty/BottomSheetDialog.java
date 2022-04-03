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
import java.text.SimpleDateFormat;
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
            Calendar getCalendar = Calendar.getInstance();
            int year = getCalendar.get(Calendar.YEAR);
            int month = getCalendar.get(Calendar.MONTH);
            int day = getCalendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year1, month1, day1) -> {
                Calendar setCalendar = Calendar.getInstance();
                setCalendar.set(Calendar.YEAR, year1);
                setCalendar.set(Calendar.MONTH, month1);
                setCalendar.set(Calendar.DAY_OF_MONTH, day1);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, EEEE");
                String currentDateString = format.format(setCalendar.getTime());
                text_DatePicked.setText(currentDateString);
            }, year, month, day);
            datePickerDialog.show();
        });

        button_Add.setOnClickListener(view -> {
            Model model = new Model(text_Title.getText().toString(), text_Desc.getText().toString(), text_DatePicked.getText().toString(), false);

            Bundle bundle = new Bundle();
            bundle.putParcelable("setDataTask",model);
            getParentFragmentManager().setFragmentResult("getDataTask",bundle);

            dismiss();
            Toast.makeText(getActivity().getApplicationContext(), "New Task Added!", Toast.LENGTH_SHORT).show();
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

