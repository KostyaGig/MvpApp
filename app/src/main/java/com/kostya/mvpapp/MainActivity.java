package com.kostya.mvpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kostya.mvpapp.auth_interface.IAuthView;
import com.kostya.mvpapp.auth_interface.adapter.MainAdapter;
import com.kostya.mvpapp.model.User;
import com.kostya.mvpapp.presenter.AuthPresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements IAuthView {

    private ProgressDialog pd;

    private EditText edEmail,edPassword;
    private Button btnInsert;

    private RecyclerView mainRecView;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initProgressDialog();

        AuthPresenter presenter = new AuthPresenter(MainActivity.this,this);

        btnInsert.setOnClickListener(view -> {
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();

            presenter.insertUser(email,password);
        }
        );

        presenter.getUsers();
        mainRecView.setLayoutManager(new LinearLayoutManager(this));
        mainRecView.setAdapter(adapter);
    }

    private void init() {

        edEmail = findViewById(R.id.ed_email);
        edPassword = findViewById(R.id.ed_password);

        btnInsert = findViewById(R.id.btn_insert);

        mainRecView = findViewById(R.id.mainRecView);
    }

    private void initProgressDialog() {

        pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("Insert data");
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
    }


    @Override
    public void successInsert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d("jgkerg",message);
    }

    @Override
    public void failureInsert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d("jgkerg",message);
    }

    @Override
    public void getUpdatedUsers(List<User> allUsers) {
        adapter.updateListUsers(allUsers);
    }

    @Override
    public void getUsers(List<User> allUsers) {
        for (User users: allUsers){
            Log.d("jgkerg",allUsers.toString());
            Log.d("jgkerg",users.getEmail());
        }
        adapter = new MainAdapter(allUsers);

    }


    @Override
    public void showProgress() {
        new Thread(() -> {

            try {
                runOnUiThread(() -> pd.show());
                TimeUnit.SECONDS.sleep(3);
                runOnUiThread(() -> pd.dismiss());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        Log.d("jgkerg","show");
    }

    @Override
    public void hideProgress() {
        pd.dismiss();
        Log.d("jgkerg","dismiss");
    }
}