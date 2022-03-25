package me.elmajni.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ItemModel> liste=new ArrayList<>();
        //Remplir notre liste
        liste.add(new ItemModel(1L,"Khaoula Elmajni", "elmajnikhaoula99@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(2L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(3L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(4L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(5L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(6L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(7L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(8L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(9L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(10L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(11L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(12L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(13L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));
        liste.add(new ItemModel(15L,"Ahmed Choukairi", "ahmedchoukairi@gmail.com","Software Engineer","+212639127097"));

        //Apler l'adaptateur
        monAdaptateur myAdapter = new monAdaptateur(liste);
        ListView lv = findViewById(R.id.maliste);
        lv.setAdapter(myAdapter);
    }

    class monAdaptateur extends BaseAdapter {
        ArrayList<ItemModel> eltList = new ArrayList<ItemModel>();
        public monAdaptateur(ArrayList<ItemModel> elts){
            this.eltList=elts;
        }

        public int getCount() {
            return eltList.size(); }
        public Object getItem(int i) {
            return eltList.get(i).getName();
        }
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater linflater = getLayoutInflater();
            View view1 = linflater.inflate(R.layout.item_view,null);
            //Récupérer chaque elt de la vue
            TextView name = view1.findViewById(R.id.name);
            TextView email= view1.findViewById(R.id.email);
            TextView job = view1.findViewById(R.id.job);
            TextView phone= view1.findViewById(R.id.phone);
            //Attribuer à chaque elt de vue sa propre val
            name.setText(eltList.get(i).getName());
            email.setText(eltList.get(i).getEmail());
            job.setText(eltList.get(i).getJob());
            phone.setText(eltList.get(i).getPhone());
            return view1;
        }
    }

}