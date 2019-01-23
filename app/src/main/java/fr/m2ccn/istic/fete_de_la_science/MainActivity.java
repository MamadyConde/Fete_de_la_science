package fr.m2ccn.istic.fete_de_la_science;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import fr.m2ccn.istic.fete_de_la_science.adapter.Myadapter;
import fr.m2ccn.istic.fete_de_la_science.model.EventData;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<EventData> listEventData;
    Myadapter adapter;




    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        listEventData= new ArrayList<EventData>();
        int i= 1;
        while (i < 6366) {
            reference = FirebaseDatabase.getInstance().getReference("dataFetedelaScience").child(Integer.toString(i));
            i++;
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String thematique = dataSnapshot.child("fields").child("thematiques").getValue(String.class);
                    String titre = dataSnapshot.child("fields").child("titre_fr").getValue(String.class);
                    String ville = dataSnapshot.child("fields").child("ville").getValue(String.class);
                   // String date = dataSnapshot.child("fields").child("region").getValue(String.class);

                    String image = dataSnapshot.child("fields").child("image").getValue(String.class);
                    String animation = dataSnapshot.child("fields").child("type_d_animation").getValue(String.class);
                    String description = dataSnapshot.child("fields").child("description_fr").getValue(String.class);
                    String descriptionlongue = dataSnapshot.child("fields").child("description_longue_fr").getValue(String.class);

                    String horaire = dataSnapshot.child("fields").child("horaires_detailles_fr").getValue(String.class);
                    String codePostal = dataSnapshot.child("fields").child("code_postal").getValue(String.class);
                    String adresse = dataSnapshot.child("fields").child("adresse").getValue(String.class);
                    String lieu = dataSnapshot.child("fields").child("nom_du_lieu").getValue(String.class);

/*                    String lieu = dataSnapshot.child("fields").child("").getValue(String.class);
                    String lieu = dataSnapshot.child("fields").child("").getValue(String.class);*/


                    //EventData myEventData = new EventData(thematique, titre, ville, date, image);
                    EventData myEventData = new EventData(thematique,titre, ville,image,animation,description,descriptionlongue,adresse,codePostal,horaire,lieu);
                    listEventData.add(myEventData);

                    adapter = new Myadapter(MainActivity.this, listEventData);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, " wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
