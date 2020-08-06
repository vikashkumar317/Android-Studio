package com.example.learntablayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://api.androidhive.info/json/";

    @GET("movies_2017.json")
    Call<List<Movie>> getMovies();
}

