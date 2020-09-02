package com.example.pracainzynierska.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pracainzynierska.AddPlaceActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.data.Place;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {
    private String pathFile;
    private AddPlaceActivity addPlaceActivity;


    class PlaceHolder extends RecyclerView.ViewHolder {
        private final TextView placeName;
        private final TextView distanceToPlace;
        private final ImageView imageUrl;
        private PlaceHolder(@NonNull View itemView) {
            super(itemView);
            placeName =  itemView.findViewById(R.id.distanceToplace);
            distanceToPlace = itemView.findViewById(R.id.distanceToplace);
            imageUrl = itemView.findViewById(R.id.imageView2);

        }
    }


    private final LayoutInflater mInflater;
    private List<Place> placeItem;

    public PlaceAdapter(Context context) { mInflater = LayoutInflater.from(context);

    }

    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.fragment_list,parent,false);
    return new PlaceHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {
        if(null != placeItem){
            Place current = placeItem.get(position);
            holder.placeName.setText(current.placeName);

            if(current.imageUrl != null) {
                Picasso.get().load(current.imageUrl).fit().placeholder(R.drawable.csharp).into(holder.imageUrl);
            }
            else{
                Picasso.get().load(R.drawable.cplus).fit().into(holder.imageUrl);
            }
            }else{
            holder.placeName.setText("No Places");
        }
    }

    @Override
    public int getItemCount() {
        if(placeItem != null)
            return placeItem.size();
     else  return 0;
    }
   public void setWords(List<Place> place){
        placeItem = place;
        notifyDataSetChanged();

    }



}
