package me.elmajni.mywebserviceapp2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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


import com.google.gson.Gson;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<Task> tasks;
    private Context context;
    private ItemClickListener itemClickListener;

    public ItemAdapter(Context context, List<Task> tasks) {
        this.tasks = tasks;
        this.context= context;
        notifyDataSetChanged();
    }

    public ItemAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_row,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.taskname.setText(tasks.get(position).getName());
        holder.taskstatus.setText(tasks.get(position).getStatus());
        holder.taskDetails.setText(tasks.get(position).getDetails());

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(tasks.get(position));
        } );

        holder.btEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //initialize main data
                Intent intent = new Intent(context,UpdateTask.class);
                Gson gson = new Gson();
                String taskJson = gson.toJson(tasks.get(position));
                intent.putExtra("contactJson", taskJson);
                context.startActivity(intent);
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure to delete this task?")
                        .setContentText("You won't be able to retrieve this task again!")
                        .setConfirmText("Delete!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                /*Call<Task> call = Client.getRetrofitClient().deleteTask(tasks.get(position));
                                call.enqueue(new Callback<Task>() {
                                    @Override
                                    public void onResponse(Call<Task> call, Response<Task> response) {
                                        if (response.isSuccessful() && response.body() != null){
                                            System.out.println(response.toString());
                                            notifyDataSetChanged();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Task> call, Throwable t) {
                                        Toast.makeText(context, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                        System.out.println("error khaoula "+t.getMessage());
                                    }
                                });*/
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
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
        TextView taskname,taskstatus,taskDetails;
        ImageView btEdit, btDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.taskname);
            taskstatus = itemView.findViewById(R.id.taskstatus);
            btEdit=itemView.findViewById(R.id.btnEdit);
            btDelete = itemView.findViewById(R.id.btnDelete);
            taskDetails = itemView.findViewById(R.id.details);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(Task contact);
        public void onItemLongClick(Task contact);
    }

}