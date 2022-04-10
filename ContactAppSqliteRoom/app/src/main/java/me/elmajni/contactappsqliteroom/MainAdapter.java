package me.elmajni.contactappsqliteroom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements Filterable {

    // Initialize variable
    private List<Contact> contacts;
    private Activity context;
    private AppDatabase db;
    private ItemClickListener itemClickListener;
    private List<Contact> contactListAll;

    //Create constructor
    public MainAdapter(Activity context, List<Contact> contacts, ItemClickListener itemClickListener)
    {
       this.context=context;
       this.contacts = contacts;
       this.itemClickListener = itemClickListener;
       contactListAll = new ArrayList<>(contacts);
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
        holder.itemView.setOnLongClickListener(view -> {
            itemClickListener.onItemLongClick(contacts.get(position));
            return true;
        } );

        holder.call.setTag(contact);
        holder.sendMsg.setTag(contact);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = (Contact) view.getTag();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone()));
                context.startActivity(intent);
            }
        });

        holder.sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contact.getPhone(), null)));
            }
        });
    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        //run on bg thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Contact> filteredList=new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredList.addAll(contactListAll);
            }else {
                for (Contact c:contactListAll){
                    if (c.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(c);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //run on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            contacts.clear();
            contacts.addAll((Collection<? extends Contact>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public interface ItemClickListener{
        public void onItemClick(Contact contact);
        public void onItemLongClick(Contact contact);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,job,email,phone;
        Button call,sendMsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            job=itemView.findViewById(R.id.job);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            call = itemView.findViewById(R.id.call);
            sendMsg = itemView.findViewById(R.id.msg);


        }
    }
}
