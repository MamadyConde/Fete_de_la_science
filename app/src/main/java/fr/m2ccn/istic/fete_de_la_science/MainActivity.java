package fr.m2ccn.istic.fete_de_la_science;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    private EditText mSearchField;
    private ImageButton mSearchBtn;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
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
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();
                Log.i("tatata",searchText);
                firebaseSearch(searchText);

            }
        });

    }

    private void firebaseSearch(String searchText) {
        Toast.makeText(MainActivity.this, "Started Search", Toast.LENGTH_LONG).show();
        int i = 0;
        while (i < 6366) {
            reference = FirebaseDatabase.getInstance().getReference("dataFetedelaScience").child(Integer.toString(i));
            Query firebaseSearchQuery = reference.child("fields").child("titre_fr").startAt(searchText).endAt(searchText + "\uf8ff");
            Log.i("zzzzz", firebaseSearchQuery.toString());
            i++;

            FirebaseRecyclerAdapter<EventData, MyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EventData, MyViewHolder>(
                    EventData.class,
                    R.layout.cardview,
                    MyViewHolder.class,
                    firebaseSearchQuery

            ) {

                @Override
                protected void populateViewHolder(MyViewHolder viewHolder, EventData model, int position) {

                    viewHolder.setDetails(getApplicationContext(),model.getTitre(), model.getDescription());
                }
            };

            recyclerView.setAdapter(firebaseRecyclerAdapter);
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView myCardView;
        TextView titre,ville,region, description;

        View mView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }
        public void setDetails(Context ctx, String Titre, String Description){

            titre =  (TextView) itemView.findViewById(R.id.carTitre);
            description = (TextView) itemView.findViewById(R.id.carDescription);

            titre.setText(Titre);
            description.setText(Description);
        }

    }

}
