package com.example.pracainzynierska;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.viewmodels.PlaceViewModel;
import com.google.android.gms.maps.GoogleMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class UpdateActivity extends AppCompatActivity {

    private Place UpdatedPlace, CurrentPlace;

    private EditText PlaceName;
    private EditText Description;

    private Button ImageButton;
    private Button updateButton;

    private TextView filePath;
    private TextView source;

    private double lat;
    private double lng;

    private Spinner spinner;

    private GoogleMap Map;

    private String path;

    private PlaceViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle("Zaktualizuj Obiekt");

        CurrentPlace = getIntent()
                .getExtras()
                .getParcelable("place_item");

        PlaceName = findViewById(R.id.placeName);
        Description = findViewById(R.id.description);
        ImageButton = findViewById(R.id.imageButton);
        updateButton = findViewById(R.id.updateButton);
        spinner = (Spinner) findViewById(R.id.spinner);

        PlaceName.setText(CurrentPlace.placeName);
        Description.setText(CurrentPlace.description);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.place_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);


    }

    public void UpdatePlace(View view){
        String placeDesc = Description.getText().toString();
        String placeName = PlaceName.getText().toString();
        String placeType = spinner.getSelectedItem().toString();

        UpdatedPlace = new Place(CurrentPlace.id,placeName,placeDesc,path,placeType,lng,lat );
        viewModel.update(UpdatedPlace);

        Intent intent = new Intent(this,PlaceActivity.class);
        startActivity(intent);

    }
    public void onActivityResult (int requestCode, int resultCode,
                                  Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1001){
            if (resultCode == RESULT_OK) {
                Uri currFileURI = data.getData();
                path = currFileURI.getPath().toString();
                filePath.setText(path);
            }
            if(resultCode == RESULT_CANCELED){
                path = CurrentPlace.imageUrl;
            }
        }
        if(requestCode == 100){
            if (resultCode == RESULT_OK){
                lat = data.getDoubleExtra("Latitude",0.0);
                lng = data.getDoubleExtra("Longitude",0.0);
            }
            else {
                lat = CurrentPlace.latitude;
                lng = CurrentPlace.longitude;
            }
        }
    }

    public void addMarkerPlace(View view){
        Intent intent = new Intent(this, MapsAddMarkerPlaceActivity.class);
        startActivityForResult(intent,100);
    }
}
