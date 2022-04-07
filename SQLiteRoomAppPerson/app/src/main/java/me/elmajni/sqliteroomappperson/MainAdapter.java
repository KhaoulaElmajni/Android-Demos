package me.elmajni.sqliteroomappperson;

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
    private List<Personne> dataList;
    private Activity context;
    private AppDatabase database;

    //Create constructor
    public MainAdapter(Activity context, List<Personne> dataList)
    {
       this.context=context;
       this.dataList=dataList;
       notifyDataSetChanged();
    }
    {

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
        Personne data=dataList.get(position);

        // Initialize database
        database=AppDatabase.getInstance(context);

        // Set text on text view
        holder.textView.setText(data.getId()+" : "+data.getName());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize main data
                Personne d=dataList.get(holder.getAdapterPosition());

                // Get id
                final int ID=d.getId();

                // Get text
                String name=d.getName();

                // create dialog
                final Dialog dialog=new Dialog(context);

                // set content view
                dialog.setContentView(R.layout.dialog_update);

                //Initialize width
                int width= WindowManager.LayoutParams.MATCH_PARENT;

                //Initialize height
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                //Set layout
                dialog.getWindow().setLayout(width,height);

                //show dialog
                dialog.show();

                //Initialize and assign variable
                final EditText editText=dialog.findViewById(R.id.name);
                Button btUpdate=dialog.findViewById(R.id.btnUpdate);

                // Set text on edit text
                editText.setText(name);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss Dialog
                        dialog.dismiss();

                        //Get Updated text from edit text
                        String uText=editText.getText().toString().trim();

                        // Update text in database
                        database.mainDao().update(ID, uText);

                        //notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize main data
                Personne d=dataList.get(holder.getAdapterPosition());

                database.mainDao().delete(d);
                //notify when data is updated
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            textView=itemView.findViewById(R.id.person);
            btEdit=itemView.findViewById(R.id.btnEdit);
            btDelete = itemView.findViewById(R.id.btnDelete);
           // btDelete=itemView.findViewById(R.id.bt_delete);
        }
    }
}
