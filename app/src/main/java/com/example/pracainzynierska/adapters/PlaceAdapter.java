package com.example.pracainzynierska.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pracainzynierska.MainActivity;
import com.example.pracainzynierska.MapsActivity;
import com.example.pracainzynierska.PlaceDetailsActivity;
import com.example.pracainzynierska.R;
import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.utilities.LocationUtils;
import com.example.pracainzynierska.workers.LocationWorker;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkRequest;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {
    private String pathFile;
    private final LayoutInflater mInflater;
    private List<Place> placeItem;
    private LocationWorker locationWorker;
    private double distance;
    private MapsActivity mapsActivity;
    private Context context;
    private WorkRequest locationRequest;
    private MainActivity mainActivity;
    private LocationUtils locationUtils;
    private Location location;
    private SharedPreferences sharedPreferences;
    private double longitude;
    private double latitude;
    private View.OnClickListener onClickListener;

    class PlaceHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private final TextView placeName;
        private final TextView distanceToPlace;
        private final PlaceAdapter mAdapter;
        private final ImageView imageUrl;

        private PlaceHolder(@NonNull View itemView, PlaceAdapter adapter) {

            super(itemView);

            placeName =  itemView.findViewById(R.id.placeName);
            distanceToPlace = itemView.findViewById(R.id.distanceToPlace);
            imageUrl = itemView.findViewById(R.id.placeImage);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();
            Context context = v.getContext();
            Place current = placeItem.get(mPosition);

            Intent intent = new Intent(v.getContext(), PlaceDetailsActivity.class);
            intent.putExtra("place_item", current);
            context.startActivity(intent);
            mAdapter.notifyDataSetChanged();
            }
    }

    public PlaceAdapter(Context context) { mInflater = LayoutInflater.from(context);
        this.context = context;
        locationUtils = new LocationUtils();
        mainActivity = new MainActivity();
        sharedPreferences =  context.getSharedPreferences("PREFERENCE_FILE_KEY",0);
        location = new Location("something");
    }

    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = mInflater.inflate(R.layout.fragment_list, parent, false);
            return new PlaceHolder(itemView,this);


    }


    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {
        if(null != placeItem){
            receiveSharedPreferences();
            createLocation();
            Place current = placeItem.get(position);
           distance = locationUtils.distanceToPlaceCalculation(current,location);
            String distanceTotext = Double.toString(distance);
            holder.placeName.setText(current.placeName);
           holder.distanceToPlace.setText(distanceTotext + "km");
           holder.imageUrl.setImageResource(R.drawable.kotlin);
          /*  if(current.imageUrl != null) {
                Picasso.get()
                        .load(R.drawable.cplus)
                        .fit()                              TODO
                        .into(holder.imageUrl);
            }
            else{
                Picasso.get()
                        .load(R.drawable.cplus)
                        .resize(10,10)
                        .into(holder.imageUrl);
            }*/
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
private void receiveSharedPreferences(){
        longitude = Double.parseDouble(sharedPreferences.getString("longitude","0"));
        latitude = Double.parseDouble(sharedPreferences.getString("latitude","0"));

}
private void createLocation(){
        location.setLatitude(latitude);
        location.setLongitude(longitude);
}
}
