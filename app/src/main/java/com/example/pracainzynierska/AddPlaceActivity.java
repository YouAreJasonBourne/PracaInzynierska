package com.example.pracainzynierska;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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

public class AddPlaceActivity extends AppCompatActivity {
    private  final String PLACE  = "Wszystkie miejsca";
    private EditText mPlaceName;
    private EditText mDescription;

    private Button mImageButton;
    private Button mSubmitButton;

    private TextView filePath;
    private TextView source;

    private double lat;
    private double lng;

    private Spinner spinner;

    private GoogleMap mMap;

    private String path;

    private PlaceViewModel viewModel;

    private Place place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        getSupportActionBar().setTitle("Dodaj Obiekt");

        mPlaceName = findViewById(R.id.placeName);
        mDescription = findViewById(R.id.description);
        mImageButton = findViewById(R.id.imageButton);
        mSubmitButton = findViewById(R.id.submitButton);
        spinner = (Spinner) findViewById(R.id.spinner);

        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.place_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent AddPlaceIntent = new Intent(AddPlaceActivity.this, PlaceActivity.class);
                AddPlaceIntent.putExtra("place",PLACE);
                if (TextUtils.isEmpty(mPlaceName.getText())) {
                    setResult(RESULT_CANCELED, AddPlaceIntent);
                } else  {
                    String placeName  = mPlaceName.getText().toString();
                    String placeDescription  = mDescription.getText().toString();
                    String placeType  = spinner.getSelectedItem().toString();
                    String pathFile = path;

                    Double longitude = lng;
                    Double latitude = lat;

                    place = new Place(placeName,placeDescription,pathFile,placeType,longitude,latitude);

                    viewModel.insert(place);

                    startActivity(AddPlaceIntent);
                }
                finish();
            }
        });


    }

 public void openFileManager(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Demo"),1001);

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
                filePath.setText("Nie wybrano pliku" );
            }
            }
     if(requestCode == 100){
    if (resultCode == RESULT_OK){
        lat = data.getDoubleExtra("Latitude",0.0);
        lng = data.getDoubleExtra("Longitude",0.0);
    }
     }
    }

    public void addPlace(View view) {
        Intent intent = new Intent(this, AddPlaceActivity.class);
        startActivity(intent);
    }
    public void addMarkerPlace(View view){
        Intent intent = new Intent(this, MapsAddMarkerPlaceActivity.class);
        startActivityForResult(intent,100);
    }


}
