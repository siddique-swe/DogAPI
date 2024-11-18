package com.example.dogapi;
import com.example.dogapi.network.DogApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://dog.ceo/api/";
    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static DogApiService getApiService(){
        return getRetrofitInstance().create(DogApiService.class);
    }
}
