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
    //Create constructor
    public MainAdapter(Activity context, List<Contact> contacts)
    {
       this.context=context;
       this.contacts = contacts;
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
        Contact personne= contacts.get(position);
        // Initialize database
        db =AppDatabase.getInstance(context);
        // Set text on text view
        holder.textView.setText(personne.getId()+" : "+personne.getName());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize main data
                Contact personne1= contacts.get(holder.getAdapterPosition());
                //get id
                final int ID=personne1.getId();
                //get name
                String name=personne1.getName();
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
                EditText editText=dialog.findViewById(R.id.name);
                Button btUpdate=dialog.findViewById(R.id.btnUpdate);
                editText.setText(name);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //close dialog window
                        dialog.dismiss();
                        //get Updated person from edit text
                        String name=editText.getText().toString().trim();
                        //update person in DB
                        db.personneDao().update(ID, name);
                        //notify when data is updated
                        personnes.clear();
                        personnes.addAll(db.personneDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return contacts.size();
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
