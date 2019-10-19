package com.example.Remote;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.0.8:51316/api/";

    public static CataService getCataService(){
        return RetrofitClient.getClient(API_URL).create(CataService.class);
    }
}
