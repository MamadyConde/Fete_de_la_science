package fr.m2ccn.istic.fete_de_la_science;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailEventActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView myTitre;
    TextView myanimation,myDescriptionlongue,myThematique,myHoraire,myLieu,myAdresse,myCodePostal;
    ImageView myImage;

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
        }
    }

}
