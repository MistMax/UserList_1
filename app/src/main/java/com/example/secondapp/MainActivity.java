package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    RecyclerView recyclerView;
    UserAdapter userAdapter; //Объявляем переменные
    ArrayList<User> userList = new ArrayList<>(); //Объявляем коллекцию
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь "+i);
            user.setUserLastName("Фамилия "+i);
            userList.add(user); // С помощью циклад добавляем в коллекцию пользователей.
        }*/

        recyclerView = findViewById(R.id.recyclerView); //Находим RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); // Установка LayoutManager для отображения списка
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormUserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void recyclerViewInit(){
        Users users = new Users(MainActivity.this);
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
            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
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
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this); // создаем так называемый раздуватель для холдера
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
