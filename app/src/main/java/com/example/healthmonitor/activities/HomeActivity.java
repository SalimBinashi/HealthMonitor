package com.example.healthmonitor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthmonitor.R;
import com.example.healthmonitor.utilities.SharedHelper;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    // views
    TextView emailTv, genderTv, dobTv, roleTv, statusTv;
    private Button lookupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // init views
        emailTv = findViewById(R.id.emailTv);
        genderTv = findViewById(R.id.genderTv);
        dobTv = findViewById(R.id.dobTv);
        roleTv = findViewById(R.id.roleTv);
        statusTv = findViewById(R.id.statusTv);
        lookupBtn = findViewById(R.id.lookupBtn);

        lookupBtn.setOnClickListener(this);


        emailTv.setText(SharedHelper.getKey(HomeActivity.this, "email"));
        genderTv.setText(SharedHelper.getKey(HomeActivity.this, "gender"));
        dobTv.setText(SharedHelper.getKey(HomeActivity.this, "dob"));
        roleTv.setText(SharedHelper.getKey(HomeActivity.this, "role"));
        statusTv.setText(SharedHelper.getKey(HomeActivity.this, "status"));
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(lookupBtn)) {
            startActivity(new Intent(HomeActivity.this, LookupActivity.class));
        }

    }
}