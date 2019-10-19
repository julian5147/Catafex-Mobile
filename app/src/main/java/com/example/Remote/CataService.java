package com.example.Remote;

import com.example.Models.Cata;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CataService {

    @POST("ApiRegistrarCata/")
    Call<Boolean> registrarCata(@Body Cata cata);
}
