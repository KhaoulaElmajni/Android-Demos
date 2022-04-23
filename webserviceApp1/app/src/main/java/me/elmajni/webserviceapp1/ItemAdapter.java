package me.elmajni.webserviceapp1;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    LayoutInflater inflater;
    List<Task> tasks;
    private Context context;

    public ItemAdapter(Context context, List<Task> tasks) {
        this.inflater = LayoutInflater.from(context);
        this.tasks = tasks;
        this.context= context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        holder.taskname.setText(tasks.get(position).getName());
        holder.taskstatus.setText(tasks.get(position).getStatus());
        holder.btEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //initialize main data
                Task task= tasks.get(holder.getAdapterPosition());
                //get id
                final int ID=task.getId();
                //get name
                String name=task.getName();
                //create dialog window for updating
                final Dialog dialog=new Dialog(context);
                //set content view
                dialog.setContentView(R.layout.dialog_update);
                //Initialize width & height
                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);

                //show update dialog
                dialog.show();

                //Initialize views
                EditText editText=dialog.findViewById(R.id.taskUpdate);
                Button btUpdate=dialog.findViewById(R.id.btnUpdate);
                editText.setText(name);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //close dialog window
                        dialog.dismiss();
                        /////traitement
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                JsonObjectRequest deleteRequest = new  JsonObjectRequest(Request.Method.DELETE,
                        URLs.ROOT_URL+String.valueOf(holder.getAdapterPosition()+2),null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("Response ", response.toString());
                                System.out.println(URLs.ROOT_URL+String.valueOf(holder.getAdapterPosition()+1));
                                Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                                Toast.makeText(context,String.valueOf(holder.getAdapterPosition()+1) ,Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(error.getMessage()+"****"+URLs.ROOT_URL+holder.getAdapterPosition());
                                Toast.makeText(context,error.getMessage()+"****"+URLs.ROOT_URL+holder.getItemId()+error.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id",String.valueOf(holder.getAdapterPosition()+2));
                        return super.getParams();
                    }
                };
                // And finally, we create a Volley Queue
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(deleteRequest);
            }
        });
    }

    @Override
    public int getItemCount() {
        int a ;
        if(tasks != null && !tasks.isEmpty()) {
            a = tasks.size();
        } else {
            a = 0;
        }
        return a;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView taskname,taskstatus;
        EditText taskinput;
        ImageView btEdit, btDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.taskname);
            taskstatus = itemView.findViewById(R.id.taskstatus);
            btEdit=itemView.findViewById(R.id.btnEdit);
            btDelete = itemView.findViewById(R.id.btnDelete);
            taskinput= itemView.findViewById(R.id.taskInput);

        }
    }
}