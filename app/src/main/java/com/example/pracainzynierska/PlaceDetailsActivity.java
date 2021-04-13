package com.example.pracainzynierska;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.viewmodels.PlaceViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class PlaceDetailsActivity extends AppCompatActivity {
    private TextView textView;
    private PlaceViewModel viewModel;
    private Place place;
    private Button delete, edit;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        Place placeItem = getIntent()
                .getExtras()
                .getParcelable("place_item");
        place = placeItem;
        imageView = findViewById(R.id.placeImage);
        textView = findViewById(R.id.description);
        delete = findViewById(R.id.deleteButton);
        edit = findViewById(R.id.editButton);
        textView.setText(placeItem.description);



        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

    }
    public void deletePlace(View view)
    {
        Intent intent = new Intent(this, PlaceActivity.class);
        viewModel.delete(place);
        startActivity(intent);
    }

    public void updatePlace(View view)
    {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("place_item",place);
        startActivity(intent);

    }
}
