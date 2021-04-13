package com.example.pracainzynierska.data;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Place.class,},version = 12)
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
                                AppDatabase.class,"places_database").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration().allowMainThreadQueries()
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
        });
}
        };
}
