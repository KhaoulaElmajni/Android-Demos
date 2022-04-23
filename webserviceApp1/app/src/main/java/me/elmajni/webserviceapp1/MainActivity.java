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
    private static String JSON_URL = "http://192.168.0.102:8090/tasks";
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
               postRequestTask();
               sendGetRequestForALlTasks();
           }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRequestAllTasks();
                sendGetRequestForALlTasks();
            }
        });

    }

    private void deleteRequestAllTasks() {

    }

    private void postRequestTask() {

        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // At this point, Volley has sent the data to your URL
                        // and has a response back from it. I'm going to assume
                        // that the server sends an "OK" string.
                        if (response.equals("OK")) {
                            // Do login stuff.

                            Toast.makeText(MainActivity.this, "new task has been added : "+response, Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        } else {
                            // So the server didn't return an "OK" response.
                            // Depending on what you did to handle errors on your
                            // server, you can decide what action to take here.
                            Toast.makeText(MainActivity.this, "nothing added", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // This is when errors related to Volley happen.
                        // It's up to you what to do if that should happen, but
                        // it's usually not a good idea to be too clear as to
                        // what happened here to your users.
                        Log.i("stringRequest", "Error, Status Code " + error.networkResponse.statusCode);
                        Log.i("stringRequest", "URL: " + JSON_URL);
                        Log.i("stringRequest", "Net Response to String: " + error.networkResponse.toString());
                        Log.i("stringRequest", "Error bytes: " + new String(error.networkResponse.data));

                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Here is where we tell Volley what it should send in
                // our POST request. For this example, we want to send
                // We will need key ids for our data, so our server can know
                // what is what.

                Map<String, String> map = new HashMap<>();

                // map.put(key, value);
                map.put("name", taskInput.getText().toString());
                map.put("status", "WAITING");
                return map;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };*/

        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", taskInput.getText().toString());
        params.put("status", "WAITING");
        JSONObject jsonObj = new JSONObject(params);
        // Request a json response from the provided URL
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.POST, JSON_URL, jsonObj, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
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


        // And finally, we create a Volley Queue. For this example, I'm using
        // getContext(), because I was working with a Fragment. But context could
        // be "this", "getContext()", etc.
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjRequest);
    }

    private void sendGetRequestForALlTasks() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
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
}