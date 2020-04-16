package com.example.pracainzynierska.data;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Place.class,},version = 2)
public abstract class AppDatabase extends RoomDatabase {
        public abstract PlaceDao placeDao();

        private static volatile AppDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static AppDatabase getDatabase(final Context context){
            if (INSTANCE == null){
                synchronized (AppDatabase.class){
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class,"places_database").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
        private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
                databaseWriteExecutor.execute(() -> {
                    PlaceDao dao = INSTANCE.placeDao();
                    Place place = new Place("Malczewskiego","Opis","https");
                    Place place1 = new Place("Narodowy","Opis","https:");
                    Place place2 = new Place("costam","Opis","https:");
                    Place place3 = new Place("cost1am","Opis","https:");
                    Place place4= new Place("cost1am","Opis","https:");
                    Place place5 = new Place("cost2am","Opis","https:");
                    Place place6 = new Place("cost3am","Opis","https:");
                    Place place7 = new Place("cost4am","Opis","https:");
                    Place place8 = new Place("cost5am","Opis","https:");
                    Place place9 = new Place("cost6am","Opis","https:");

                    dao.insert(place);
                    dao.insert(place1);
                    dao.insert(place2);
                    dao.insert(place3);
                    dao.insert(place4);
                    dao.insert(place5);
                    dao.insert(place6);
                    dao.insert(place7);
                    dao.insert(place8);
                    dao.insert(place9);

        });
}
        };
}
