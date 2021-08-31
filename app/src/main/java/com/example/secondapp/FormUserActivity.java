package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormUserActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    Button insertUserBtn;
    User user;
    boolean addUser = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);
        user = (User) getIntent().getSerializableExtra("user");
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        insertUserBtn = findViewById(R.id.insertUserBtn);
        if (user != null) {
            editTextName.setText(user.getUserName());
            editTextLastName.setText(user.getUserLastName());
            editTextPhone.setText(user.getPhone());
            addUser = false;
        }else {
            user = new User();
        }


        insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setUserName(editTextName.getText().toString());
                user.setUserLastName(editTextLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                Users users = new Users(FormUserActivity.this);
                if (addUser) users.addUser(user);
                else users.updateUser(user);
                onBackPressed();
            }
        });
    }
}