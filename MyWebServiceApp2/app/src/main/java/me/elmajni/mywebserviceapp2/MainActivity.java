package me.elmajni.mywebserviceapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ItemAdapter adapter;
    private EditText taskInput,taskDetails;

    private Button add, reset;
    private List<Task> tasks=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.btnAdd);
        reset = findViewById(R.id.btnRst);
        taskInput = findViewById(R.id.taskInput);
        taskDetails = findViewById(R.id.taskDetails);



        recyclerView = findViewById(R.id.tasksList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(tasks);
        recyclerView.setAdapter(adapter);


        fetchTasks();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTask();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAllTasks();
            }
        });
    }

    private void resetAllTasks() {

    }

    private void createTask() {
        if (taskInput.getText().toString().equals("") || taskDetails.getText().toString().equals("")){
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("There are some Blancs?")
                    .setContentText("You won't be able to add a blanc task!")
                    .setConfirmText("OK!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();

        }else {
            Task task = new Task(taskInput.getText().toString(),"WAITING",taskDetails.getText().toString());
            Call<Task> call = Client.getRetrofitClient().createTask(task);
            call.enqueue(new Callback<Task>() {
                @Override
                public void onResponse(Call<Task> call, Response<Task> response) {
                    if (response.isSuccessful() && response.body() != null){
                        System.out.println(response.toString());
                        adapter.notifyDataSetChanged();
                        fetchTasks();
                    }

                }

                @Override
                public void onFailure(Call<Task> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("error khaoula "+t.getMessage());
                }
            });
        }
    }

    private void fetchTasks() {
        Client.getRetrofitClient().getTasks().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful() && response.body() != null){
                    System.out.println(response.toString());
                    tasks.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("error khaoula "+t.getMessage());
            }
        });
    }

}