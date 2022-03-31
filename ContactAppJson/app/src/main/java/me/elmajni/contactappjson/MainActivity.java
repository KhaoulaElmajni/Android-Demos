package me.elmajni.contactappjson;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button call;
    private ListView lv;
    private static ObjectMapper mapper = new ObjectMapper();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ItemModel> liste = new ArrayList<>();
        try {
            // convert JSON array to list of contacts
            List<ItemModel> contacts = Arrays.asList(mapper.readValue(getResources().openRawResource(R.raw.data_json), ItemModel[].class));
            for(ItemModel item: contacts)
                liste.add(item);
            //Appeller l'adaptateur
            monAdaptateur myAdapter = new monAdaptateur(liste);
            lv = findViewById(R.id.maliste);
            lv.setAdapter(myAdapter);
            lv.setClickable(true);
            //on item click
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this,Pop.class);
                    //ItemModel itemModel = (ItemModel) lv.getSelectedItem();
                    ItemModel itemModel = (ItemModel) lv.getItemAtPosition(i);
                    String name = itemModel.getName();
                    String job = itemModel.getJob();
                    String phone = itemModel.getPhone();
                    String email = itemModel.getEmail();

                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    bundle.putString("phone",phone);
                    bundle.putString("job",job);
                    bundle.putString("email",email);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void makeCall(View view) {
        call = findViewById(R.id.call);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0639127097"));
        startActivity(intent);
    }

    class monAdaptateur extends BaseAdapter {
        ArrayList<ItemModel> eltList = new ArrayList<ItemModel>();

        public monAdaptateur(ArrayList<ItemModel> elts) {
            this.eltList = elts;
        }

        public int getCount() {
            return eltList.size();
        }

        public ItemModel getItem(int i) {
            return eltList.get(i);
        }

        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater linflater = getLayoutInflater();
            View view1 = linflater.inflate(R.layout.item_view, null);
            //Récupérer chaque elt de la vue
            TextView name = view1.findViewById(R.id.name);
            TextView email = view1.findViewById(R.id.email);
            TextView job = view1.findViewById(R.id.job);
            TextView phone = view1.findViewById(R.id.phone);
            //Attribuer à chaque elt de vue sa propre val
            name.setText(eltList.get(i).getName());
            email.setText(eltList.get(i).getEmail());
            job.setText(eltList.get(i).getJob());
            phone.setText(eltList.get(i).getPhone());
            return view1;
        }
    }

}