package com.example.pracainzynierska;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pracainzynierska.workers.LocationWorker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_JOB_ID = 1000;
    private static final int REQUEST_LOCATION_PERMISSION = 10 ;
    final private String CHURCH = "Obiekt Sakralny";
    final private String MUSEUM = "Muzeum";
    final private String PALACE = "Pałac";
    final private String LAKE = "Jezioro";
    final private String HISTORY_PLACE = "Obiekt Historyczny";
    final private String PARK = "Park";
    public double longitude;
    public double latitide;
    private TextView textView;
    boolean isfinished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Log.d("SSSS", "getLocation: permissions granted");
            startLocationWorker();

        }
    }

    private void startLocationWorker() {
        WorkRequest locationRequest = new OneTimeWorkRequest.Builder(LocationWorker.class)
                .build();
        WorkManager.getInstance(getApplicationContext())
                .enqueue(locationRequest);
     receiveDataFromWorker(locationRequest);

    }
   private void receiveDataFromWorker(WorkRequest workRequest){
        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(workRequest.getId())
                .observeForever(new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {

                       if(workInfo.getState() == WorkInfo.State.SUCCEEDED ){
                          longitude= workInfo.getOutputData().getDouble("longitude",longitude);
                          latitide= workInfo.getOutputData().getDouble("latitude",latitide);
                          locationSharedPreferences();
                       }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationWorker();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.trybnocny:
                Intent intent = new Intent(this, AddPlaceActivity.class);
                this.startActivity(intent);
                break;
            case R.id.dodaj_miejsce:
                Intent intent2 = new Intent(this, AddPlaceActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.wyszukaj_miejsca_na_mapie:
                Intent intent1 = new Intent(this, showPlaceOnMapActivity.class);
                this.startActivity(intent1);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }

        return true;
    }
    public void locationSharedPreferences(){
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "PREFERENCE_FILE_KEY",Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("longitude",Double.toString(longitude));
        editor.putString("latitude",Double.toString(latitide));
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }
    public void placeMuseum(View view) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("name",MUSEUM);
        startActivity(intent);
    }

    public void placePark(View view) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("name",PARK);
        startActivity(intent);
    }

    public void placeLake(View view) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("name",LAKE);
        startActivity(intent);
    }

    public void placeHistoryPlace(View view) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("name",HISTORY_PLACE);
        startActivity(intent);
    }

    public void placeChurch(View view) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("name",CHURCH);
        startActivity(intent);
    }

    public void placePalace(View view) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra("name",PALACE);
        startActivity(intent);
    }

    public void mapView(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivity(intent);
    }
    public void addPlace(View view) {
        Intent intent = new Intent(this, AddPlaceActivity.class);
        startActivity(intent);
    }
}
