package com.example.corona.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.corona.api.CasesApi;
import com.example.corona.model.Country;
import com.example.corona.model.Summary;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoronaRepository {
    private MutableLiveData<ArrayList<Country>> countries = new MutableLiveData<>();
    private static CoronaRepository instance;

    private CoronaRepository(Application application) {
        getCountries();
    }

    public LiveData<ArrayList<Country>> getAllCountries() {
        return countries;
    }

    public static CoronaRepository getInstance(Application application) {
        if (instance == null) {
            instance = new CoronaRepository(application);
        }
        return instance;
    }

    private void getCountries() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        CasesApi endpoints = retrofit.create(CasesApi.class);

        Call<Summary> call = endpoints.getCountries();
        call.enqueue(new Callback<Summary>() {
            @Override
            public void onResponse(Call<Summary> call, Response<Summary> response) {
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    Summary apiCountries = response.body();
                    countries.setValue(apiCountries.getCountries());
                    System.out.println("API COUNTRIES ARE HERE:" + Arrays.asList(apiCountries.getCountries()));
                }
            }
            @Override
            public void onFailure(Call<Summary> call, Throwable t) {
                ;
                Log.i("RetrofitError", "" + t.getMessage());
                Log.i("Retrofit", "Something went wrong :(" + call.toString());
            }
        });
    }
}