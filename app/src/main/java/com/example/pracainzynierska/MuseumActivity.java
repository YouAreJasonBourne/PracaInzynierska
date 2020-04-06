package com.example.pracainzynierska;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pracainzynierska.adapters.MuseumAdapter;

public class MuseumActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.cplus,R.drawable.csharp,R.drawable.java,
    R.drawable.kotlin,R.drawable.javascript};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.programming_languages);
        s2 = getResources().getStringArray(R.array.description);

        MuseumAdapter museumAdapter = new MuseumAdapter(this,s1,s2,images);

        recyclerView.setAdapter(museumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
