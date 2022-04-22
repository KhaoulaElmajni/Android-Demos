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
        holder.taskInput.setText(tasks.get(position).getName());
        holder.stat.setText(tasks.get(position).getId());
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
        TextView taskInput,stat;
        ImageView btEdit, btDelete;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            taskInput=itemView.findViewById(R.id.taskInput);
            stat = itemView.findViewById(R.id.state);
            btEdit=itemView.findViewById(R.id.btnEdit);
            btDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}