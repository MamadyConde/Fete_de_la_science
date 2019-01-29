package fr.m2ccn.istic.fete_de_la_science;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.m2ccn.istic.fete_de_la_science.adapter.Myadapter;
import fr.m2ccn.istic.fete_de_la_science.model.EventData;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<EventData> listEventData;
    Myadapter adapter;
    private int selectedFilter = 0;




    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);


        recyclerView.setHasFixedSize(true);
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
                    String date = dataSnapshot.child("fields").child("dates").getValue(String.class);
                    String motcle = dataSnapshot.child("fields").child("mots_cles_fr").getValue(String.class);
                    String telephone = dataSnapshot.child("fields").child("telephone_du_lieu").getValue(String.class);


                    EventData myEventData = new EventData(thematique,titre, ville,image,animation,description,descriptionlongue,adresse,codePostal,horaire,lieu,date,motcle,telephone);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        inflater.inflate(R.menu.nav_items, menu);
        MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String inputText) {
        String userInput = inputText.toLowerCase();
        List<EventData> Listfiltre = new ArrayList<>();
        switch (selectedFilter) {
            case 0:
            for (EventData event : (listEventData)) {
                if (event.getNomLieu() != null) {
                    if (event.getNomLieu().toLowerCase().contains(userInput))
                        Listfiltre.add(event);
                }
            }
            case 1:
                for (EventData event : (listEventData)) {
                    if (event.getThematique() != null) {
                        if (event.getThematique().toLowerCase().contains(userInput))
                            Listfiltre.add(event);
                    }
                }
            case 2:
                for (EventData event : (listEventData)) {
                    if (event.getDate() != null) {
                        if (event.getDate().toLowerCase().contains(userInput))
                            Listfiltre.add(event);
                    }
                }
            case 3:
                for (EventData event : (listEventData)) {
                    if (event.getMotcle() != null) {
                        if (event.getMotcle().toLowerCase().contains(userInput))
                            Listfiltre.add(event);
                    }
                }
        }
        adapter.updateEventList(Listfiltre);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.parcours:
                Toast.makeText(MainActivity.this, "parcours", Toast.LENGTH_SHORT).show();
                break;
            case R.id.carte:
                Intent myIntentcarte = new Intent(this,MapsActivity.class);
                startActivity(myIntentcarte);
                break;
        }
        return super.onOptionsItemSelected(item);

    }



}
