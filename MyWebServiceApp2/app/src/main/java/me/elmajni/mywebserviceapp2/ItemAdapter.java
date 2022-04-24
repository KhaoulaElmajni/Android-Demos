package me.elmajni.mywebserviceapp2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<Task> tasks;
    private Context context;

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
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure to delete this task?")
                        .setContentText("You won't be able to retrieve this task again!")
                        .setConfirmText("Delete!")
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
}