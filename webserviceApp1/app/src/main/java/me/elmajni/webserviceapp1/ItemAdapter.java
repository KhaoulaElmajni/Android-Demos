package me.elmajni.webserviceapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    LayoutInflater inflater;
    List<Task> tasks;

    public ItemAdapter(Context context, List<Task> tasks) {
        this.inflater = LayoutInflater.from(context);
        this.tasks = tasks;
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
    }

    @Override
    public int getItemCount() {
        int a ;
        if(tasks != null && !tasks.isEmpty()) {
            a = tasks.size();
        }
        else {
            a = 0;
        }
        return a;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView taskname,taskstatus;
        ImageView btEdit, btDelete;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.taskname);
            taskstatus = itemView.findViewById(R.id.taskstatus);
            btEdit=itemView.findViewById(R.id.btnEdit);
            btDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}