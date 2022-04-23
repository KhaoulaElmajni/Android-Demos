package me.elmajni.webserviceapp1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button add, reset;
    private List<Task> taskList;
    private EditText taskInput;
    //private static String JSON_URL = "http://192.168.0.102:8090/tasks";
    private ItemAdapter adapter;
    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tasksList);
        add = findViewById(R.id.btnAdd);
        reset = findViewById(R.id.btnRst);
        taskList = new ArrayList<>();
        taskInput = findViewById(R.id.taskInput);

        sendGetRequestForALlTasks();

        add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sendGetRequestForALlTasks();
               postRequestTask();
               taskInput.setText("");
           }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendGetRequestForALlTasks();
                deleteRequestAllTasks();
            }
        });

    }

    private void sendGetRequestForALlTasks() {
        taskList.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLs.ROOT_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject taskObject = response.getJSONObject(i);

                        Task task = new Task();
                        task.setName(taskObject.getString("name").toString());
                        task.setStatus(taskObject.getString("status").toString());

                        taskList.add(task);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new ItemAdapter(getApplicationContext(), taskList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void deleteRequestAllTasks() {

    }

    private void postRequestTask() {
        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", taskInput.getText().toString());
        params.put("status", "WAITING");
        JSONObject jsonObj = new JSONObject(params);
        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, URLs.ROOT_URL, jsonObj, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
        // And finally, we create a Volley Queue
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjRequest);

    }


}