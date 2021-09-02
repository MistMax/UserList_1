package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListFragment extends Fragment {
    Button addBtn;
    RecyclerView recyclerView;
    UserAdapter userAdapter; //Объявляем переменные
    ArrayList<User> userList = new ArrayList<>(); //Объявляем коллекцию
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = layoutInflater.inflate(R.layout.fragment_user_list, viewGroup, false);

        recyclerView = view.findViewById(R.id.recyclerView); //Находим RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // Установка LayoutManager для отображения списка
        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FormUserActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void recyclerViewInit(){
        Users users = new Users(getActivity());
        userList = users.getUserList();
        userAdapter = new UserAdapter(userList); //создание объекта адаптера
        recyclerView.setAdapter(userAdapter); //установка адаптера
    }
    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
    }


    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // Класс, для создания элементов RecyclerView
        TextView itemTextView; // Переменная
        User user;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) { // Конструктор для холдера
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView); // Инициализация
            itemView.setOnClickListener(this);
        }
        public void bind (String userName, User user) { // Метод для присвоения данных из адаптера
            this.user = user;
            itemTextView.setText(userName); // установка значения
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), UserPagerActivity.class);
            startActivity(intent);
            //MainActivity.changeFragment(view, user);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> { // класс адаптер, связывает данные и отображает
        ArrayList<User> users; // Объявляем переменную

        public UserAdapter(ArrayList<User> users) { // конструктор для принимания списка коллекции
            this.users = users; // Принимаем элементы коллекции
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //метод создает холдер, для заполнения
            LayoutInflater inflater = LayoutInflater.from(getActivity()); // создаем так называемый раздуватель для холдера
            return new UserHolder(inflater, parent); // холдер
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder userHolder, int position) { // метод, для передачи данных в холдер, для заполнения
            User user = users.get(position); // Получаем пользователей(позиционка)
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString, user);
        }

        @Override
        public int getItemCount() { // Метод для отображения кол-ва элементов в списке
            return users.size(); // Размер коллекции users
        }
    }
}
