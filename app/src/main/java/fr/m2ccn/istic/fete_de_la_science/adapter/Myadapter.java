package fr.m2ccn.istic.fete_de_la_science.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.m2ccn.istic.fete_de_la_science.DetailEventActivity;
import fr.m2ccn.istic.fete_de_la_science.R;
import fr.m2ccn.istic.fete_de_la_science.model.EventData;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>  {
    Context context;
    ArrayList<EventData> eventDataList;

    public Myadapter(Context contextAdapter , ArrayList<EventData> eventAdapter)
    {
        context = contextAdapter;
        eventDataList = eventAdapter;

    }
    @NonNull
    @Override
    public Myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myadapter.MyViewHolder holder, int position) {
        holder.titre.setText(eventDataList.get(position).getTitre());
        holder.description.setText(eventDataList.get(position).getDescription());
       // Picasso.get().load(eventDataList.get(position).getImage()).into(holder.image);

        holder.myCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(context, DetailEventActivity.class); //definition de l'intent

                myIntent.putExtra("Titre", eventDataList.get(holder.getAdapterPosition()).getTitre());
                myIntent.putExtra("Thematique", eventDataList.get(holder.getAdapterPosition()).getThematique());
                myIntent.putExtra("Ville", eventDataList.get(holder.getAdapterPosition()).getVille());
                myIntent.putExtra("Image", eventDataList.get(holder.getAdapterPosition()).getImage());
                myIntent.putExtra("Animation", eventDataList.get(holder.getAdapterPosition()).getAnimation());
                myIntent.putExtra("Description", eventDataList.get(holder.getAdapterPosition()).getDescription());
                myIntent.putExtra("Descriptionlongue", eventDataList.get(holder.getAdapterPosition()).getDescriptionlongue());
                myIntent.putExtra("Horaire", eventDataList.get(holder.getAdapterPosition()).getHoraire());
                myIntent.putExtra("CodePostal", eventDataList.get(holder.getAdapterPosition()).getCodePostale());
                myIntent.putExtra("Adresse", eventDataList.get(holder.getAdapterPosition()).getAdresse());
                myIntent.putExtra("Lieu", eventDataList.get(holder.getAdapterPosition()).getNomLieu());


                context.startActivity(myIntent); //lancement de l'intent
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventDataList.size();
    }

    public void updateEventList(List<EventData> newEventList){
        eventDataList = new ArrayList<EventData>();
        eventDataList.addAll(newEventList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView myCardView;
        TextView titre,ville,region, description;
        //ImageView image;
        //Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            titre = (TextView) itemView.findViewById(R.id.carTitre);
            description = (TextView) itemView.findViewById(R.id.carDescription);
            myCardView = (CardView) itemView.findViewById(R.id.cardview);

        }

    }
}
