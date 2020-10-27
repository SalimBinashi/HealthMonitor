package com.example.healthmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.healthmonitor.utilities.SharedHelper;

import java.util.Calendar;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // views
    private EditText emailET, passwordET;
    private TextView dateET;
    private Button registerBtn;
    private ToggleButton statusToggle;
    DatePickerDialog datePickerDialog;
    private RadioGroup radioGroup;
    private CheckBox termsCB;

    // you can create this custom list or create an array list where data is placed when input by user
    String[] roles = {"Doctor", "Nurse"};
    Spinner roleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init views
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        dateET = findViewById(R.id.dateET);
        registerBtn = findViewById(R.id.registerBtn);
        termsCB = findViewById(R.id.termsCB);

        // init radio group
        radioGroup = findViewById(R.id.radioGroup);

        //init toggle button
        statusToggle = findViewById(R.id.statusToggle);

        // init spinner
        roleSpinner = findViewById(R.id.roleSpinner);
        roleSpinner.setOnItemSelectedListener(this);

        // create the spinner adapter
        ArrayAdapter roleAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Set the ArrayAdapter data on the Spinner
        roleSpinner.setAdapter(roleAdapter);


        // datePickerDialog components initialization

        // set up on click listeners for clickable items and buttons
        dateET.setOnClickListener(this);
        registerBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.equals(dateET)) {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            // create the date picker dialog
            datePickerDialog = new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dateET.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    }, year, month, day);
            datePickerDialog.show();
        } else if (view.equals(registerBtn)) {
            if (validate()) {
                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                String selectedRadioValue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                String dob = dateET.getText().toString().trim();
                String role = roleSpinner.getSelectedItem().toString();


                SharedHelper.putKey(MainActivity.this, "email", email);
                SharedHelper.putKey(MainActivity.this, "password", password);
                SharedHelper.putKey(MainActivity.this, "gender", selectedRadioValue);
                SharedHelper.putKey(MainActivity.this, "dob", dob);
                SharedHelper.putKey(MainActivity.this, "role", role);

                goToHomeActivity();
            }
        }

    }

    private void goToHomeActivity() {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    private boolean validate() {
        if (TextUtils.isEmpty(emailET.getText().toString())) {
            Toasty.warning(this, "Kindly fill in email", Toasty.LENGTH_SHORT).show();
            emailET.setFocusable(true);
            emailET.setError("Field is required");
            return false;

        } else if (TextUtils.isEmpty(passwordET.getText().toString())) {
            Toasty.warning(this, "Kindly fill in password", Toasty.LENGTH_SHORT).show();
            passwordET.setFocusable(true);
            passwordET.setError("Field is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailET.getText().toString()).matches()) {
            emailET.setFocusable(true);
            emailET.setError("Invalid email");
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toasty.warning(this, "Kindly select your gender", Toasty.LENGTH_SHORT).show();
            radioGroup.setFocusable(true);
            return false;
        } else if (TextUtils.isEmpty(dateET.getText().toString())) {
            Toasty.warning(this, "Kindly fill in DOB", Toasty.LENGTH_SHORT).show();
            dateET.setFocusable(true);
            return false;
        } else if (!statusToggle.isChecked()) {
            statusToggle.setFocusable(true);
            Toasty.warning(this, "Kindly activate your status", Toasty.LENGTH_SHORT).show();
            return false;
        } else if (!termsCB.isChecked()) {
            termsCB.setFocusable(true);
            Toasty.warning(this, "Kindly fill in accept terms to proceed", Toasty.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // check if button is checked
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.maleRadioBtn:
                if (checked)
                    // Show toast as male
                    Toasty.info(this, "Male", Toasty.LENGTH_SHORT).show();
                break;
            case R.id.femaleRadioBtn:
                if (checked)
                    // Show toast as female
                    Toasty.info(this, "Female", Toasty.LENGTH_SHORT).show();

                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // show toast of selection

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // show toast of selection
        Toasty.info(this, "Role not selected", Toasty.LENGTH_SHORT).show();

    }

    public void onToggleClick(View view) {
        if (statusToggle.isChecked()) {
            // show toast of selection
            Toasty.success(this, "Status Activated", Toasty.LENGTH_SHORT).show();
            String status = "Active";
            SharedHelper.putKey(MainActivity.this, "status", status);

        } else {
            // show toast of selection
            Toasty.warning(this, "Status Deactivated", Toasty.LENGTH_SHORT).show();
            String status = "InActive";
            SharedHelper.putKey(MainActivity.this, "status", status);


        }
    }
}