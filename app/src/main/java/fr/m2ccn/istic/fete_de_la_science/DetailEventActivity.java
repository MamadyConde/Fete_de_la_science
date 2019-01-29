package fr.m2ccn.istic.fete_de_la_science;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailEventActivity extends AppCompatActivity{

    Toolbar myToolbar;
    TextView myTitre,myTelephone;
    TextView myanimation,myDescriptionlongue,myThematique,myHoraire,myLieu,myAdresse,myCodePostal;
    ImageView myImage;

    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        myToolbar = findViewById(R.id.toolbar);
        myTitre = findViewById(R.id.detailTitre); //association avec l'id
        myImage = findViewById(R.id.detailImage); //association avec l'id
        myanimation = findViewById(R.id.detailAnimation); //association avec l'id
        myDescriptionlongue = findViewById(R.id.detailDescriptionlongue); //association avec l'id
        myThematique = findViewById(R.id.detailThematique); //association avec l'id
        myHoraire = findViewById(R.id.detailHoraire); //association avec l'id
        myLieu = findViewById(R.id.detailLieu); //association avec l'id
        myAdresse = findViewById(R.id.detailAdresse);
        myTelephone = findViewById(R.id.detailtelephone);
        ImageView myImage_phone = findViewById(R.id.detailImage_phone);


        Bundle myBundle = getIntent().getExtras();
        if (myBundle != null) {
           // myToolbar.setTitle(myBundle.getString("Titre")); //on extrait du bindle l'info Titre
            myToolbar.setTitle("Fête de la Science");
            myTitre.setText(myBundle.getString("Titre"));
            myanimation.setText(myBundle.getString("Animation"));
            myDescriptionlongue.setText(myBundle.getString("Descriptionlongue"));
            myThematique.setText(myBundle.getString("Thematique"));
            myHoraire.setText(myBundle.getString("Horaire"));
            myAdresse.setText(myBundle.getString("Adresse"));
            myLieu.setText(myBundle.getString("Lieu"));
            Picasso.get().load(myBundle.getString("Image")).into(myImage);

            myTelephone.setText(myBundle.getString("Telephone"));
            myImage_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callPhone();
                }

            });
        }
    }

    public void callPhone(){
        Bundle bundle = getIntent().getExtras();
        myTelephone.setText(bundle.getString("Telephone"));
        if(myTelephone.length()>0){
            if (ContextCompat.checkSelfPermission(DetailEventActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(DetailEventActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            } else {
                String dial = "tel:" + myTelephone;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else {
            Toast.makeText(DetailEventActivity.this, "numero vide ", Toast.LENGTH_LONG);

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                callPhone();
            }else{
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.liste:
                Intent myIntent = new Intent(this,MainActivity.class);
                startActivity(myIntent);

                break;
            case R.id.parcours:
                Toast.makeText(DetailEventActivity.this, "parcours", Toast.LENGTH_SHORT).show();
                break;
            case R.id.carte:
                Intent myIntentcarte = new Intent(this,MapsActivity.class);
                startActivity(myIntentcarte);
                break;
        }
        return super.onOptionsItemSelected(item);

    }
*/



}
