package com.example.listofduty;

import static com.example.listofduty.HomeFragment.SHARED_USERNAME;
import static com.example.listofduty.HomeFragment.TEXT;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ProfileFragment extends Fragment {
    private SharedPreferences sharedpreferences_Username;
    Button button;
    EditText edittext_ChangeUsername;
    Button button_CancelChangeUsername, button_SaveChangeUsername;
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frgmnt_profile, container, false);
        editText = view.findViewById(R.id.textSetName);

        sharedpreferences_Username = this.getActivity().getSharedPreferences(SHARED_USERNAME, Context.MODE_PRIVATE);
        editText.setText(sharedpreferences_Username.getString(TEXT, "There!"));

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeUsernameDialog();
            }
        });
        return view;
    }

    private void showChangeUsernameDialog() {
        AlertDialog changeUsernameDialog;
        AlertDialog.Builder changeUsernameDialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alertdialog_change_username, null);

        edittext_ChangeUsername = view.findViewById(R.id.edittextChangeUsername);
        button_CancelChangeUsername = view.findViewById(R.id.buttonCancelChangeUsername);
        button_SaveChangeUsername = view.findViewById(R.id.buttonSaveChangeUsername);

        edittext_ChangeUsername.setText(sharedpreferences_Username.getString(TEXT, "There!"));
        edittext_ChangeUsername.addTextChangedListener(addTaskTextWatcher);

        changeUsernameDialogBuilder.setView(view);
        changeUsernameDialog = changeUsernameDialogBuilder.create();
        changeUsernameDialog.show();

        button_CancelChangeUsername.setBackgroundTintList(null);
        button_CancelChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeUsernameDialog.dismiss();
            }
        });

        button_SaveChangeUsername.setBackgroundTintList(null);
        button_SaveChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(edittext_ChangeUsername.getText().toString());
                sharedpreferences_Username.edit().putString(TEXT, edittext_ChangeUsername.getText().toString()).apply();
                changeUsernameDialog.dismiss();
            }
        });
    }

    private final TextWatcher addTaskTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String string = edittext_ChangeUsername.getText().toString().trim();

            button_SaveChangeUsername.setEnabled(!(string.isEmpty()));
            button_SaveChangeUsername.setBackgroundTintList(null);
            if(string.isEmpty()) {
                button_SaveChangeUsername.setBackgroundColor(getResources().getColor(R.color.greenOff));
            } else {
                button_SaveChangeUsername.setBackgroundColor(getResources().getColor(R.color.greenOn));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
