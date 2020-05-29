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

import com.google.android.gms.maps.GoogleMap;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlaceActivity extends AppCompatActivity {
    public static final String EXTRA_PLACE_NAME = "com.example.android.placelistsql.PLACENAME";
    public static final String EXTRA_PLACE_DESCRIPTION = "com.example.android.placelistsql.DESCRIPTION";
    public static final String EXTRA_PLACE_IMAGE = "com.example.android.placelistsql.PLACEIMAGE";
    public static final String EXTRA_PLACE_TYPE = "com.example.android.placelistsql.PLACETYPE";
    public static final String EXTRA_PLACE_LATITUDE = "com.example.android.placelistsql.LATITUDE";
    public static final String EXTRA_PLACE_LONGTITUDE = "com.example.android.placelistsql.LONGTITUDE";
    public static final int ADD_PLACE_ACTIVITY_REQUEST_CODE = 1;
    private EditText mPlaceName;
    private EditText mDescription;
    private Button mImageButton;
    private Button mSubmitButton;
    private Spinner spinner;
    private GoogleMap mMap;
    private String path;
    private TextView filePath;
    private TextView source;
    private double lat;
    private double lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        getSupportActionBar().setTitle("Dodaj Obiekt");

        filePath = findViewById(R.id.Source);
        mPlaceName = findViewById(R.id.placeName);
        mDescription = findViewById(R.id.description);
        mImageButton = findViewById(R.id.imageButton);
        mSubmitButton = findViewById(R.id.submitButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.place_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent(AddPlaceActivity.this, PlaceActivity.class);
                if (TextUtils.isEmpty(mPlaceName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else  {
                    String placeName  = mPlaceName.getText().toString();
                    replyIntent.putExtra(EXTRA_PLACE_NAME, placeName);
                    String placeDescription  = mDescription.getText().toString();
                    replyIntent.putExtra(EXTRA_PLACE_DESCRIPTION, placeDescription);
                     String placeType  = spinner.getSelectedItem().toString();
                    replyIntent.putExtra(EXTRA_PLACE_TYPE, placeType);
                    String pathFile = path;
                    replyIntent.putExtra(EXTRA_PLACE_IMAGE, pathFile);
                    Double longitude = lng;
                    replyIntent.putExtra(EXTRA_PLACE_LONGTITUDE,longitude);
                    Double latitude = lat;
                    replyIntent.putExtra(EXTRA_PLACE_LATITUDE,latitude);

                    startActivity(replyIntent);
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
