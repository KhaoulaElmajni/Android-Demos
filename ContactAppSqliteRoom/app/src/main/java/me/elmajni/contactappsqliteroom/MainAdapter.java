package me.elmajni.contactappsqliteroom;

import android.app.Activity;
import android.app.Dialog;
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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private List<Contact> contacts;
    private Activity context;
    private AppDatabase db;
    private ItemClickListener itemClickListener;
    //Create constructor
    public MainAdapter(Activity context, List<Contact> contacts, ItemClickListener itemClickListener)
    {
       this.context=context;
       this.contacts = contacts;
       this.itemClickListener = itemClickListener;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // Initialize main data
        Contact contact= contacts.get(position);
        // Initialize database
        db =AppDatabase.getInstance(context);
        // Set text on text view
        holder.name.setText(contact.getName());
        holder.job.setText(contact.getJob());
        holder.email.setText(contact.getEmail());
        holder.phone.setText(contact.getPhone());
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(contacts.get(position));
        } );
    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface ItemClickListener{
        public void onItemClick(Contact contact);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,job,email,phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            job=itemView.findViewById(R.id.job);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
