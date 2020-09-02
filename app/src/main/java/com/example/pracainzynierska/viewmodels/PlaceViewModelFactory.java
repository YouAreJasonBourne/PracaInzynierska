package com.example.pracainzynierska.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PlaceViewModelFactory implements ViewModelProvider.Factory {
   private final Application application;

   public PlaceViewModelFactory(Application mApplication){
       application = mApplication;
   }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(PlaceViewModel.class)){
            return (T) new PlaceViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
