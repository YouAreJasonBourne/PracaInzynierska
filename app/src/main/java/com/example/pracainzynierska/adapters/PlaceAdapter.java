package com.example.pracainzynierska.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pracainzynierska.R;
import com.example.pracainzynierska.data.Place;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {
    class PlaceHolder extends RecyclerView.ViewHolder {
        private final TextView placeName;
        private final TextView placeDescription;
        private final ImageView imageUrl;
        private PlaceHolder(@NonNull View itemView) {
            super(itemView);
            placeName =  itemView.findViewById(R.id.textView2);
            placeDescription = itemView.findViewById(R.id.textView3);
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
        if(placeItem != null){
            Place current = placeItem.get(position);
            holder.placeName.setText(current.placeName);
            holder.placeDescription.setText(current.description);
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
