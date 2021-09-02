package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserInfoFragment extends Fragment {
    private TextView userNameTextView;
    private TextView phoneTextView;
    private User user;
    private Button editUserBtn, deleteUserBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_user_info, viewGroup, false);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        userNameTextView.setText(user.getUserName()+"\n"+user.getUserLastName());
        phoneTextView.setText(user.getPhone());
        editUserBtn = view.findViewById(R.id.editUserBtn);
        deleteUserBtn = view.findViewById(R.id.deleteUserBtn);
        editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FormUserActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                // доп активность для редактирования
                // нужно передать данные пользователя (сериализация)
                // реализуем возможность изменить пользователя
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users users = new Users(getActivity());
                users.deleteUser(user.getUuid());
                //onBackPressed();
            }
        });
        return view;
    }
}
